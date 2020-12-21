package tictactoe.players;

import tictactoe.TicTacToe;
import tictactoe.exceptions.CellIsOccupiedException;
import tictactoe.exceptions.PointOutOfBoardBoundsException;
import tictactoe.tools.Point;

import java.util.Scanner;

public class User extends Player {

    private static User instance;

    private User() {
    }

    // Singleton Pattern
    public static User create() {
        if (instance == null) {
            instance = new User();
        }

        return instance;
    }

    public Point makeMove() {

        return getUserInput();
    }

    private Point getUserInput() {

        Scanner scanner = new Scanner(System.in);
        boolean isCorrect = false;
        Point userPoint = null;

        do {
            System.out.print("Enter the coordinates: ");
            String[] userInput = scanner.nextLine().trim().split(" ");

            try {
                // Throws NumberFormatException and IndexOutOfBoundsException
                userPoint = Point.createXY(Integer.parseInt(userInput[0]), Integer.parseInt(userInput[1]));

                if (!(userPoint.x >= TicTacToe.board.LEFT_BOUND &&
                        userPoint.x <= TicTacToe.board.RIGHT_BOUND &&
                        userPoint.y >= TicTacToe.board.BOTTOM_BOUND &&
                        userPoint.y <= TicTacToe.board.TOP_BOUND)) {
                    throw new PointOutOfBoardBoundsException();
                }

                if (!TicTacToe.board.isEmpty(Point.convertToLocal(userPoint))) {
                    throw new CellIsOccupiedException();
                }

                isCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (PointOutOfBoardBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You should enter two numbers!");
            } catch (CellIsOccupiedException e) {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (!isCorrect);

        return Point.convertToLocal(userPoint);
    }
}