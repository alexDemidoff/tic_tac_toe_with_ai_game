package tictactoe;

import tictactoe.players.AIPlayer;
import tictactoe.players.EasyAIPlayer;
import tictactoe.players.HardAIPlayer;
import tictactoe.players.MediumAIPlayer;

public class AIPlayerManager {

    private AIPlayerManager() {
    }

    public static AIPlayer createEasyAIPlayer() {
        return EasyAIPlayer.create();
    }

    public static AIPlayer createMediumAIPlayer() {
        return MediumAIPlayer.create();
    }

    public static AIPlayer createHardAIPlayer() {
        return HardAIPlayer.create();
    }
}