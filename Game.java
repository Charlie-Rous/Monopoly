import java.util.ArrayList;

public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int STARTING_MONEY = 1500;
    static final int GO_MONEY = 200;
    static ArrayList<Player> players = new ArrayList<Player>();
    public static void main(String[] args) {
        populatePlayers();
        for (int i = 0; i < players.size(); i++) {
            int originalRoll = dice.roll(players.get(i).getName());
            int roll;
            if (originalRoll > 12) {
                roll = originalRoll / 10;
            } else {
                roll = originalRoll;
            }
            if (players.get(i).getPosition() + roll >= 40) {
                players.get(i).addMoney(GO_MONEY);
            }
            board.move(players.get(i), roll);
            if (originalRoll > 12) {
                i--;
            }
            
        }
        
        
    }
    //populate players
    public static void populatePlayers() {
        players.add(new Player("Player 1", STARTING_MONEY));
        players.add(new Player("Player 2", STARTING_MONEY));
    }
}