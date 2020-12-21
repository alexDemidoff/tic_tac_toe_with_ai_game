package tictactoe.players;

import tictactoe.TicTacToe;
import tictactoe.gameboard.Seed;
import tictactoe.tools.Point;

public class MediumAIPlayer extends EasyAIPlayer {

    private static AIPlayer instance;

    private MediumAIPlayer() {
    }

    // Singleton Pattern
    public static AIPlayer create() {
        if (instance == null) {
            instance = new MediumAIPlayer();
        }

        return instance;
    }

    @Override
    public Point makeMove() {

        System.out.println("Making move level \"medium\"");

        Point empty = findEmptyToMakeSequenceOfThree();
        return empty != null ? empty : makeRandomMove();
    }

    private Point findEmptyToMakeSequenceOfThree() {

        Seed aiPlayer = TicTacToe.board.getAmountOf(Seed.CROSS) <=
                TicTacToe.board.getAmountOf(Seed.NOUGHT) ? Seed.CROSS : Seed.NOUGHT;

        Point result = TicTacToe.board.findEmptyFor(aiPlayer);

        return result != null ? result : TicTacToe.board.findEmptyFor(aiPlayer == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS);
    }
}