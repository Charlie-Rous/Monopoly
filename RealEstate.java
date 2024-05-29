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

    public int mortgage() {
        if (numHouses == 0) {
            return super.mortgage();
        } else {
            System.out.println("You must sell all houses before mortgaging this property.");
            return -1;
        }
    }

    public void clear() {
        super.clear();
        numHouses = 0;
    }

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
        if (isMortgaged()) {
            return "[" + monopoly + "] " + getName() + "[" + numHouses + "] (mortgaged)";
        }
        return "[" + monopoly + "] " + getName() + "[" + numHouses + "]";
    }

    public String getMonopoly() {
        return monopoly;
    }

    public void removeHouse() {
        numHouses--;
    }

    public int getRent() {
        return rents[numHouses];
    }

    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }
}
