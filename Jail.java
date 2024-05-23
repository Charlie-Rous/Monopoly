import java.util.ArrayList;
public class Jail extends Tile {
    private ArrayList<Player> players;
    public Jail(String name, int price, int[] rents) {
        super(name, price, rents);
    }
}
