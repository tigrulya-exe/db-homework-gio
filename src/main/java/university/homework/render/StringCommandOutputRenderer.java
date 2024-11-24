package university.homework.render;

import java.io.PrintStream;

public class StringCommandOutputRenderer implements CommandOutputRenderer {
    private final PrintStream outputStream;

    public StringCommandOutputRenderer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void renderSuccess(String result) {
        outputStream.print("[success]: ");
        outputStream.println(result);
        printDelimiter();
    }

    @Override
    public void askUser(String text) {
        outputStream.print(text);
    }

    @Override
    public void renderError(String errorMessage) {
        outputStream.print("[error]: ");
        outputStream.println(errorMessage);
        printDelimiter();
    }

    private void printDelimiter() {
        outputStream.println("----------------");
        outputStream.println();
    }
}
