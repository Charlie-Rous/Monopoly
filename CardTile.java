public class CardTile extends Tile {

    private Deck deck;

    public CardTile(String name, Deck deck) {
        super(name);
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.drawCard();
    }

}
