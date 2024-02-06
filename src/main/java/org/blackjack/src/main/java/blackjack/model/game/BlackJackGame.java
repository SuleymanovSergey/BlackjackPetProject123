package org.blackjack.src.main.java.blackjack.model.game;



import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.player.Player;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private Deck deck;
    private Dealer dealer;
    private List<Player> players;

    public BlackJackGame(Dealer dealer) {
        this.dealer = dealer;
        this.deck = dealer.getDeck();
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }


    public void startNewGame() {
        if (dealer == null) {
            System.out.println("Dealer is null");
        }
        deck = dealer.getDeck();
        dealer.resetHand();

        // Раздаем карты
        for (Player player : players) {
            player.resetHand();
            player.addCard(deck.dealCard());
            player.addCard(deck.dealCard());
        }

        dealer.addCard(deck.dealCard());

        // Процесс игры
        for (Player player : players) {
            while (player.shouldHit()) {
                player.addCard(deck.dealCard());
            }
        }

        // Ход дилера
        while (dealer.shouldHit()) {
            dealer.addCard(deck.dealCard());
        }
    }

    // Возвращает список результатов игры
    public List<GameResult> getResults() {
        List<GameResult> results = new ArrayList<>();
        int dealerScore = dealer.getHandValue();
        for (Player player : players) {
            int playerScore = player.getHandValue();
            if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
                results.add(new GameResult(player, "lose"));
            } else if (playerScore == dealerScore) {
                results.add(new GameResult(player, "push"));
            } else {
                results.add(new GameResult(player, "win"));
            }
        }
        return results;
    }
}
