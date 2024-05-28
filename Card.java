public class Card {
    private String text;

    public Card(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void execute(Player player, Board board) {
        System.out.println("Executing card: " + text);
    }


}
