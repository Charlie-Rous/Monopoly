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

    public void increaseNumOwned() {
        numOwned++;
    }

    public String toString() {
        return getName() + " [" + numOwned + "]";
    }
}
