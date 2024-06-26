public class Utilities extends Property {
    static int numOwned = 0;

    public Utilities(String name) {
        super(name, 150);
    }

    public int getRent(int roll) {
        if (numOwned == 1) {
            return roll * 4;
        }

        return roll * 10;

    }

    public void clear() {
        super.clear();
        numOwned--;

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
