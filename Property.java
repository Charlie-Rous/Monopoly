public class Property extends Tile {
    private int pricePerHouse;
    private int numHouses = 0;
    private String monopoly;
    private int price;
    private Player owner;
    private int[] rents; 
    
    public Property(String name, int price, int[] rents, int pph, String m) {
        super(name);
        this.price = price;
        this.owner = null;
        this.rents = rents;
        pricePerHouse = pph;
        monopoly = m;
    }


    //create getters for the price and the owner of the property
    public int getPrice() {
        return price;
    }
    public Player getOwner() {
        return owner;
    }
    public int[] getRents() {
        return rents;
    }

    //set owner of property
    public void setOwner(Player p) {
        owner = p;
    }

    //create getters for the price per house and the number of houses
    public int getPricePerHouse() {
        return pricePerHouse;
    }
    public int getNumHouses() {
        return numHouses;
    }

    //create a method to add a house to the property
    public void addHouse() {
        numHouses++;
    }

    public String toString(){
        return getName() + " [" + monopoly + "] [" + numHouses + "]";
    }
    
}
