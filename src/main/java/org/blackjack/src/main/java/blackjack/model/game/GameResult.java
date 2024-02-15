package org.blackjack.src.main.java.blackjack.model.game;

import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;

public class GameResult {
    private BlackJackPlayer player; // Изменено с Player на BlackJackPlayer
    private GameResultType result;

    public GameResult(BlackJackPlayer player, GameResultType result) {
        this.player = player;
        this.result = result;
    }

    public BlackJackPlayer getPlayer() {
        return this.player;
    }

    public GameResultType getResult() {
        return result;
    }
}


