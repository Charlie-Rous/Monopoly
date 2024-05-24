public class Player {
    //create a basic player class for the game
    private String name;
    private int money;
    private int position;

    //create a constructor for the player class
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    //create a boolean wants to buy method for the player class
    public boolean wantsToBuy(Property property) {
        if (money >= property.getPrice()) {
            
            System.out.println(name + " bought " + property.getName());
            return true;
        }
        return false;
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
}
