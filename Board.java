import java.util.ArrayList;

import javax.rmi.CORBA.Util;



public class Board {
    private ArrayList<Tile> tiles;
    private Jail jail;

    public Board() {
        tiles = new ArrayList<Tile>();
        populateTiles();
        jail = new Jail("Jail");
    }

    // create a move method that takes in a player and a number of spaces to move
    public void move(Player player, int spaces, boolean doubles) {
        if (jail.getPlayers().contains(player)) {
            if (doubles) {
                jail.removePlayer(player);
                move(player, spaces, doubles);
            } else {
                System.out.println(player.getName() + " is in jail for "
                        + jail.getTurns().get(jail.getPlayers().indexOf(player)) + " turns");
                jail.addTurns(player);
            }
        } else {
            player.move(spaces);
            Tile tile = tiles.get(player.getPosition());
            System.out.println(player.getName() + " landed on " + tile.getName());

            processTile(tile, player, spaces);
            
            if (player.monopolyToBuild() != null) {
                player.build(player.monopolyToBuild());
            }
        }

        System.out.println(player.getName() + " has $" + player.getMoney());
        System.out.println(player.getName() + " ownes: " + player.getProperties());
    }

    public void processTile(Tile tile, Player player, int roll) {
        if (tile instanceof Property) {
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
                player.subtractMoney(rent);
                property.getOwner().addMoney(rent);

                System.out.println(player.getName() + " paid " + property.getOwner().getName() + " $"+ rent);
            }
        } else if (tile instanceof Tax) {
            if (tile instanceof Income) {
                int amount = ((Income) tile).getAmount();
                double percent = ((Income) tile).getPercent();
                if (player.getMoney() * percent < amount) {
                    System.out.println(player.getName() + " paid $" + (int) (player.getMoney() * percent) + " in income tax");
                    FreeParking.addFunds((int) (player.getMoney() * percent));
                    player.subtractMoney((int) (player.getMoney() * percent));
                } else {
                    player.subtractMoney(amount);
                    FreeParking.addFunds(amount);
                }
            } else {
                player.subtractMoney(((Tax) tile).getAmount());
                FreeParking.addFunds(((Tax) tile).getAmount());
            }

        } else if (tile instanceof FreeParking) {
            player.addMoney(FreeParking.getFunds());
            System.out.println(player.getName() + " collected $" + FreeParking.getFunds() + " from Free Parking");
            FreeParking.clearFunds();
        }
    }

    public Jail getJail() {
        return jail;
    }

    public void populateTiles() {
        tiles.add(new Tile("GO"));

        int[] rents = { 2, 10, 30, 90, 160, 250 };
        tiles.add(new RealEstate("Mediterranean Avenue", 60, rents, 50, "Brown"));

        tiles.add(new Tile("Community Chest"));

        int[] rents2 = { 4, 20, 60, 180, 320, 450 };
        tiles.add(new RealEstate("Baltic Avenue", 60, rents2, 50, "Brown"));

        tiles.add(new Income("Income Tax", 200, 0.1));
        tiles.add(new Transportation("Reading Railroad"));

        int[] rents3 = { 6, 30, 90, 270, 400, 550 };
        tiles.add(new RealEstate("Oriental Avenue", 100, rents3, 50, "Light Blue"));

        tiles.add(new Tile("Chance"));

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

        tiles.add(new Tile("Community Chest"));

        tiles.add(new RealEstate("Tennessee Avenue", 180, rents7, 100, "Orange"));
        int[] rents8 = { 16, 80, 220, 600, 800, 1000 };
        tiles.add(new RealEstate("New York Avenue", 200, rents8, 100, "Orange"));

        tiles.add(new FreeParking("Free Parking"));

        int[] rents9 = { 18, 90, 250, 700, 875, 1050 };
        tiles.add(new RealEstate("Kentucky Avenue", 220, rents9, 150, "Red"));

        tiles.add(new Tile("Chance"));

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

        tiles.add(new Tile("Community Chest"));

        int[] rents14 = { 28, 150, 450, 1000, 1200, 1400 };
        tiles.add(new RealEstate("Pennsylvania Avenue", 320, rents14, 200, "Green"));

        tiles.add(new Transportation("Short Line"));
        tiles.add(new Tile("Chance"));

        int[] rents15 = { 35, 175, 500, 1100, 1300, 1500 };
        tiles.add(new RealEstate("Park Place", 350, rents15, 200, "Dark Blue"));

        tiles.add(new Tax("Luxury Tax", 75));

        int[] rents16 = { 50, 200, 600, 1400, 1700, 2000 };
        tiles.add(new RealEstate("Boardwalk", 400, rents16, 200, "Dark Blue"));
    }
}
