package org.blackjack.src.main.java.blackjack.model.bank;

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
            account.withdraw(betAmount);
            System.out.println("Your bet is accepted");
            return false;
        }
        System.out.println("Your bet isn't accepted");
        return true;
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



