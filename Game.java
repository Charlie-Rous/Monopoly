import java.util.ArrayList;

public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int startingMoney = 1500;
    static ArrayList<Player> players = new ArrayList<Player>();
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", 1500);
        Player player2 = new Player("Player 2", 1500); 

        board.move(player1, dice.roll(player1.getName()));
        
    }
}