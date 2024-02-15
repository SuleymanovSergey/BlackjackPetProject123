package org.blackjack.src.main.java.blackjack.model.player;

import lombok.EqualsAndHashCode;
import org.blackjack.src.main.java.blackjack.model.bank.BankAccount;
import org.blackjack.src.main.java.blackjack.model.card.CardBlackjackValue;

import java.util.Scanner;

@EqualsAndHashCode(callSuper=false)
public class BlackJackPlayer extends Player {
    private BankAccount account;
    private String name;

    public BlackJackPlayer(String name) {
        this.name = name;
    }

    public BankAccount getAccount() {
        return account;
    }

    // Метод для инициализации BankAccount
    public void setAccount(BankAccount account) {
        this.account = account;
    }

    @Override
    public boolean shouldHit() {
        Scanner scanner = new Scanner(System.in);
        String answer;
        while (true) {
            System.out.println("Do you want to hit? Type 'yes' to hit, type 'no' to stop");
            answer = scanner.next();
            if (answer.equalsIgnoreCase("yes")) {
                return true;
            } else if (answer.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please type 'yes' or 'no'.");
            }
        }
    }

    private int getHandValue() {
        return CardBlackjackValue.getHandValue(this.getHand());
    }

    public String getName() {
        return name;
    }
}
