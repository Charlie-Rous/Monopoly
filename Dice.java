
public class Dice {
    public int roll(String name) {
        int roll1 = (int) (Math.random() * 6) + 1;
        int roll2 = (int) (Math.random() * 6) + 1;
        if (roll1 == roll2) {
            System.out.println(name + " rolled double " + roll1 + "s");
            return (roll1 + roll2) * 10;
        }
        System.out.println(name + " rolled a " + roll1 + " and a " + roll2);
        return roll1 + roll2;
    }

}
