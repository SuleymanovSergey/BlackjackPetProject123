package org.blackjack.src.main.java.blackjack.model.player;


import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

public class Dealer extends Player {
    private final Deck deck;
    private ShuffleAlgorithm shuffleAlgorithm;

    public Dealer(Deck deck, ShuffleAlgorithm algorithm) {
        this.deck = deck;
        this.shuffleAlgorithm = algorithm;
    }

    public void shuffleDeck() {
        shuffleAlgorithm.shuffle(deck.getCards());
    }

    @Override
    public boolean shouldHit() {
        return getHandValue() < 17;
    }

    public void dealCardToPlayer(Player player) {
        player.addCard(deck.dealCard());
    }

    public Deck getDeck() {
        return deck;
    }
}

