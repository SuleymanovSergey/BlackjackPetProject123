package org.blackjack.src.main.java;

import org.blackjack.src.main.java.blackjack.controller.BlackjackCasino;
import org.blackjack.src.main.java.blackjack.model.bank.Bank;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.shuffle.PerfectShuffle;
import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        Deck deck = new Deck();
        ShuffleAlgorithm perfectShuffle = new PerfectShuffle();
        Dealer dealer = new Dealer(deck, perfectShuffle);
        BlackjackCasino casino = new BlackjackCasino(bank, dealer);

        System.out.println("Enter the number of players:");
        int numberOfPlayers = scanner.nextInt();

        List<BlackJackPlayer> players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            scanner.nextLine();
            System.out.println("Enter name for Player " + i + ":");
            String playerName = scanner.nextLine();
            BlackJackPlayer player = new BlackJackPlayer(playerName);
            players.add(player);
        }

        int initialBalance = 0; // Эта переменная не используется. Баланс устанавливается через депозит
        casino.runGame(players, initialBalance);

        scanner.close();
    }
}



