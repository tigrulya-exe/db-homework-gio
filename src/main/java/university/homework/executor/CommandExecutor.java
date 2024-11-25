package university.homework.executor;

import university.homework.command.Command;
import university.homework.command.CommandException;
import university.homework.command.result.StopExecution;
import university.homework.command.result.SuccessResult;
import university.homework.command.result.UserErrorResult;
import university.homework.render.CommandOutputRenderer;
import university.homework.render.CommandRenderer;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandExecutor {

    private final Map<Integer, IndexedCommand> commands;

    private final Scanner userInputReader;

    private final CommandRenderer commandRenderer;
    private final CommandOutputRenderer commandOutputRenderer;
    private final ExecutionContext executionContext;

    public CommandExecutor(
            List<Command> commands,
            CommandRenderer commandRenderer,
            CommandOutputRenderer commandOutputRenderer,
            Scanner userInputReader) {
        if (commands == null || commands.isEmpty()) {
            throw new IllegalArgumentException("Commands list shouldn't be empty");
        }

        this.userInputReader = userInputReader;
        this.commandOutputRenderer = commandOutputRenderer;
        this.commandRenderer = commandRenderer;
        this.commands = IntStream.range(0, commands.size())
                .mapToObj(idx -> new IndexedCommand(idx + 1, commands.get(idx)))
                .collect(Collectors.toMap(
                        IndexedCommand::index,
                        Function.identity()
                ));
        this.executionContext = new ExecutionContext(
                userInputReader, commandOutputRenderer);
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
                switch (nextCommand.execute(executionContext)) {
                    case SuccessResult success -> commandOutputRenderer.renderSuccess(success.getResult());
                    case UserErrorResult userErrorResult ->
                            commandOutputRenderer.renderError(userErrorResult.getResult());
                    case StopExecution ignored -> {
                        break commandLoop;
                    }
                }
            } catch (CommandException commandException) {
                commandOutputRenderer.renderError("Fatal error: " +
                        commandException.getMessage());
                break;
            }

            waitForUserInput();
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
            commandOutputRenderer.renderError("Wrong command id");
            waitForUserInput();
            return null;
        }
    }

    private void waitForUserInput() {
        commandOutputRenderer.render("Press any key to continue...\n");
        userInputReader.nextLine();
        userInputReader.nextLine();
    }
}
