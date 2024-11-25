package university.homework.render;

import java.io.PrintStream;

public class StringCommandOutputRenderer implements CommandOutputRenderer {
    private final PrintStream outputStream;

    public StringCommandOutputRenderer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void renderSuccess(String result) {
        renderResult("Result: SUCCESS", result);

    }

    @Override
    public void render(String text) {
        outputStream.print(text);
    }

    @Override
    public void renderError(String errorMessage) {
        renderResult("Result: ERROR", errorMessage);
    }

    private void renderResult(String prefix, String message) {
        outputStream.println();
        outputStream.println(prefix);
        printDelimiter();
        outputStream.println(message);
        printDelimiter();
        outputStream.println();
    }

    private void printDelimiter() {
        outputStream.println("----------------");
    }
}
