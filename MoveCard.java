public class MoveCard extends Card {

    private int amount;

    public MoveCard(String text, int amount) {
        super(text);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void execute(Player player, Board board) {
        if (amount > 0) {
            System.out.println(player.getName() + " moved " + amount + " steps.");
        } else {
            System.out.println(player.getName() + " moved back " + amount + " steps.");
        }
        board.move(player, amount, false);
    }
}
