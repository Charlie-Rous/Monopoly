import java.lang.reflect.Array;
import java.util.ArrayList;
public class Player {
    //create a basic player class for the game
    private String name;
    private int money;
    private int position;
    ArrayList<Property> properties = new ArrayList<Property>();
    private int brownMonopoly = 0;
    private int lightBlueMonopoly = 0;
    private int pinkMonopoly = 0;
    private int orangeMonopoly = 0;
    private int redMonopoly = 0;
    private int yellowMonopoly = 0;
    private int greenMonopoly = 0;
    private int blueMonopoly = 0;
    private boolean hasBrownMonopoly = false;
    private boolean hasLightBlueMonopoly = false;
    private boolean hasPinkMonopoly = false;
    private boolean hasOrangeMonopoly = false;
    private boolean hasRedMonopoly = false;
    private boolean hasYellowMonopoly = false;
    private boolean hasGreenMonopoly = false;
    private boolean hasBlueMonopoly = false;



    //create a constructor for the player class
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    //create a boolean wants to buy method for the player class
    public boolean wantsToBuy(Property property) {
        if (money >= property.getPrice()) {
            return true;
        }
        return false;
    }

    //create a method to add a property to the player's list of properties
    public void addProperty(Property property) {
        System.out.println(name + " bought " + property.getName());
        properties.add(property);
        property.setOwner(this);
        //increase the monopoly count for the property using switch
        switch (property.getMonopoly()) {
            case "Brown":
                brownMonopoly++;
                if (brownMonopoly == 2) {
                    hasBrownMonopoly = true;
                }
                break;
            case "Light Blue":
                lightBlueMonopoly++;
                if (lightBlueMonopoly == 3) {
                    hasLightBlueMonopoly = true;
                }
                break;
            case "Pink":
                pinkMonopoly++;
                if (pinkMonopoly == 3) {
                    hasPinkMonopoly = true;
                }
                break;
            case "Orange":
                orangeMonopoly++;
                if (orangeMonopoly == 3) {
                    hasOrangeMonopoly = true;
                }
                break;
            case "Red":
                redMonopoly++;
                if (redMonopoly == 3) {
                    hasRedMonopoly = true;
                }
                break;
            case "Yellow":
                yellowMonopoly++;
                if (yellowMonopoly == 3) {
                    hasYellowMonopoly = true;
                }
                break;
            case "Green":
                greenMonopoly++;
                if (greenMonopoly == 3) {
                    hasGreenMonopoly = true;
                }
                break;
            case "Blue":
                blueMonopoly++;
                if (blueMonopoly == 2) {
                    hasBlueMonopoly = true;
                }
                break;
        }
    }
    //create a method to get the player's properties
    public ArrayList<Property> getProperties() {
        return properties;
    }
    //create a method to add money to the player's account
    public void addMoney(int amount) {
        money += amount;
    }

    //create a method to subtract money from the player's account
    public void subtractMoney(int amount) {
        money -= amount;
    }

    //create a move method for the player class
    public void move(int spaces) {
        position += spaces;
        position = position % 40;
    }

    //create a getter for the player's position
    public int getPosition() {
        return position;
    }
    //create a getter for the player's name

    public String getName() {
        return name;
    }
    //create a getter for the player's money
    public int getMoney() {
        return money;
    }
    //create a setter for the player's position
    public void setPosition(int position) {
        this.position = position;
    }
}
