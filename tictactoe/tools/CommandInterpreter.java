package tictactoe.tools;

import tictactoe.exceptions.BadParametersException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommandInterpreter {
    private final Set<String> parameters = new HashSet<>();
    private String firstPlayerType;
    private String secondPlayerType;
    private boolean isExit;

    private CommandInterpreter() {
        Collections.addAll(parameters, "easy", "user", "medium", "hard");
    }

    public static CommandInterpreter createCommandInterpreter() {
        return new CommandInterpreter();
    }

    public String getFirstPlayerType() {
        return firstPlayerType;
    }

    public String getSecondPlayerType() {
        return secondPlayerType;
    }

    public boolean isExit() {
        return isExit;
    }

    public void readCommand() {

        Scanner scanner = new Scanner(System.in);
        boolean isCorrect = false;

        do {
            System.out.print("Input command: ");
            String[] userInput = scanner.nextLine().split(" ");

            try {
                if (userInput.length == 1 && userInput[0].equals("exit")) {
                    isExit = true;
                } else if (userInput.length == 3) {
                    if (userInput[0].equals("start") &&
                            parameters.contains(userInput[1]) &&
                            parameters.contains(userInput[2])) {
                        firstPlayerType = userInput[1];
                        secondPlayerType = userInput[2];
                    } else {
                        throw new BadParametersException();
                    }
                } else {
                    throw new BadParametersException();
                }

                isCorrect = true;

            } catch (BadParametersException e) {
                System.out.println("Bad parameters!");
            }

        } while (!isCorrect);
    }
}