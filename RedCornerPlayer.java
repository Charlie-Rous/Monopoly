public class RedCornerPlayer extends Player {

    public RedCornerPlayer(String name, int position) {
        super(name, position);
    }

    public boolean wantsToBuy(Property property) {
        if (property instanceof RealEstate) {
            RealEstate realEstate = (RealEstate) property;
            if (realEstate.getMonopoly().equals("Red")
                    || realEstate.getMonopoly().equals("Orange") && getMoney() >= property.getPrice()) {
                return true;
            }
        } else if (getMoney() >= property.getPrice()) {
            return true;
        }
        return false;
    }
}
