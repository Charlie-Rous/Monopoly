import java.util.ArrayList;
public class Jail extends Tile {
    private ArrayList<Player> players;
    private ArrayList<Integer> turns;

    public Jail(String name) {
        super(name);
        players = new ArrayList<Player>();
    }
    public void addPlayer(Player p) {
        players.add(p);
    }
    public void removePlayer(Player p) {
        players.remove(p);
    }
    public void addTurns(Player p) {
        turns.set(players.indexOf(p), turns.get(players.indexOf(p)) + 1);
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

}
