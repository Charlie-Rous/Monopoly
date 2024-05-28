public class MoneyCard extends Card {

    private int amount;

    public MoneyCard(String text, int amount) {
        super(text);
        this.amount = amount;
    }

    public void execute(Player player, Board board) {
        player.addMoney(amount);
        if (amount > 0) {
            System.out.println(player.getName() + " received " + "$" + amount + ".");
        } else {
            FreeParking.addFunds(-1 * amount);
            System.out.println(player.getName() + " payed $" + amount + " .");
        }
    }

    public int getAmount() {
        return amount;
    }
}
