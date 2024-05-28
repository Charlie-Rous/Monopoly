import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

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

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        position = 0;

        monopolies.put("Brown", false);
        monopolies.put("Light Blue", false);
        monopolies.put("Pink", false);
        monopolies.put("Orange", false);
        monopolies.put("Red", false);
        monopolies.put("Yellow", false);
        monopolies.put("Green", false);
        monopolies.put("Dark Blue", false);
    }

    public boolean hasMonopoly(String color) {
        return monopolies.get(color);
    }

    public void setMonopoly(String color, boolean hasMonopoly) {
        monopolies.put(color, hasMonopoly);
    }

    public boolean hasAnyMonopoly() {
        return monopolies.values().contains(true);
    }

    public boolean wantsToBuy(Property property) {
        if (money >= property.getPrice()) {
            return true;
        }
        return false;
    }

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

    public void addProperty(Property property) {
        System.out.println(name + " bought " + property.getName());
        properties.add(property);
        property.setOwner(this);

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

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    public void move(int spaces) {
        position += spaces;
        position = position % 40;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
