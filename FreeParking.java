public class FreeParking extends Tile {
    private static int funds;

    public FreeParking(String name) {
        super(name);
        funds = 500;
    }

    public static int getFunds() {
        return funds;
    }

    public static void addFunds(int amount) {
        funds += amount;
        System.out.println("Free Parking now has $" + funds + " in funds.");
    }

    public static void clearFunds() {
        funds = 0;
        System.out.println("Free Parking now has $" + funds + " in funds.");
    }
}
