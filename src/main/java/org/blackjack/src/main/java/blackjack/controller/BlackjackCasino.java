package org.blackjack.src.main.java.blackjack.controller;



import org.blackjack.src.main.java.blackjack.model.bank.Bank;
import org.blackjack.src.main.java.blackjack.model.bank.BankAccount;
import org.blackjack.src.main.java.blackjack.model.game.BlackJackGame;
import org.blackjack.src.main.java.blackjack.model.game.GameResult;
import org.blackjack.src.main.java.blackjack.model.player.BlackJackPlayer;
import org.blackjack.src.main.java.blackjack.model.player.Dealer;

import java.util.ArrayList;
import java.util.List;

public class BlackjackCasino {
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

    public void startGame(List<BlackJackPlayer> players) {
        BlackJackGame game = new BlackJackGame(dealer);
        players.forEach(player -> {
            game.addPlayer(player);
            // Используем BankAccount игрока для ставки
            bank.placeBet(player.getAccount(), 10); // Пример ставки
        });
        game.startNewGame();
        resolveBets(game);
        games.add(game);
    }

    private void resolveBets(BlackJackGame game) {
        List<GameResult> results = game.getResults();
        for (GameResult result : results) {
            BlackJackPlayer player = result.getPlayer(); // Теперь это безопасно
            switch (result.getResult()) {
                case WIN:
                    // Используем BankAccount игрока для выплаты выигрыша
                    bank.payoutWin(player.getAccount(), 20); // Пример выигрыша
                    break;
                case LOSE:
                    // Ставка уже учтена
                    break;
                case PUSH:
                    // Используем BankAccount игрока для возврата ставки
                    // Ошибка в комментарии исправлена на player.getAccount()
                    bank.refundBet(player.getAccount(), 10); // Предполагаем, что это правильный метод
                    break;
                default:
                    break;
            }
        }
    }
}


