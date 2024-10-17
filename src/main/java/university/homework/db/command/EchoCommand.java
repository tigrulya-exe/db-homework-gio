package university.homework.db.command;

import java.util.Scanner;
import university.homework.db.command.result.CommandResult;

public class EchoCommand implements Command {
    @Override
    public String getName() {
        return "Echo command";
    }

    @Override
    public CommandResult execute(Scanner userInputReader) throws CommandException {
        return CommandResult.success("Your args: "
            + userInputReader.next() + userInputReader.nextLine());
    }
}
