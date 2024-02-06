package org.blackjack.src.main.java.blackjack.controller;



import org.blackjack.src.main.java.blackjack.model.bank.Bank;
import org.blackjack.src.main.java.blackjack.model.game.BlackJackGame;
import org.blackjack.src.main.java.blackjack.model.game.GameResult;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackCasino {
    private Bank bank;
    private Dealer dealer;

    private List<BlackJackGame> games;

    public BlackjackCasino(Dealer dealer) {
        this.bank = new Bank();
        this.games = new ArrayList<>();
        this.dealer = dealer;
    }

    public void registerPlayer(Player player, int initialBalance) {
        bank.registerPlayer(player, initialBalance);
    }

    public void startGame(List<Player> players) {
        BlackJackGame game = new BlackJackGame(dealer);
        players.forEach(player -> {
            game.addPlayer(player);
            bank.placeBet(player, 10); // Пример ставки
        });
        game.startNewGame();
        resolveBets(game);
        games.add(game);
    }

    private void resolveBets(BlackJackGame game) {
        List<GameResult> results = game.getResults();
        for (GameResult result : results) {
            Player player = result.getPlayer();
            switch (result.getResult()) {
                case "win":
                    bank.payoutWin(player, 20); // Пример выигрыша
                    break;
                case "lose":
                    // Ставка уже учтена
                    break;
                case "push":
                    bank.refundBet(player, 10);
                    break;
            }
        }
    }
}

