
public class Dice {
    public int[] roll(String name) {
        int[] rolls = new int[2];
        rolls[0] = (int) (Math.random() * 6) + 1;
        rolls[1] = (int) (Math.random() * 6) + 1;
        if (rolls[0] == rolls[1]) {
            System.out.println(name + " rolled double " + rolls[0] + "s");
        } else {
            System.out.println(name + " rolled a " + rolls[0] + " and a " + rolls[1]);
        }
        
        return rolls;

    }

}
