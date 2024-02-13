package org.blackjack.src.main.java.blackjack.model.player;


import lombok.EqualsAndHashCode;
import org.blackjack.src.main.java.blackjack.model.card.Card;
import org.blackjack.src.main.java.blackjack.model.card.CardBlackjackValue;
import org.blackjack.src.main.java.blackjack.model.shuffle.ShuffleAlgorithm;
import org.blackjack.src.main.java.blackjack.utils.Deck;

import java.util.List;


@EqualsAndHashCode(callSuper=false)
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

    private int getHandValue() {
        return CardBlackjackValue.getHandValue(this.getHand());
    }

    public void dealCardToPlayer(Player player) {
        player.addCard(dealCard());
    }

    public int getDeckSize() {
        return deck.size();
    }

    public List<Card> getHand() {
        return hand;
    }

    public Card dealCard() {
        if (!deck.isEmpty()) {
            return deck.remove();
        } else {
            throw new IllegalStateException("No more cards in the deck");
        }
    }

}

