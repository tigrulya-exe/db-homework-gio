package university.homework.db.render;

public interface ResultRenderer {
    void renderSuccess(String result);

    void renderError(String errorMessage);
}
