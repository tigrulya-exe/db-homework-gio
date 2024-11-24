package university.homework.render;

import java.util.Collection;
import university.homework.executor.IndexedCommand;

public interface CommandRenderer {
    void render(Collection<IndexedCommand> commands);
}
