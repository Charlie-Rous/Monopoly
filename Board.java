import java.util.ArrayList;
import java.util.Collections;


public class Board {
    private ArrayList<Tile> tiles;
    private Jail jail;
    private Deck chance;
    private Deck communityChest;
    private final int GO_MONEY = 200;

    public Board() {
        tiles = new ArrayList<Tile>();
        populateChance();
        populateCommunityChest();
        populateTiles();

        jail = new Jail("Jail");
    }

    
    public void move(Player player, int spaces, boolean doubles) {
        if (jail.getPlayers().contains(player)) {
            if (doubles) { // rolling doubles lets you out of jail
                jail.removePlayer(player);
                move(player, spaces, doubles);
            } else {
                System.out.println(player.getName() + " is in jail for "
                        + jail.getTurns().get(jail.getPlayers().indexOf(player)) + " turns");
                jail.addTurns(player);
            }
        } else {
            if (player.getPosition() + spaces >= 40) {
                player.addMoney(GO_MONEY);
                System.out.println(player.getName() + " passed GO and collected $200");
            }
            player.move(spaces);
            
            Tile tile = tiles.get(player.getPosition());
            System.out.println(player.getName() + " landed on " + tile.getName());

            processTile(tile, player, spaces);

            if (player.monopolyToBuild() != null) {
                player.build(player.monopolyToBuild());
            }
        }

        
        
    }

    public void processTile(Tile tile, Player player, int roll) {
        if (player.getPosition() == 30) {
            System.out.println(player.getName() + " is going to jail");
            jail.addPlayer(player);
            player.setPosition(10);
            return;
        }
        if (tile instanceof Property  && ((Property) tile).isMortgaged() == false) {
            Property property = (Property) tile;
            int rent;
            if (property instanceof Utilities) {
                Utilities utility = (Utilities) property;
                rent = utility.getRent(roll);
            } else {
                rent = property.getRent();
            }

            if (property.getOwner() == null) { // If no one owns the property
                if (player.wantsToBuy(property)) {
                    player.subtractMoney(property.getPrice());
                    player.addProperty(property);
                    property.setOwner(player);
                    if (property instanceof Transportation) {
                        Transportation.increaseNumOwned();
                    } else if (property instanceof Utilities) {
                        Utilities.increaseNumOwned();
                    }
                }
            } else if (property.getOwner() != player) { // If someone else owns the property
                int amountPayed = player.subtractMoney(rent);
                property.getOwner().addMoney(amountPayed);

                System.out.println(player.getName() + " paid " + property.getOwner().getName() + " $" + amountPayed);
            }
        } else if (tile instanceof Tax) {
            if (tile instanceof Income) {
                int amount = ((Income) tile).getAmount();
                double percent = ((Income) tile).getPercent();
                if (player.getNetWorth() * percent < amount) {
                    System.out.println(
                            player.getName() + " paid $" + (int) (player.getNetWorth() * percent) + " in income tax");
                    FreeParking.addFunds(player.subtractMoney((int) (player.getNetWorth() * percent)));
                    
                } else {
    
                    FreeParking.addFunds(player.subtractMoney(amount));
                }
            } else {
                FreeParking.addFunds(player.subtractMoney(((Tax) tile).getAmount()));
            }

        } else if (tile instanceof FreeParking) {
            player.addMoney(FreeParking.getFunds());
            System.out.println(player.getName() + " collected $" + FreeParking.getFunds() + " from Free Parking");
            FreeParking.clearFunds();
        } else if (tile instanceof CardTile) {
            Card card;
            if (tile.getName().equals("Chance")) {
                card = chance.drawCard();
                System.out.println(player.getName() + " drew a Chance card: " + card.getText());
                chance.addCard(card);
            } else {
                card = communityChest.drawCard();
                System.out.println(player.getName() + " drew a Community Chest card: " + card.getText());
                communityChest.addCard(card);
            }
            card.execute(player, this);

        }
    }

