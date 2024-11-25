package university.homework.render;

public interface CommandOutputRenderer {
    void render(String text);

    void renderSuccess(String result);

    void renderError(String errorMessage);
}
