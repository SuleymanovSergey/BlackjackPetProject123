package org.blackjack.src.main.java.blackjack.model.card;

import java.util.List;

public class CardBlackjackValue {
    public static int getValue(Rank rank) {
        return switch (rank) {
            case TWO    -> 2;
            case THREE  -> 3;
            case FOUR   -> 4;
            case FIVE   -> 5;
            case SIX    -> 6;
            case SEVEN  -> 7;
            case EIGHT  -> 8;
            case NINE   -> 9;
            case TEN,
                 JACK,
                 QUEEN,
                 KING   -> 10;
            case ACE    -> 11;
            default -> throw new IllegalArgumentException("Unknown rank: " + rank);
        };
    }

    public static int getHandValue(List<Card> hand) {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            int cardValue = getValue(card.getRank()); // Убедитесь, что метод getValue статический
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
}
