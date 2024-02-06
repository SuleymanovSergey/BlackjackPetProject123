package org.blackjack.src.main.java.blackjack.model.shuffle;

import org.blackjack.src.main.java.blackjack.model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class PerfectShuffle implements ShuffleAlgorithm {
    @Override
    public void shuffle(List<Card> deck) {
        int halfSize = deck.size() / 2;
        List<Card> firstHalf = new ArrayList<>(deck.subList(0, halfSize));
        List<Card> secondHalf = new ArrayList<>(deck.subList(halfSize, deck.size()));

        for (int i = 0; i < halfSize; i++) {
            deck.set(2 * i, firstHalf.get(i));
            deck.set(2 * i + 1, secondHalf.get(i));
        }
    }
}