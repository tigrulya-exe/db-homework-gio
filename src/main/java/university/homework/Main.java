package university.homework;

import java.util.List;
import java.util.Scanner;
import university.homework.command.Command;
import university.homework.executor.CommandExecutor;
import university.homework.render.StringCommandRenderer;
import university.homework.render.StringCommandOutputRenderer;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = List.of(
        );

        CommandExecutor commandExecutor = new CommandExecutor(
            commands,
            new StringCommandRenderer(System.out),
            new StringCommandOutputRenderer(System.out),
            new Scanner(System.in)
        );

        commandExecutor.run();
    }
}
