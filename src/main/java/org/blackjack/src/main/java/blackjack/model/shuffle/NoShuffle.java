package org.blackjack.src.main.java.blackjack.model.shuffle;

import org.blackjack.src.main.java.blackjack.model.card.Card;
import java.util.List;

public class NoShuffle implements ShuffleAlgorithm {
    @Override
    public void shuffle(List<Card> deck) {
        // Не выполняет перемешивание
    }
}