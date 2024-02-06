package org.blackjack.src.main.java;

import org.blackjack.src.main.java.blackjack.controller.BlackjackCasino;
import org.blackjack.src.main.java.blackjack.model.game.BlackJackGame;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.shuffle.PerfectShuffle;
import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Deck deck = new Deck();
        ShuffleAlgorithm perfectShuffle = new PerfectShuffle();


        Dealer dealer = new Dealer(deck, perfectShuffle);
        BlackjackCasino casino = new BlackjackCasino(dealer);
        BlackJackPlayer player = new BlackJackPlayer(1000);


        casino.registerPlayer(player, 1000);


        System.out.println("Начало новой игры в блэкджек.");
        dealer.shuffleDeck();
        BlackJackGame game = new BlackJackGame(dealer);
        game.addPlayer(player);



        game.startNewGame();


        while (player.shouldHit()) {
            System.out.println("Игрок решает взять карту.");
            dealer.dealCardToPlayer(player);
        }


        while (dealer.shouldHit()) {
            System.out.println("Дилер берет карту для себя.");
            dealer.addCard(deck.dealCard());
        }


        casino.startGame(List.of(player));


        System.out.println("Игра окончена.");
        System.out.println("Баланс игрока: " + player.getBalance());
    }
}
