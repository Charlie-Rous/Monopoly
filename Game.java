import java.util.ArrayList;


public class Game {
    static Dice dice = new Dice();
    static Board board = new Board();
    static final int STARTING_MONEY = 1500;
    static ArrayList<Player> players = new ArrayList<Player>();
    static int numTurns = 1;;
    static int numDoubles = 0;
    static final int MAX_TURNS = 100;
    static boolean turnByTurn = false;
    public static void main(String[] args) {
        startGame();

        while (players.size() > 1 && numTurns < MAX_TURNS) {
            playTurn();
            if (turnByTurn) {
                System.out.print("Continue? (y/n)");
                if (System.console().readLine().equals("n")) {
                    break;
                }
            }

            numTurns++;
        }
        System.out.println();


        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " wins!");
        } else {
            System.out.println("Game ended in a draw");
        }

    }

    public static void startGame() {
        populatePlayers();
        boolean validInput = false;
        System.out.println("Game started with " + players.size() + " players");
        while (!validInput) {
            System.out.print("Simulation or Turn by Turn ? (s/t) ");
            String input = System.console().readLine();
            if (input.equals("t"))  {
                turnByTurn = true;
                validInput = true;
            } else if (input.equals("s")) {
                turnByTurn = false;
                validInput = true;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // play turn
    public static void playTurn() {
        System.out.println("Turn " + numTurns);
        System.out.println();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println(player.getName() + " $" + player.getMoney());
            System.out.println();
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

            trades(player);

            

            System.out.println(player.getName() + " has $" + player.getMoney());
            player.printProperties();
            

            
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

    public static void trades(Player player) {
        String monopoly = player.wantsToTradeFor();
            if (monopoly != null) {
                System.out.println(player.getName() + " wants to trade for " + monopoly);
                for (Player otherPlayer : players) {
                    if (otherPlayer != player) {
                        if (otherPlayer.ownsProperty(monopoly) != null) {
                            RealEstate property = otherPlayer.ownsProperty(monopoly);
                            String otherMonopoly = otherPlayer.wantsToTradeFor();
                            int price = property.getPrice() * 3;
                            if (otherMonopoly != null && player.ownsProperty(otherMonopoly) != null && !otherMonopoly.equals(monopoly) ) {
                                Property property1 = otherPlayer.ownsProperty(monopoly);
                                Property property2 = player.ownsProperty(otherPlayer.wantsToTradeFor());
                                
                                player.removeProperty(property2);
                                otherPlayer.removeProperty(property1);
                                player.addProperty(property1);
                                otherPlayer.addProperty(property2);
                                System.out.println(player.getName() + " traded " + property2.getName() + " for " + property1.getName() + " with " + otherPlayer.getName());
                                return;
                            } else if (player.getMoney() >= price) {
                                player.subtractMoney(price);
                                otherPlayer.addMoney(price);
                                otherPlayer.removeProperty(property);
                                player.addProperty(property);
                                
                                System.out.println(player.getName() + " bought " + property.getName() + " from " + otherPlayer.getName() + " for $" + price);
                                return;
                            }

                        }
                    }
                }
                System.out.println(player.getName() + " could not find a trade partner");
            } else {
                System.out.println(player.getName() + " does not want to trade");
            }
        
    }

    public static void populatePlayers() {
        players.add(new Player("Player 1", STARTING_MONEY));
        players.add(new Player("Player 2", STARTING_MONEY));
        // players.add(new ConservativePlayer("Conservative", MAX_TURNS));
        // players.add(new RedCornerPlayer("Red Player", MAX_TURNS));
        // players.add(new BrownCornerPlayer("Brown Player", MAX_TURNS));
        // players.add(new YellowCornerPlayer("Yellow Player", MAX_TURNS));
        // players.add(new PinkCornerPlayer("Pink Player", MAX_TURNS));
    }
}