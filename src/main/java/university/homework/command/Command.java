package university.homework.command;

import university.homework.executor.ExecutionContext;
import university.homework.command.result.CommandResult;

public interface Command {
    String getName();

    CommandResult execute(ExecutionContext context) throws CommandException;
}
