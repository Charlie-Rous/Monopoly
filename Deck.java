import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public Card drawCard() {
        return cards.remove(0);

    }

    public void addCard(Card c) {
        cards.add(c);
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int rand = (int) (Math.random() * cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(rand));
            cards.set(rand, temp);
        }
    }
}
