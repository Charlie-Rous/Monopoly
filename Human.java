public class Human extends Player {
    
    public Human(String name, int money) {
        super(name, money);
        
    }

    public boolean wantsToBuy(Property property) {
        if (getMoney() >= property.getPrice()) {
            String answer = takeInput("Would you like to buy " + property.getName() + " for $" + property.getPrice() + "? (y/n)");
            if (answer.equals("y")) {
                return true;
            }
            
        }
        return false;
    }

    public String takeInput(String prompt) {
        System.out.println(prompt);
        boolean validInput = false;
        String output = "";
        while (!validInput) {
            String input = System.console().readLine();
            if (input.equals("y") || input.equals("n")) {
                output = input;
                validInput = true;
            } else {
                System.out.println("Invalid input");
            }
        }
        return output;
    }

    public String monopolyToBuild() {

        for (Property property : properties) {
            if (property instanceof RealEstate && property.isMortgaged() == false) {
                int numProperties = 3;
                RealEstate realEstate = (RealEstate) property;

                if (realEstate.getMonopoly().equals("Dark Blue") || realEstate.getMonopoly().equals("Brown")) {
                    numProperties = 2;
                }
                if (getMoney() >= realEstate.getPricePerHouse() * numProperties && realEstate.getNumHouses() < 5
                        && hasMonopoly(realEstate.getMonopoly())) {
                    return realEstate.getMonopoly();
                }
            }

        }
        return null;
    }

    public String wantsToTradeFor() {
        
        String answer = takeInput("Would you like to trade for a property? (y/n)");
        if (answer.equals("y")) {
            System.out.println("Which monopoly would you like to trade for?");
            String monopoly = System.console().readLine();
            if (monopoly.equals("Brown") || monopoly.equals("Dark Blue") || monopoly.equals("Pink") || monopoly.equals("Light Blue") || monopoly.equals("Orange") || monopoly.equals("Red") || monopoly.equals("Yellow") || monopoly.equals("Green")) {
                return monopoly;
            } else {
                System.out.println("Invalid input");
                return null;
            }
        }
        return null;
    }

}
