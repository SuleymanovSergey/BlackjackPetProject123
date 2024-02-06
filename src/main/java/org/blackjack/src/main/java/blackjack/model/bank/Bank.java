package org.blackjack.src.main.java.blackjack.model.bank;


import org.blackjack.src.main.java.blackjack.model.player.Player;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Player, BankAccount> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    // Регистрируем игрока в банке с начальным балансом
    public void registerPlayer(Player player, int initialBalance) {
        accounts.put(player, new BankAccount(initialBalance));
    }

    // Обработка ставки игрока
    public boolean placeBet(Player player, int betAmount) {
        BankAccount account = accounts.get(player);
        if (account != null && account.withdraw(betAmount)) {
            // Ставка успешно снята с баланса игрока
            return true;
        }
        // Недостаточно средств для ставки
        return false;
    }

    // Выплата выигрыша игроку
    public void payoutWin(Player player, int winAmount) {
        BankAccount account = accounts.get(player);
        if (account != null) {
            account.deposit(winAmount);
        }
    }

    // Возврат ставки игроку в случае ничьи
    public void refundBet(Player player, int betAmount) {
        payoutWin(player, betAmount); // Просто возвращаем ставку, как выигрыш
    }

    // Получение баланса игрока
    public int getBalance(Player player) {
        BankAccount account = accounts.get(player);
        return account != null ? account.getBalance() : 0;
    }
}
