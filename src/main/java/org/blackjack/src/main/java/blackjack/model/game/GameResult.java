package org.blackjack.src.main.java.blackjack.model.game;

import org.blackjack.src.main.java.blackjack.model.player.Player;

public class GameResult {
    private Player player;
    private GameResultType result; // Используем перечисление вместо строки

    public GameResult(Player player, GameResultType result) {
        this.player = player;
        this.result = result;
    }

    public Player getPlayer() {
        return player;
    }

    public GameResultType getResult() {
        return result;
    }
}

