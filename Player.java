
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
public class Player {

    private String name;
    private int money;
    private int position;
    ArrayList<Property> properties = new ArrayList<Property>();
    ArrayList<Property> mortgagedProperties = new ArrayList<Property>();
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

    public ArrayList<Property> getMortgagedProperties() {
        return mortgagedProperties;
    }

    public void printProperties() {
        Collections.sort(properties, new PropertyComparator());
        System.out.println(name + " ownes: ");
        if (properties.size() == 0) {
            System.out.println("[]");
        } else {
            System.out.print(properties.get(0));
            for (int i = 1; i < properties.size(); i++) {
                if (properties.get(i) instanceof RealEstate) {
                   RealEstate realEstate = (RealEstate) properties.get(i);
                   if (realEstate.getMonopoly().equals(((RealEstate) properties.get(i - 1)).getMonopoly())) {
                       System.out.print(", " + realEstate);
                   } else {
                       System.out.println();
                       System.out.print(realEstate);
                   }
                } else if (properties.get(i) instanceof Transportation) {
                    Transportation t = (Transportation) properties.get(i);
                    if (properties.get(i - 1) instanceof Transportation) {
                        System.out.print(", " + t);
                    } else {
                        System.out.println();
                        System.out.print(t);
                    }
                } else {
                    Utilities u = (Utilities) properties.get(i);
                    if (properties.get(i - 1) instanceof Utilities) {
                        System.out.print(", " + u);
                    } else {
                        System.out.println();
                        System.out.print(u);
                    }
                }
                
            }
        }
        System.out.println();
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
        
        for (Property property : properties) {
            if (property instanceof RealEstate && property.isMortgaged() == false) {
                int numProperties = 3;
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

    public int subtractMoney(int amount) {
        if (amount > money) {
            System.out.println(name + " owes $" + amount + " but only has $" + money + ".");
            addMoney(sellAssets(amount - money));
            System.out.println(name + " sold assets and now has $" + money + ".");
        }
        if (amount > money) {
            System.out.println("Those are insufficient funds.");
            int temp = money;
            money -= amount;
            return temp;
        }
        money -= amount;
        return amount;
    }

    public int sellAssets(int amount) {
        int total = 0;
    
        total += sellHouses(amount);
        if (total < amount ) {
            total += mortgageProperties(amount - total);
        }
        
        return total;
        
    }

    private int mortgageProperties(int amount) {
        int total = 0;
        for (Property property : properties) {
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;
                if (!realEstate.isMortgaged() && realEstate.getNumHouses() == 0) {
                    total += realEstate.mortgage();
                    mortgagedProperties.add(realEstate);
                    System.out.println(name + " mortgaged " + realEstate.getName() + " for $" + realEstate.getMortgage() + ".");
                    if (total >= amount) {
                       
                        return total;
                    }
                }
            } else {
                total += property.mortgage();
                System.out.println(name + " mortgaged " + property.getName() + " for $" + property.getMortgage() + ".");
                if (total >= amount) {
                    return total;
                }
            
            }
        }
        return total;
    }

    private int sellHouses(int amount) {
        int total = 0;
        while (total < amount && hasHouses()) {
            for (Property property : properties) {
                if (property instanceof RealEstate) {
                    RealEstate realEstate = (RealEstate) property;
                    if (realEstate.getNumHouses() > 0) {
                        total += sellHouse(realEstate.getMonopoly());
                        int numHouses = 3;
                        if (realEstate.getMonopoly().equals("Brown") || realEstate.getMonopoly().equals("Dark Blue")) {
                            numHouses = 2;
                        }
                        System.out.println(name + " sold houses on the " + realEstate.getMonopoly() + " properties for $"+ (realEstate.getPricePerHouse() / 2) * numHouses + ".");
                        if (total >= amount) {
                            return total;
                        }
                    }
                }
            }
        }
        return total;
    }

    private boolean hasHouses() {
        for (Property property : properties) {
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;
                if (realEstate.getNumHouses() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int sellHouse(String monopoly) {
        int total = 0;
        for (Property property : properties) {
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;
                if (realEstate.getMonopoly().equals(monopoly) && realEstate.getNumHouses() > 0) {
                    realEstate.removeHouse();
                    
                    total += realEstate.getPricePerHouse() / 2;
                }
            }
        }
        return total;
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

    public int getNetWorth() {
        int total = money;
        for (Property property : properties) {
            if (property.isMortgaged()) {
                total += property.getMortgage();
            } else {
                total += property.getPrice();
            }
            if (property instanceof RealEstate) {
                RealEstate realEstate = (RealEstate) property;
                total += realEstate.getNumHouses() * realEstate.getPricePerHouse();
            }
        }
        return total;
    }
}
