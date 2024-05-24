import java.util.ArrayList;

public class Board {
    private ArrayList<Tile> tiles;

    public Board() {
        tiles = new ArrayList<Tile>();
        populateTiles();
    }

// create a move method that takes in a player and a number of spaces to move
    public void move(Player player, int spaces) {
        player.move(spaces);
        Tile tile = tiles.get(player.getPosition());
        System.out.println(player.getName() + " landed on " + tile.getName());
        if (tile instanceof Property) {
            Property property = (Property) tile;
            if (player.wantsToBuy(property)) {
                player.addProperty(property);
                player.subtractMoney(property.getPrice());
            }
        }
        System.out.println(player.getName() + " has $" + player.getMoney());
        System.out.println(player.getName() + " ownes: " + player.getProperties()
    }


    public void populateTiles() {
        tiles.add(new Tile("GO", 0, null));

        int[] rents = {2, 10, 30, 90, 160, 250};
        tiles.add(new Property("Mediterranean Avenue", 60, rents, 50, "Brown" ));

        tiles.add(new Tile("Community Chest", 0, null));

        int[] rents2 = {4, 20, 60, 180, 320, 450};
        tiles.add(new Property("Baltic Avenue", 60, rents2, 50, "Brown" ));

        tiles.add(new Tile("Income Tax", 0, null));
        tiles.add(new Tile("Reading Railroad", 0, null));

        int[] rents3 = {6, 30, 90, 270, 400, 550};
        tiles.add(new Property("Oriental Avenue", 100, rents3, 50, "Light Blue" ));

        tiles.add(new Tile("Chance", 0, null));

        tiles.add(new Property("Vermont Avenue", 100, rents3, 50, "Light Blue" ));
        int[] rents4 = {8, 40, 100, 300, 450, 600};
        tiles.add(new Property("Connecticut Avenue", 120, rents4, 50, "Light Blue" ));

        tiles.add(new Tile("Jail", 0, null));

        int[] rents5 = {10, 50, 150, 450, 625, 750};
        tiles.add(new Property("St. Charles Place", 140, rents5, 100, "Pink" ));

        tiles.add(new Tile("Electric Company", 0, null));

        tiles.add(new Property("States Avenue", 140, rents5, 100, "Pink" ));
        int[] rents6 = {12, 60, 180, 500, 700, 900};
        tiles.add(new Property("Virginia Avenue", 160, rents6, 100, "Pink" ));

        tiles.add(new Tile("Pennsylvania Railroad", 0, null));

        int[] rents7 = {14, 70, 200, 550, 750, 950};
        tiles.add(new Property("St. James Place", 180, rents7, 100, "Orange" ));

        tiles.add(new Tile("Community Chest", 0, null));

        tiles.add(new Property("Tennessee Avenue", 180, rents7, 100, "Orange" ));
        int[] rents8 = {16, 80, 220, 600, 800, 1000};
        tiles.add(new Property("New York Avenue", 200, rents8, 100, "Orange" ));

        tiles.add(new Tile("Free Parking", 0, null));

        int[] rents9 = {18, 90, 250, 700, 875, 1050};
        tiles.add(new Property("Kentucky Avenue", 220, rents9, 150, "Red" ));

        tiles.add(new Tile("Chance", 0, null));

        tiles.add(new Property("Indiana Avenue", 220, rents9, 150, "Red" ));
        int[] rents10 = {20, 100, 300, 750, 925, 1100};
        tiles.add(new Property("Illinois Avenue", 240, rents10, 150, "Red" ));

        tiles.add(new Tile("B & O Railroad", 0, null));

        int[] rents11 = {22, 110, 330, 800, 975, 1150};
        tiles.add(new Property("Atlantic Avenue", 260, rents11, 150, "Yellow" ));
        tiles.add(new Property("Ventnor Avenue", 260, rents11, 150, "Yellow" ));

        tiles.add(new Tile("Water Works", 0, null));

        int[] rents12 = {24, 120, 360, 850, 1025, 1200};
        tiles.add(new Property("Marvin Gardens", 280, rents12, 150, "Yellow" ));

        tiles.add(new Tile("Go to Jail", 0, null));

        int[] rents13 = {26, 130, 390, 900, 1100, 1275};
        tiles.add(new Property("Pacific Avenue", 300, rents13, 200, "Green" ));
        tiles.add(new Property("North Carolina Avenue", 300, rents13, 200, "Green" ));

        tiles.add(new Tile("Community Chest", 0, null));

        int[] rents14 = {28, 150, 450, 1000, 1200, 1400};
        tiles.add(new Property("Pennsylvania Avenue", 320, rents14, 200, "Green" ));

        tiles.add(new Tile("Short Line", 0, null));
        tiles.add(new Tile("Chance", 0, null));

        int[] rents15 = {35, 175, 500, 1100, 1300, 1500};
        tiles.add(new Property("Park Place", 350, rents15, 200, "Dark Blue"));

        tiles.add(new Tile("Luxury Tax", 0, null));
        
        int[] rents16 = {50, 200, 600, 1400, 1700, 2000};
        tiles.add(new Property("Boardwalk", 400, rents16, 200, "Dark Blue"));
    }
}
