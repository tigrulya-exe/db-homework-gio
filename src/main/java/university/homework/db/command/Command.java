package university.homework.db.command;

import java.util.Scanner;
import university.homework.db.command.result.CommandResult;

public interface Command {
    String getName();

    CommandResult execute(Scanner userInputReader) throws CommandException;
}
