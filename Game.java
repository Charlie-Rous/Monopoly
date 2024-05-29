import java.util.ArrayList;
import java.util.Collections;

public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int STARTING_MONEY = 1500;
    static ArrayList<Player> players = new ArrayList<Player>();
    static int numTurns = 0;;
    static int numDoubles = 0;
    static final int MAX_TURNS = 100;

    public static void main(String[] args) {
        populatePlayers();
        System.out.println("Game started with " + players.size() + " players");
        while (players.size() > 1 && numTurns < MAX_TURNS) {
            playTurn();
            numTurns++;
        }
        System.out.println();

        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " wins!");
        } else {
            System.out.println("Game ended in a draw");
        }

    }

    // play turn
    public static void playTurn() {
        System.out.println("Turn " + numTurns);
        System.out.println();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println(player.getName() + "$" + player.getMoney());
            boolean doubles = false;
            int[] rolls = dice.roll(player.getName());
            int roll = rolls[0] + rolls[1];

            if (rolls[0] == rolls[1]) {
                doubles = true;
            }

           
            board.move(player, roll, doubles);

            if (player.getMoney() < 0) {
                System.out.println(player.getName() + " has gone bankrupt and is out of the game");
                player.leaveGame();
                players.remove(i);
                i--;
                break;
            }

            System.out.println(player.getName() + " has $" + player.getMoney());
            Collections.sort(player.getProperties(), new PropertyComparator());
        
            System.out.println(player.getName() + " ownes: " + player.getProperties());

            
            if (doubles) {
                numDoubles++;
                if (numDoubles == 3) {
                    System.out.println(player.getName() + " rolled 3 doubles in a row and is going to jail");
                    board.getJail().addPlayer(player);
                    players.get(i).setPosition(10);
                    numDoubles = 0;
                    System.out.println();
                    System.out.println("--------------------");
                    System.out.println();
                } else {
                    i--; // allows player to go again
                }
            } else {
                numDoubles = 0;
                System.out.println();
                System.out.println("--------------------");
            }
            
            System.out.println();
            
            
        }
    }

    public static void populatePlayers() {
        players.add(new Player("Player 1", STARTING_MONEY));
        players.add(new Player("Player 2", STARTING_MONEY));
    }
}