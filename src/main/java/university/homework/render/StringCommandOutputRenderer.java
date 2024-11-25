package university.homework.render;

import java.io.PrintStream;

public class StringCommandOutputRenderer implements CommandOutputRenderer {
    private final PrintStream outputStream;

    public StringCommandOutputRenderer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void renderSuccess(String result) {
        outputStream.println();
        outputStream.println("Result: SUCCESS");
        outputStream.println(result);
        printDelimiter();
    }

    @Override
    public void render(String text) {
        outputStream.print(text);
    }

    @Override
    public void renderError(String errorMessage) {
        outputStream.println();
        outputStream.println("Result: ERROR");
        outputStream.println(errorMessage);
        printDelimiter();
    }

    private void printDelimiter() {
        outputStream.println("----------------");
        outputStream.println();
    }
}
