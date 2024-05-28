public class RealEstate extends Property {
    private int pricePerHouse;
    private int numHouses = 0;
    private String monopoly;
    private int[] rents;

    public RealEstate(String name, int price, int[] rents, int pricePerHouse, String monopoly) {
        super(name, price);
        this.pricePerHouse = pricePerHouse;
        this.monopoly = monopoly;
        this.rents = rents;

    }

    public void clear() {
        super.clear();
        numHouses = 0;
    }
    
    // create getters for the price per house and the number of houses
    public int getPricePerHouse() {
        return pricePerHouse;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public void addHouse() {
        numHouses++;
    }

    public String toString() {
        return " [" + monopoly + "] " + getName() + "[" + numHouses + "]";
    }

    // create a getter for the monopoly color
    public String getMonopoly() {
        return monopoly;
    }

    public int getRent() {
        return rents[numHouses];
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }
}
