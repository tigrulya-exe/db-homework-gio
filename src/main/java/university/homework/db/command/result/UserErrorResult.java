package university.homework.db.command.result;

public final class UserErrorResult extends CommandResult {
    public UserErrorResult(String errorMessage) {
        super(errorMessage);
    }
}