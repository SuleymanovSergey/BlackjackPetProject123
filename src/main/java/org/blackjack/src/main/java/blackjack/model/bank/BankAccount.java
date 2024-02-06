package org.blackjack.src.main.java.blackjack.model.bank;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }
}

