import java.util.ArrayList;

public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int STARTING_MONEY = 1500;
    static final int GO_MONEY = 200;
    static ArrayList<Player> players = new ArrayList<Player>();
    static int numTurns = 0;;
    static int numDoubles = 0;
    static final int MAX_TURNS = 200;

    public static void main(String[] args) {
        populatePlayers();
        while (players.size() > 1 && numTurns < MAX_TURNS) {
            playTurn();
            numTurns++;
        }

        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " wins!");
        } else {
            System.out.println("Game ended in a draw");
        }

    }

    // play turn
    public static void playTurn() {
        for (int i = 0; i < players.size(); i++) {
            boolean doubles = false;
            int[] rolls = dice.roll(players.get(i).getName());
            int roll = rolls[0] + rolls[1];

            if (rolls[0] == rolls[1]) {
                doubles = true;
            }
            if (players.get(i).getPosition() + roll >= 40) {
                players.get(i).addMoney(GO_MONEY);
                System.out.println(players.get(i).getName() + " passed GO and collected $200");
            }
            board.move(players.get(i), roll, doubles);

            if (players.get(i).getMoney() < 0) {
                System.out.println(players.get(i).getName() + " has gone bankrupt and is out of the game");
                players.get(i).leaveGame();
                players.remove(i);
                i--;
                break;
            }

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
                    System.out.println();
                    System.out.println("--------------------");
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