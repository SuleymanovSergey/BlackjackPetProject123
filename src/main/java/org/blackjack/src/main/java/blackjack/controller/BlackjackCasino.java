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

public class BlackjackCasino {
    int betAmount;
    private Bank bank;
    private Dealer dealer;
    private List<BlackJackGame> games;

    public BlackjackCasino(Bank bank, Dealer dealer) {
        this.bank = bank;
        this.dealer = dealer;
        this.games = new ArrayList<>();
    }


    public void registerPlayer(BlackJackPlayer player, int initialBalance) {
        BankAccount account = new BankAccount(initialBalance);
        player.setAccount(account); // Устанавливаем связанный банковский счёт для игрока
        bank.registerAccount(account); // Регистрируем счёт в банке
    }

    public void runGame(BlackJackPlayer player, int initialBalance) {
        registerPlayer(player, initialBalance);

        System.out.println("Start of a new game.");
        dealer.shuffleDeck();

        BlackJackGame game = new BlackJackGame(dealer);
        game.addPlayer(player);
        makeBets(List.of(player));
        game.startNewGame();

        resolveBets(game);
        games.add(game);

        System.out.println("Game is over.");
        System.out.println("Player balance: " + player.getBalance());
    }


    private void resolveBets(BlackJackGame game) {
        List<GameResult> results = game.getResults();
        for (GameResult result : results) {
            BlackJackPlayer player = result.getPlayer(); // Теперь это безопасно
            switch (result.getResult()) {
                case WIN:
                    // Используем BankAccount игрока для выплаты выигрыша
                    bank.payoutWin(player.getAccount(), (betAmount*2));
                    System.out.println("You win");// Пример выигрыша
                    break;
                case LOSE:
                    // Ставка уже учтена
                    System.out.println("You lose");
                    break;
                case PUSH:
                    // Используем BankAccount игрока для возврата ставки
                    // Ошибка в комментарии исправлена на player.getAccount()
                    bank.refundBet(player.getAccount(), betAmount);
                    System.out.println("Draw");
                    break;
                default:
                    break;
            }
        }
    }

    private void makeBets(List<BlackJackPlayer> players) {
        System.out.println("You can make bets.");
        players.forEach(player -> {
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter bet size");
                betAmount = scanner.nextInt();
            } while (bank.placeBet(player.getAccount(),betAmount));
        });
    }
}


