package tictactoe.players;

import tictactoe.TicTacToe;
import tictactoe.gameboard.Board;
import tictactoe.gameboard.Seed;
import tictactoe.tools.Point;
import tictactoe.tools.PointWithScore;
import java.util.ArrayList;

public class HardAIPlayer extends AIPlayer {

    private static AIPlayer instance;

    private Seed aiPlayer;
    private Seed opponent;

    private HardAIPlayer() {
    }

    // Singleton Pattern
    public static AIPlayer create() {
        if (instance == null) {
            instance = new HardAIPlayer();
        }

        return instance;
    }

    @Override
    public Point makeMove() {

        System.out.println("Making move level \"hard\"");

        aiPlayer = TicTacToe.board.getCurrentPlayer();

        opponent = aiPlayer == Seed.CROSS ? Seed.NOUGHT : Seed.CROSS;

        return minimax(TicTacToe.board, true).getPoint();
    }

    private PointWithScore minimax(Board newBoard, boolean isMax) {
        ArrayList<Point> emptyCells = newBoard.getEmptyCells();

        if (newBoard.findWinner() == opponent) {
            return new PointWithScore(-10);
        } else if (newBoard.findWinner() == aiPlayer) {
            return new PointWithScore(10);
        } else if (emptyCells.size() == 0) {
            return new PointWithScore(0);
        }

        ArrayList<PointWithScore> moves = new ArrayList<>();

        for (int i = 0; i < emptyCells.size(); i++) {
            PointWithScore move = new PointWithScore();

            newBoard.setMove(emptyCells.get(i));
            move.setPoint(emptyCells.get(i));

            if (isMax) {
                move.setScore(minimax(newBoard, false).getScore());
            } else {
                move.setScore(minimax(newBoard, true).getScore());
            }

            newBoard.setCellToEmpty(move.getPoint());
            moves.add(move);
        }

        int bestMoveIndex = -1;
        int bestScore;
        if (isMax) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() > bestScore) {
                    bestScore = moves.get(i).getScore();
                    bestMoveIndex = i;
                }
            }

        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() < bestScore) {
                    bestScore = moves.get(i).getScore();
                    bestMoveIndex = i;
                }
            }
        }

        return moves.get(bestMoveIndex);
    }
}