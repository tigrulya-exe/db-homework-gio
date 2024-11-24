package university.homework.executor;

import university.homework.command.Command;

public record IndexedCommand(int index, Command command) {
}
