package tictactoe;

import tictactoe.players.Player;
import tictactoe.players.User;

public class PlayerManager {

    private PlayerManager() {
    }

    public static Player createPlayer(String type) {

        switch (type) {
            case "user":
                return User.create();
            case "easy":
                return AIPlayerManager.createEasyAIPlayer();
            case "medium":
                return AIPlayerManager.createMediumAIPlayer();
            case "hard":
                return AIPlayerManager.createHardAIPlayer();
            default:
                return null;
        }
    }
}