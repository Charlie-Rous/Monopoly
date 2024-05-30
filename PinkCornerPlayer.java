public class PinkCornerPlayer extends Player{
    
    public PinkCornerPlayer(String name, int position) {
        super(name, position);
    }

    public boolean wantsToBuy(Property property) {
        if (property instanceof RealEstate) {
            RealEstate realEstate = (RealEstate) property;
            if (realEstate.getMonopoly().equals("Pink") || realEstate.getMonopoly().equals("Light Blue") && getMoney() >= property.getPrice()) {
                return true;
            }
        } else if (getMoney() >= property.getPrice()) {
            return true;
        }
        return false;
    }
}
