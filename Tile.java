public class Tile {
    private String name;
    private int price;
    private Player owner;
    private int[] rents; 


    public Tile(String name, int price, int[] rents) {
        this.name = name;
        this.price = price;
        this.rents = rents;
    }
}
