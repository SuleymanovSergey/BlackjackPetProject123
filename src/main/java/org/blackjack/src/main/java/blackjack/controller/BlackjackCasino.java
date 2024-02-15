package org.blackjack.src.main.java.blackjack.controller;



import org.blackjack.src.main.java.blackjack.model.bank.Bank;
import org.blackjack.src.main.java.blackjack.model.bank.BankAccount;
import org.blackjack.src.main.java.blackjack.model.game.BlackJackGame;
import org.blackjack.src.main.java.blackjack.model.game.GameResult;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;
import org.blackjack.src.main.java.blackjack.model.shuffle.PerfectShuffle;
import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlackjackCasino {
    private Map<String, Integer> bets; // Ставки игроков, где ключ - имя игрока
    private Bank bank;
    private Dealer dealer;
    private List<BlackJackGame> games;

    public BlackjackCasino(Bank bank, Dealer dealer) {
        this.bank = bank;
        this.dealer = dealer;
        this.games = new ArrayList<>();
        this.bets = new HashMap<>(); // Инициализация карты ставок
    }

    public void registerPlayer(BlackJackPlayer player, int initialBalance) {
        BankAccount account = new BankAccount(0);
        player.setAccount(account); // Устанавливаем связанный банковский счёт для игрока
        bank.registerAccount(account); // Регистрируем счёт в банке
    }

    public void runGame(List<BlackJackPlayer> players, int initialBalance) {
        for (BlackJackPlayer player : players) {
            registerPlayer(player, initialBalance);
            requestDepositForPlayer(player); // Предполагается, что этот метод запрашивает депозит у игрока и вносит его на счет в банке
        }

        System.out.println("Start of a new game.");
        dealer.shuffleDeck();

        BlackJackGame game = new BlackJackGame(dealer);
        for (BlackJackPlayer player : players) {
            game.addPlayer(player);
        }
        makeBets(players); // Предполагается, что этот метод обрабатывает ставки игроков
        game.startNewGame();

        resolveBets(game); // Обработка ставок после игры
        games.add(game);

        System.out.println("Game is over.");
        for (BlackJackPlayer player : players) {
            // Получаем банковский аккаунт игрока
            BankAccount playerAccount = player.getAccount();
            // Запрашиваем баланс игрока через банк
            int playerBalance = bank.getBalance(playerAccount);
            System.out.println("Player balance for " + player.getName() + ": " + playerBalance);
        }
    }


    // Метод для размещения ставок
    public void makeBets(List<BlackJackPlayer> players) {
        Scanner scanner = new Scanner(System.in); // Используется для ввода с консоли

        for (BlackJackPlayer player : players) {
            int bet = 0;
            boolean validBet = false;

            while (!validBet) {
                System.out.println("Player " + player.getName() + ", enter your bet amount:");

                if (scanner.hasNextInt()) {
                    bet = scanner.nextInt();
                    // Получаем банковский аккаунт игрока
                    BankAccount playerAccount = player.getAccount();
                    // Запрашиваем текущий баланс у банка
                    int playerBalance = bank.getBalance(playerAccount);

                    if (bet > 0 && bet <= playerBalance) {
                        validBet = true; // Ставка допустима
                    } else {
                        System.out.println("Invalid bet. Your bet must be more than 0 and less than or equal to your balance.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Очищаем некорректный ввод
                }
            }
            bets.put(player.getName(), bet);
            bank.placeBet(player.getAccount(), bet);
        }
    }

    private void resolveBets(BlackJackGame game) {
        List<GameResult> results = game.getResults();
        for (GameResult result : results) {
            BlackJackPlayer player = result.getPlayer();
            int betAmount = bets.get(player.getName()); // Получаем ставку игрока по имени
            switch (result.getResult()) {
                case WIN:
                    bank.payoutWin(player.getAccount(), (betAmount * 2));
                    System.out.println(player.getName() + " wins");
                    break;
                case LOSE:
                    System.out.println(player.getName() + " loses");
                    break;
                case PUSH:
                    bank.refundBet(player.getAccount(), betAmount);
                    System.out.println("Draw for " + player.getName());
                    break;
                default:
                    break;
            }
        }
    }

    private void requestDepositForPlayer(BlackJackPlayer player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much would you like to deposit, " + player.getName() + "?");
        int depositAmount = scanner.nextInt();
        while (depositAmount <= 0) {
            System.out.println("Deposit positive amount. Please enter a valid amount:");
            depositAmount = scanner.nextInt();
        }

        player.getAccount().deposit(depositAmount);
        System.out.println("Deposit of " + depositAmount + " has been added to " + player.getName() + "'s account.");
    }
}




