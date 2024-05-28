public class Property extends Tile {

    private int price;
    private Player owner;
    private int rent;

    public Property(String name, int price) {
        super(name);
        this.price = price;
        this.owner = null;
        rent = 0;
    }

    public void clear() {
        owner = null;
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
