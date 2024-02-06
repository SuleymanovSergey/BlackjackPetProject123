package org.blackjack.src.main.java.blackjack.model.card;

public class Card {
    private final Suit suit; // Масть карты
    private final Rank rank; // Значение карты

    // Конструктор для инициализации карты
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Геттер для масти
    public Suit getSuit() {
        return suit;
    }

    // Геттер для значения
    public Rank getRank() {
        return rank;
    }

    // Переопределение метода toString для удобного вывода карты
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
