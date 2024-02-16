package org.blackjack.src.main.java.blackjack.model.game;

import org.blackjack.src.main.java.blackjack.model.card.Card;
import org.blackjack.src.main.java.blackjack.model.card.CardBlackjackValue;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.player.Player;


import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private Dealer dealer;
    private List<BlackJackPlayer> players;
    private boolean isGameOver = false;

    public BlackJackGame(Dealer dealer) {
        this.dealer = dealer;
        this.players = new ArrayList<>();
    }

    public void addPlayer(BlackJackPlayer player) {
        players.add(player);
    }


    public void startNewGame() {

        dealer.resetHand();

        // Раздаем карты
        for (Player player : players) {
            player.resetHand();
            System.out.println(player.getName() + ", gets hand.");
            player.addCard(dealer.dealCard());
            player.addCard(dealer.dealCard());
        }

        System.out.println("Dealer gets hand.");
        dealer.addCard(dealer.dealCard());
        dealer.addCard(dealer.dealCard());

        // Процесс игры
        while (!isGameOver) {
            if (dealer.getDeckSize() == 0) {
                isGameOver = true;
            }

            if (CardBlackjackValue.getHandValue(dealer.getHand()) >= 21) {
                isGameOver = true;
            }

            for (Player player : players) {
                if (CardBlackjackValue.getHandValue(player.getHand()) >= 21) {
                    isGameOver = true;
                }
            }

            for (Player player : players) {
                if (player.shouldHit()) {
                    System.out.println(player.getName() + ", wants to hit.");
                    Card newCard = dealer.dealCard(); // Взять карту у дилера
                    player.addCard(newCard); // Добавить карту в руку игрока
                }
            }

            // Ход дилера
            if (dealer.shouldHit()) {
                System.out.println("Dealer wants to hit.");
                Card newCard = dealer.dealCard(); // Взять карту
                dealer.addCard(newCard); // Добавить карту к дилеру
            }
        }
    }

    // Возвращает список результатов игры
    public List<GameResult> getResults() {
        List<GameResult> results = new ArrayList<>();

        CardBlackjackValue CardBlackjackValue = new CardBlackjackValue();
        int dealerScore = CardBlackjackValue.getHandValue(dealer.getHand());

        for (BlackJackPlayer player : players) {
            int playerScore = CardBlackjackValue.getHandValue(player.getHand());
            if (playerScore > 21 || (dealerScore <= 21 && dealerScore > playerScore)) {
                results.add(new GameResult(player, GameResultType.LOSE));
            } else if (playerScore == dealerScore) {
                results.add(new GameResult(player, GameResultType.PUSH));
            } else {
                results.add(new GameResult(player, GameResultType.WIN));
            }
        }
        return results;
    }
}

