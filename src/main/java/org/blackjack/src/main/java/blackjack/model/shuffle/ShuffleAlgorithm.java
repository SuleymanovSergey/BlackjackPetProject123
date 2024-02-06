package org.blackjack.src.main.java.blackjack.model.shuffle;

import org.blackjack.src.main.java.blackjack.model.card.Card;

import java.util.List;

public interface ShuffleAlgorithm {
    void shuffle(List<Card> deck);
}