package org.blackjack.src.main.java.blackjack.model.game;

import org.blackjack.src.main.java.blackjack.model.player.Player;

public class GameResult {
    private Player player;
    private String result; // "win", "lose", "push"

    public GameResult(Player player, String result) {
        this.player = player;
        this.result = result;
    }

    public Player getPlayer() {
        return player;
    }

    public String getResult() {
        return result;
    }
}
