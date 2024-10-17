package university.homework.db.command;

import java.util.Scanner;
import university.homework.db.command.result.CommandResult;

public class ConstStringCommand implements Command {
    private final String message;

    public ConstStringCommand(String message) {
        this.message = message;
    }

    @Override
    public String getName() {
        return "Command returning " + message;
    }

    @Override
    public CommandResult execute(Scanner userInputReader) throws CommandException {
        return CommandResult.success(message);
    }
}