    public Jail getJail() {
        return jail;
    }

    public void populateTiles() {
        tiles.add(new Tile("GO"));

        int[] rents = { 2, 10, 30, 90, 160, 250 };
        tiles.add(new RealEstate("Mediterranean Avenue", 60, rents, 50, "Brown"));

        tiles.add(new CardTile("Community Chest", communityChest));

        int[] rents2 = { 4, 20, 60, 180, 320, 450 };
        tiles.add(new RealEstate("Baltic Avenue", 60, rents2, 50, "Brown"));

        tiles.add(new Income("Income Tax", 200, 0.1));
        tiles.add(new Transportation("Reading Railroad"));

        int[] rents3 = { 6, 30, 90, 270, 400, 550 };
        tiles.add(new RealEstate("Oriental Avenue", 100, rents3, 50, "Light Blue"));

        tiles.add(new CardTile("Chance", chance));

        tiles.add(new RealEstate("Vermont Avenue", 100, rents3, 50, "Light Blue"));
        int[] rents4 = { 8, 40, 100, 300, 450, 600 };
        tiles.add(new RealEstate("Connecticut Avenue", 120, rents4, 50, "Light Blue"));

        tiles.add(new Jail("Jail"));

        int[] rents5 = { 10, 50, 150, 450, 625, 750 };
        tiles.add(new RealEstate("St. Charles Place", 140, rents5, 100, "Pink"));

        tiles.add(new Utilities("Electric Company"));

        tiles.add(new RealEstate("States Avenue", 140, rents5, 100, "Pink"));
        int[] rents6 = { 12, 60, 180, 500, 700, 900 };
        tiles.add(new RealEstate("Virginia Avenue", 160, rents6, 100, "Pink"));

        tiles.add(new Transportation("Pennsylvania Railroad"));

        int[] rents7 = { 14, 70, 200, 550, 750, 950 };
        tiles.add(new RealEstate("St. James Place", 180, rents7, 100, "Orange"));

        tiles.add(new CardTile("Community Chest", communityChest));

        tiles.add(new RealEstate("Tennessee Avenue", 180, rents7, 100, "Orange"));
        int[] rents8 = { 16, 80, 220, 600, 800, 1000 };
        tiles.add(new RealEstate("New York Avenue", 200, rents8, 100, "Orange"));

        tiles.add(new FreeParking("Free Parking"));

        int[] rents9 = { 18, 90, 250, 700, 875, 1050 };
        tiles.add(new RealEstate("Kentucky Avenue", 220, rents9, 150, "Red"));

        tiles.add(new CardTile("Chance", chance));

        tiles.add(new RealEstate("Indiana Avenue", 220, rents9, 150, "Red"));
        int[] rents10 = { 20, 100, 300, 750, 925, 1100 };
        tiles.add(new RealEstate("Illinois Avenue", 240, rents10, 150, "Red"));

        tiles.add(new Transportation("B & O Railroad"));

        int[] rents11 = { 22, 110, 330, 800, 975, 1150 };
        tiles.add(new RealEstate("Atlantic Avenue", 260, rents11, 150, "Yellow"));
        tiles.add(new RealEstate("Ventnor Avenue", 260, rents11, 150, "Yellow"));

        tiles.add(new Utilities("Water Works"));

        int[] rents12 = { 24, 120, 360, 850, 1025, 1200 };
        tiles.add(new RealEstate("Marvin Gardens", 280, rents12, 150, "Yellow"));

        tiles.add(new Tile("Go to Jail"));

        int[] rents13 = { 26, 130, 390, 900, 1100, 1275 };
        tiles.add(new RealEstate("Pacific Avenue", 300, rents13, 200, "Green"));
        tiles.add(new RealEstate("North Carolina Avenue", 300, rents13, 200, "Green"));

        tiles.add(new CardTile("Community Chest", communityChest));

        int[] rents14 = { 28, 150, 450, 1000, 1200, 1400 };
        tiles.add(new RealEstate("Pennsylvania Avenue", 320, rents14, 200, "Green"));

        tiles.add(new Transportation("Short Line"));
        tiles.add(new CardTile("Chance", chance));

        int[] rents15 = { 35, 175, 500, 1100, 1300, 1500 };
        tiles.add(new RealEstate("Park Place", 350, rents15, 200, "Dark Blue"));

        tiles.add(new Tax("Luxury Tax", 75));

        int[] rents16 = { 50, 200, 600, 1400, 1700, 2000 };
        tiles.add(new RealEstate("Boardwalk", 400, rents16, 200, "Dark Blue"));
    }

