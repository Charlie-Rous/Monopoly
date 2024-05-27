public class Property extends Tile {
    
    private int price;
    private Player owner;
    

    public Property(String name, int price) {
        super(name);
        this.price = price;
        this.owner = null;
    }



    // create getters for the price and the owner of the property
    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

   

    // set owner of property
    public void setOwner(Player p) {
        owner = p;
    }



    public String toString() {
        return "$" + price + " " + getName();
    }
    

   

   
}
