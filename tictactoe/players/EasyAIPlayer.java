package tictactoe.players;

import tictactoe.TicTacToe;
import tictactoe.tools.Point;

import java.util.ArrayList;
import java.util.Random;

public class EasyAIPlayer extends AIPlayer {

    private static AIPlayer instance;

    protected EasyAIPlayer() {
    }

    // Singleton Pattern
    public static AIPlayer create() {
        if (instance == null) {
            instance = new EasyAIPlayer();
        }

        return instance;
    }

    public Point makeMove() {

        System.out.println("Making move level \"easy\"");

        return makeRandomMove();
    }

    protected Point makeRandomMove() {

        ArrayList<Point> emptyCells = TicTacToe.board.getEmptyCells();
        Random random = new Random();

        return emptyCells.get(random.nextInt(emptyCells.size()));
    }
}