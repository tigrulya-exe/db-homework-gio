package university.homework.db;

import java.util.List;
import java.util.Scanner;
import university.homework.db.command.Command;
import university.homework.db.command.ConstStringCommand;
import university.homework.db.command.EchoCommand;
import university.homework.db.render.StringCommandRenderer;
import university.homework.db.render.StringResultRenderer;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = List.of(
            new ConstStringCommand("One"),
            new ConstStringCommand("Two"),
            new EchoCommand(),
            new ConstStringCommand("Three"),
            new ConstStringCommand("Four")
        );

        CommandExecutor commandExecutor = new CommandExecutor(
            commands,
            new StringCommandRenderer(System.out),
            new StringResultRenderer(System.out),
            new Scanner(System.in)
        );

        commandExecutor.run();
    }
}
