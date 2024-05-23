public class Property extends Tile {
    private int pricePerHouse;
    private int numHouses;
    private String monopoly;
    public Property(String name, int price, int[] rents, int pph, String m) {
        super(name, price, rents);
        pricePerHouse = pph;
        monopoly = m;
    }
    
}
