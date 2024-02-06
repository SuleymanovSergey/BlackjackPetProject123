package org.blackjack.src.main.java.blackjack.model.player;

public class BlackJackPlayer extends Player {
    private int balance;
    private int currentBet;

    public BlackJackPlayer(int initialBalance) {
        this.balance = initialBalance;
    }

    public boolean placeBet(int amount) {
        if (amount > balance) {
            return false; // Недостаточно средств для ставки
        }
        currentBet = amount;
        balance -= amount;
        return true;
    }

    @Override
    public boolean shouldHit() {
        return getHandValue() < 21;
    }

    public void win() {
        balance += currentBet * 2; // Выигрыш удваивает ставку
        currentBet = 0;
    }

    public void lose() {
        currentBet = 0; // Ставка сгорает
    }

    public void push() {
        balance += currentBet; // Возврат ставки
        currentBet = 0;
    }

    public int getBalance() {
        return balance;
    }
}
