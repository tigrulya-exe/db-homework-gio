package university.homework.db.command.result;

public abstract sealed class CommandResult permits StopExecution, SuccessResult, UserErrorResult {
    private final String result;

    public CommandResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static CommandResult success(String result) {
        return new SuccessResult(result);
    }

    public static CommandResult userError(String result) {
        return new UserErrorResult(result);
    }

    public static CommandResult stopExecution() {
        return new StopExecution();
    }
}
