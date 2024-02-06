package org.blackjack.src.main.java.blackjack.model.card;

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
}
