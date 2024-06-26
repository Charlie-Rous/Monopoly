public class Transportation extends Property {
    static int numOwned;
    int[] rents = { 0, 25, 50, 100, 200 };

    public Transportation(String name) {
        super(name, 200);
        numOwned = 0;
    }

    public void clear() {
        super.clear();
        numOwned--;
    }

    public int getRent() {
        return rents[numOwned];
    }

    public static void increaseNumOwned() {
        numOwned++;
    }

    public String toString() {
        if (isMortgaged()) {
            return getName() + "[" + numOwned + "] (mortgaged)";
        }
        return getName() + " [" + numOwned + "]";
    }

}
