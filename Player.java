import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    // create a basic player class for the game
    private String name;
    private int money;
    private int position;
    ArrayList<Property> properties = new ArrayList<Property>();
    HashMap<String, Boolean> monopolies = new HashMap<String, Boolean>();
    private int brownMonopoly = 0;
    private int lightBlueMonopoly = 0;
    private int pinkMonopoly = 0;
    private int orangeMonopoly = 0;
    private int redMonopoly = 0;
    private int yellowMonopoly = 0;
    private int greenMonopoly = 0;
    private int blueMonopoly = 0;

    // create a constructor for the player class
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        position = 0;
        // Initialize all monopolies to false
        monopolies.put("Brown", false);
        monopolies.put("Light Blue", false);
        monopolies.put("Pink", false);
        monopolies.put("Orange", false);
        monopolies.put("Red", false);
        monopolies.put("Yellow", false);
        monopolies.put("Green", false);
        monopolies.put("Dark Blue", false);
    }

    // Check if player has a monopoly of a certain color
    public boolean hasMonopoly(String color) {
        return monopolies.get(color);
    }

    // Set a monopoly of a certain color
    public void setMonopoly(String color, boolean hasMonopoly) {
        monopolies.put(color, hasMonopoly);
    }

    public boolean hasAnyMonopoly() {
        return monopolies.values().contains(true);
    }

    // create a boolean wants to buy method for the player class
    public boolean wantsToBuy(Property property) {
        if (money >= property.getPrice()) {
            return true;
        }
        return false;
    }

    // create a wants to build method for the player class
    public String monopolyToBuild() {
        int numProperties = 3;
        for (Property property : properties) {
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;

                if (realEstate.getMonopoly().equals("Dark Blue") || realEstate.getMonopoly().equals("Brown")) {
                    numProperties = 2;
                }
                if (money >= realEstate.getPricePerHouse() * numProperties && realEstate.getNumHouses() < 5
                        && hasMonopoly(realEstate.getMonopoly())) {
                    return realEstate.getMonopoly();
                }
            }
            

        }
        return null;
    }

    public void build(String monopoly) {
        int numHouses = 0;
        for (Property property : properties) {
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;
                if (realEstate.getMonopoly().equals(monopoly)) {
                    realEstate.addHouse();
                    numHouses = realEstate.getNumHouses();
                    subtractMoney(realEstate.getPricePerHouse());
                }
            }
        
        }
        System.out.println(name + " built houses on the " + monopoly + " properties.");
        System.out.println("The " + monopoly + " monopoly now has " + numHouses + " houses.");
    }

    // create a method to add a property to the player's list of properties
    public void addProperty(Property property) {
        System.out.println(name + " bought " + property.getName());
        properties.add(property);
        property.setOwner(this);
        // increase the monopoly count for the property using switch
        if (property instanceof RealEstate) {
            increaseMonopolyCount((RealEstate) property);
        }
       
    }

    public void increaseMonopolyCount(RealEstate property) {
        switch (property.getMonopoly()) {
            case "Brown":
                brownMonopoly++;
                if (brownMonopoly == 2) {
                    setMonopoly("Brown", true);
                }
                break;
            case "Light Blue":
                lightBlueMonopoly++;
                if (lightBlueMonopoly == 3) {
                    setMonopoly("Light BLue", true);
                }
                break;
            case "Pink":
                pinkMonopoly++;
                if (pinkMonopoly == 3) {
                    setMonopoly("Pink", true);
                }
                break;
            case "Orange":
                orangeMonopoly++;
                if (orangeMonopoly == 3) {
                    setMonopoly("Orange", true);
                }
                break;
            case "Red":
                redMonopoly++;
                if (redMonopoly == 3) {
                    setMonopoly("Red", true);
                }
                break;
            case "Yellow":
                yellowMonopoly++;
                if (yellowMonopoly == 3) {
                    setMonopoly("Yellow", true);
                }
                break;
            case "Green":
                greenMonopoly++;
                if (greenMonopoly == 3) {
                    setMonopoly("Green", true);
                }
                break;
            case "Dark Blue":
                blueMonopoly++;
                if (blueMonopoly == 2) {
                    setMonopoly("Dark Blue", true);
                }
                break;
        }    
    }

    public void leaveGame() {
        for (Property property : properties) {
            property.clear();

        }
    }

    // create a method to get the player's properties
    public ArrayList<Property> getProperties() {
        return properties;
    }

    // create a method to add money to the player's account
    public void addMoney(int amount) {
        money += amount;
    }

    // create a method to subtract money from the player's account
    public void subtractMoney(int amount) {
        money -= amount;
    }

    // create a move method for the player class
    public void move(int spaces) {
        position += spaces;
        position = position % 40;
    }

    // create a getter for the player's position
    public int getPosition() {
        return position;
    }
    // create a getter for the player's name

    public String getName() {
        return name;
    }

    // create a getter for the player's money
    public int getMoney() {
        return money;
    }

    // create a setter for the player's position
    public void setPosition(int position) {
        this.position = position;
    }
}
