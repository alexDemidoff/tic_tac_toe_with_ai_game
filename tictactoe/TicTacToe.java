package tictactoe;

import tictactoe.gameboard.Board;
import tictactoe.gameboard.GameState;
import tictactoe.players.Player;
import tictactoe.tools.CommandInterpreter;

public class TicTacToe {
    public static Board board = Board.create(3);
    public static CommandInterpreter commandInterpreter = CommandInterpreter.createCommandInterpreter();

    public static void run() {

        commandInterpreter.readCommand();

        while (!commandInterpreter.isExit()) {
            Player firstPlayer = PlayerManager.createPlayer(commandInterpreter.getFirstPlayerType());
            Player secondPlayer = PlayerManager.createPlayer(commandInterpreter.getSecondPlayerType());
            board.draw();

            while (board.getStateOfGame() == GameState.PLAYING) {
                board.setMove(firstPlayer.makeMove());
                board.draw();

                if (board.getStateOfGame() == GameState.PLAYING) {
                    board.setMove(secondPlayer.makeMove());
                    board.draw();
                }
            }

            board.printStateOfGame();

            System.out.println();
            commandInterpreter.readCommand();
            board.reset();
        }
    }
}