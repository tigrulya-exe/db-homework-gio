package university.homework.db.render;

import java.util.Collection;
import university.homework.db.command.IndexedCommand;

public interface CommandRenderer {
    void render(Collection<IndexedCommand> commands);
}
