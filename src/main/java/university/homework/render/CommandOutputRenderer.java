package university.homework.render;

public interface CommandOutputRenderer {
    void askUser(String text);

    void renderSuccess(String result);

    void renderError(String errorMessage);
}
