package org.blackjack.src.main.java.blackjack.model.bank;


import org.blackjack.src.main.java.blackjack.model.player.Player;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<BankAccount, Integer> accounts; // Изменено на хранение BankAccount напрямую

    public Bank() {
        this.accounts = new HashMap<>();
    }

    // Теперь метод принимает объект BankAccount напрямую
    public void registerAccount(BankAccount account) {
        accounts.put(account, account.getBalance());
    }

    // Обработка ставки с учетом BankAccount
    public boolean placeBet(BankAccount account, int betAmount) {
        if (accounts.containsKey(account) && account.withdraw(betAmount)) {
            // Ставка успешно снята с баланса
            return true;
        }
        // Недостаточно средств для ставки
        return false;
    }

    // Выплата выигрыша с учетом BankAccount
    public void payoutWin(BankAccount account, int winAmount) {
        if (accounts.containsKey(account)) {
            account.deposit(winAmount);
        }
    }

    // Возврат ставки в случае ничьи
    public void refundBet(BankAccount account, int betAmount) {
        payoutWin(account, betAmount); // Просто возвращаем ставку, как выигрыш
    }

    // Получение баланса с учетом BankAccount
    public int getBalance(BankAccount account) {
        return account != null ? account.getBalance() : 0;
    }
}



