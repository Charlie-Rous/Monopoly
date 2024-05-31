public class Property extends Tile{

    private int price;
    private Player owner;
    private int rent;
    private int mortgage;
    private boolean isMortgaged;
   

    public Property(String name, int price) {
        super(name);
        this.price = price;
        this.owner = null;
        rent = 0;
        mortgage = price / 2;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public int mortgage() {
        isMortgaged = true;
        return mortgage;
    }

    public int getMortgage() {
        return mortgage;
    }

    public void clear() {
        owner = null;
        isMortgaged = false;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public int getRent() {
        return rent;
    }

    public void setOwner(Player p) {
        owner = p;
    }

    public String toString() {
        return "$" + price + " " + getName();
    }

    

}