    public void populateChance() {
        chance = new Deck();
        chance.addCard(new MoveTo("Advance to Go", 0));
        chance.addCard(new MoveTo("Advance to Illinois Avenue", 24));
        chance.addCard(new MoveTo("Advance to St. Charles Place", 11));
        chance.addCard(new MoveTo("Advance to Reading Railroad", 5));
        chance.addCard(new MoveTo("Advance to Boardwalk", 39));
        chance.addCard(new MoneyCard("Bank pays you dividend of $50", 50));
        // chance.addCard(new Card("Get out of Jail Free", 0));
        chance.addCard(new MoveCard("Go back 3 spaces", -3));
        chance.addCard(new MoveCard("Go forward 3 spaces", 3));
        chance.addCard(new MoveTo("Go to Jail", 30));
        chance.addCard(new MoneyCard("Pay poor tax of $15", -15));
        chance.addCard(new MoveTo("Take a trip to Reading Railroad", 5));
        chance.addCard(new MoveTo("Take a walk on the Boardwalk", 39));

        chance.shuffle();
    }

    public void populateCommunityChest() {
        communityChest = new Deck();
        communityChest.addCard(new MoneyCard("Bank error in your favor, collect $200", 200));
        communityChest.addCard(new MoneyCard("Doctor's fees, pay $50", -50));
        communityChest.addCard(new MoneyCard("From sale of stock you get $50", 50));
        // communityChest.addCard(new MoneyCard("Get out of Jail Free", 0));
        // communityChest.addCard(new MoneyCard("Grand Opera Night, collect $50 from
        // every player", 50));
        communityChest.addCard(new MoneyCard("Holiday Fund matures, collect $100", 100));
        communityChest.addCard(new MoneyCard("Income tax refund, collect $20", 20));
        // communityChest.addCard(new MoneyCard("It's your birthday, collect $10 from
        // every player", 10));
        communityChest.addCard(new MoneyCard("Life insurance matures, collect $100", 100));
        communityChest.addCard(new MoneyCard("Pay hospital fees of $100", -100));
        communityChest.addCard(new MoneyCard("Pay school fees of $50", -50));
        communityChest.addCard(new MoneyCard("Receive $25 consultancy fee", 25));
        // communityChest.addCard(new MoneyCard("You are assessed for street repairs,
        // $40 per house, $115 per hotel", 0));
        communityChest.addCard(new MoneyCard("You have won second prize in a beauty contest, collect $10", 10));
        communityChest.addCard(new MoneyCard("You inherit $100", 100));
        communityChest.addCard(new MoneyCard("From sale of stock you get $45", 45));
        communityChest.addCard(new MoneyCard("You get a $100 birthday gift", 100));
        communityChest.addCard(new MoneyCard("You win $10", 10));
        communityChest.addCard(new MoneyCard("You win $100", 100));
        communityChest.addCard(new MoneyCard("You win $20", 20));
        communityChest.addCard(new MoneyCard("You win $200", 200));
        communityChest.addCard(new MoneyCard("You win $50", 50));
        communityChest.addCard(new MoneyCard("You win $5", 5));

        communityChest.shuffle();
    }

}
