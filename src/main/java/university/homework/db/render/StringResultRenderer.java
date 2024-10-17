package university.homework.db.render;

import java.io.PrintStream;

public class StringResultRenderer implements ResultRenderer {
    private final PrintStream outputStream;

    public StringResultRenderer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void renderSuccess(String result) {
        outputStream.print("[success]: ");
        outputStream.println(result);
        printDelimiter();
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
