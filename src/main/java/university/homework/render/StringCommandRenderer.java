package university.homework.render;

import java.io.PrintStream;
import java.util.Collection;
import university.homework.executor.IndexedCommand;

public class StringCommandRenderer implements CommandRenderer {

    private final PrintStream outputStream;

    public StringCommandRenderer(PrintStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void render(Collection<IndexedCommand> commands) {
        commands.stream()
            .map(this::render)
            .forEach(outputStream::println);

        outputStream.println();
        outputStream.println("Choose the id of the command to execute:");
    }

    private String render(IndexedCommand indexedCommand) {
        return indexedCommand.index() + ". " + indexedCommand.command().getName();
    }
}
