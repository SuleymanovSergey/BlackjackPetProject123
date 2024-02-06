package org.blackjack.src.main.java.blackjack.model.player;



import org.blackjack.src.main.java.blackjack.model.card.Card;
import org.blackjack.src.main.java.blackjack.model.card.CardBlackjackValue;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected List<Card> hand = new ArrayList<>();

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            int cardValue = CardBlackjackValue.getValue(card.getRank());
            if (cardValue == 11) {
                aces += 1;
            }
            value += cardValue;
        }
        while (value > 21 && aces > 0) {
            value -= 10; // Считаем туз за 1
            aces -= 1;
        }
        return value;
    }

    public abstract boolean shouldHit();

    public void resetHand() {
        hand.clear();
    }
}

