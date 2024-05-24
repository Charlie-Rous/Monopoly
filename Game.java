import java.util.ArrayList;

public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int STARTING_MONEY = 1500;
    static final int GO_MONEY = 200;
    static ArrayList<Player> players = new ArrayList<Player>();
    static int numTurns = 50;
    static int numDoubles = 0;

    public static void main(String[] args) {
        populatePlayers();
        while (numTurns > 0) {
            playTurn();
            numTurns--;
        } 
        
    }

    

    //play turn
    public static void playTurn() {
        for (int i = 0; i < players.size(); i++) {
            boolean doubles = false;
            int roll = dice.roll(players.get(i).getName());
            
            if (roll > 12) {
                roll = roll / 10;
                doubles = true;
            } 
            if (players.get(i).getPosition() + roll >= 40) {
                players.get(i).addMoney(GO_MONEY);
                System.out.println(players.get(i).getName() + " passed GO and collected $200");
            }
            board.move(players.get(i), roll, doubles);
            if (players.get(i).getPosition() == 30) {
                System.out.println(players.get(i).getName() + " is going to jail");
                board.getJail().addPlayer(players.get(i));
                players.get(i).setPosition(10);
            }
            if (doubles) {
                numDoubles++;
                if (numDoubles == 3) {
                    System.out.println(players.get(i).getName() + " rolled 3 doubles in a row and is going to jail");
                    board.getJail().addPlayer(players.get(i));
                    players.get(i).setPosition(10);
                    numDoubles = 0;
                } else {
                    i--;
                }
            }  else {
                numDoubles = 0;
                System.out.println("--------------------");
            }
        }
    }
    //populate players
    public static void populatePlayers() {
        players.add(new Player("Player 1", STARTING_MONEY));
        players.add(new Player("Player 2", STARTING_MONEY));
    }
}