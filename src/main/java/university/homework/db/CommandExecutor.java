package university.homework.db;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import university.homework.db.command.Command;
import university.homework.db.command.CommandException;
import university.homework.db.command.IndexedCommand;
import university.homework.db.command.result.StopExecution;
import university.homework.db.command.result.SuccessResult;
import university.homework.db.command.result.UserErrorResult;
import university.homework.db.render.CommandRenderer;
import university.homework.db.render.ResultRenderer;

public class CommandExecutor {

    private final Map<Integer, IndexedCommand> commands;

    private final Scanner userInputReader;

    private final CommandRenderer commandRenderer;
    private final ResultRenderer resultRenderer;

    public CommandExecutor(
        List<Command> commands,
        CommandRenderer commandRenderer,
        ResultRenderer resultRenderer,
        Scanner userInputReader
    ) {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Commands list shouldn't be empty");
        }

        this.userInputReader = userInputReader;
        this.resultRenderer = resultRenderer;
        this.commandRenderer = commandRenderer;
        this.commands = IntStream.range(0, commands.size())
            .mapToObj(idx -> new IndexedCommand(idx + 1, commands.get(idx)))
            .collect(Collectors.toMap(
                IndexedCommand::index,
                Function.identity()
            ));
    }

    public void run() {
        commandLoop:
        while (true) {
            commandRenderer.render(commands.values());

            // at first user need to choose command to execute
            Command nextCommand = getNextCommand();
            if (nextCommand == null) {
                continue;
            }

            try {
                switch (nextCommand.execute(userInputReader)) {
                    case SuccessResult success ->
                        resultRenderer.renderSuccess(success.getResult());
                    case UserErrorResult userErrorResult ->
                        resultRenderer.renderError(userErrorResult.getResult());
                    case StopExecution ignored -> {
                        break commandLoop;
                    }
                }
            } catch (CommandException commandException) {
                resultRenderer.renderError("Fatal error: " +
                    commandException.getMessage());
                break;
            }
        }
    }

    private Command getNextCommand() {
        try {
            return Optional.ofNullable(
                    commands.get(userInputReader.nextInt()))
                .map(IndexedCommand::command)
                .orElseThrow(NoSuchElementException::new);

            // handle both formatting exceptions and nonexistent command id
        } catch (NoSuchElementException exception) {
            resultRenderer.renderError("Wrong command id");
            return null;
        }
    }
}
