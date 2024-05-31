public class ConservativePlayer extends Player {

    public ConservativePlayer(String name, int position) {
        super(name, position);
    }

    public boolean wantsToBuy(Property property) {
        if (getMoney() >= property.getPrice() * 2) {
            return true;
        }
        return false;
    }
}
