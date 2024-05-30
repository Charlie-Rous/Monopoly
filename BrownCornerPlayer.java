public class BrownCornerPlayer extends Player{
    
    public BrownCornerPlayer(String name, int position) {
        super(name, position);
    }

    public boolean wantsToBuy(Property property) {
        if (property instanceof RealEstate) {
            RealEstate realEstate = (RealEstate) property;
            if (realEstate.getMonopoly().equals("Brown") || realEstate.getMonopoly().equals("Dark Blue") && getMoney() >= property.getPrice()) {
                return true;
            }
        } else if (getMoney() >= property.getPrice()) {
            return true;
        }
        return false;
    }
}
