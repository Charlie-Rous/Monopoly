public class YellowCornerPlayer extends Player {
    

    public YellowCornerPlayer(String name, int money) {
        super(name, money);
        
    }

  

    public boolean wantsToBuy(Property property) {
        if (property instanceof RealEstate) {
            RealEstate realEstate = (RealEstate) property;
            if (realEstate.getMonopoly().equals("Yellow") || realEstate.getMonopoly().equals("Green") && getMoney() >= property.getPrice()) {
                return true;
            }
        } else if (getMoney() >= property.getPrice()) {
            return true;
        }
        return false;
    }
}