package university.homework.executor;

import java.util.Scanner;
import university.homework.render.CommandOutputRenderer;

public record ExecutionContext(
    Scanner userInputReader,
    CommandOutputRenderer commandOutputRenderer) {
}
