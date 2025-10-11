package br.ufma.compiler;


import java.util.*;
import java.util.stream.Collectors;

class Command {
    public enum Type {
        ADD,
        SUB,
        PUSH,
        POP,
        PRINT;
    }

    public Command.Type type;
    public  String arg = "";

    public Command (String[] command) {
        type = Command.Type.valueOf(command[0].toUpperCase());
        if (command.length > 1) {
            arg = command[1];
        }
    }

    public String toString () {
        return type.name() + " " + arg;
    }
}

public class Interpretador {

    List<String[]> commands;
    Stack<Integer> stack = new Stack<>();
    Map<String, Integer> variables = new HashMap<>();

    Interpretador (List<String> input) {
        commands = input.stream()
                .map(String::strip)
                .filter(s -> !s.isEmpty() && !s.startsWith("//"))
                .map(s -> s.split(" "))
                .collect(Collectors.toList());
    }

    public boolean hasMoreCommands () {
        return !commands.isEmpty();
    }

    public Command nexCommand () {
        return new Command(commands.removeFirst());
    }

    public void run () {
        while (hasMoreCommands()){
            var command = nexCommand();
            switch (command.type){
                case ADD:
                    var arg2 = stack.pop();
                    var arg1 = stack.pop();
                    stack.push (arg1+arg2);
                    break;
                case SUB:
                    arg2 = stack.pop();
                    arg1 = stack.pop();
                    stack.push (arg1-arg2);
                    break;
                case PUSH:
                    var value = variables.get(command.arg);
                    stack.push(Objects.requireNonNullElseGet(value, () -> Integer.parseInt(command.arg)));
                    break;
                case POP:
                    value = stack.pop();
                    variables.put(command.arg, value);
                    break;
                case PRINT:
                    var arg = stack.pop();
                    System.out.println(arg);
                    break;
            }
        }
    }

}
