package university.homework.command;

import university.homework.executor.ExecutionContext;
import university.homework.command.result.CommandResult;

public class StopExecutionCommand implements Command {
    @Override
    public String getName() {
        return "Stop execution";
    }

    @Override
    public CommandResult execute(ExecutionContext context) throws CommandException {
        context.commandOutputRenderer().renderSuccess("Shutting down...");
        return CommandResult.stopExecution();
    }
}
