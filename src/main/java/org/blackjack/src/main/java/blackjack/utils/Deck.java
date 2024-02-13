package org.blackjack.src.main.java.blackjack.utils;



import org.blackjack.src.main.java.blackjack.model.card.Card;
import org.blackjack.src.main.java.blackjack.model.card.Rank;
import org.blackjack.src.main.java.blackjack.model.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initDeck();
    }

    public void initDeck() {
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    public Card remove() {
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

