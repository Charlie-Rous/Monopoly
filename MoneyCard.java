public class MoneyCard extends Card {

    private int amount;

    public MoneyCard(String text, int amount) {
        super(text);
        this.amount = amount;
    }

    public void execute(Player player, Board board) {
        
        if (amount > 0) {
            player.addMoney(amount);
            System.out.println(player.getName() + " received " + "$" + amount + ".");
        } else {
            System.out.println(player.getName() + " payed $" + -1 * amount + " .");
            FreeParking.addFunds(player.subtractMoney(-1 * amount));
            
        }
    }

    public int getAmount() {
        return amount;
    }
}
