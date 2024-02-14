package org.blackjack.src.main.java;

import org.blackjack.src.main.java.blackjack.controller.BlackjackCasino;
import org.blackjack.src.main.java.blackjack.model.bank.Bank;
import org.blackjack.src.main.java.blackjack.model.game.BlackJackGame;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.shuffle.PerfectShuffle;
import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Deck deck = new Deck();
        ShuffleAlgorithm perfectShuffle = new PerfectShuffle();
        Dealer dealer = new Dealer(deck, perfectShuffle); // Создание дилера
        BlackjackCasino casino = new BlackjackCasino(bank, dealer); // Передача дилера в конструктор
        BlackJackPlayer player = new BlackJackPlayer();
        casino.runGame(player, 50); // Запуск игры с одним игроком и начальным балансом 0
            }
}

