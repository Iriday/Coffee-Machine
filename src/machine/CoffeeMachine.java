package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static int amountOfWater = 400;
    private static int amountOfMilk = 540;
    private static int amountOfBeans = 120;
    private static int amountOfCups = 9;
    private static int amountOfMoney = 550;
    private static final Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {


        while (true) {
            String action = askAction();
            doAction(action);
        }
    }

    private static void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "$%d of money\n", amountOfWater, amountOfMilk, amountOfBeans, amountOfCups, amountOfMoney);
    }

    private static String askAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String chosenAction = scn.next();
            if (chosenAction.equalsIgnoreCase("buy")
                    || chosenAction.equalsIgnoreCase("fill")
                    || chosenAction.equalsIgnoreCase("take")
                    || chosenAction.equalsIgnoreCase("remaining")
                    || chosenAction.equalsIgnoreCase("exit")) {
                return chosenAction;
            } else {
                System.out.println("Not a valid action, try again.");
            }
        }
    }

    private static void doAction(String action) {
        switch (action) {
            case "buy":
                sellBeverage();
                break;
            case "fill":
                fillMachine();
                break;
            case "take":
                giveMoney();
                break;
            case "remaining":
                printStatus();
                break;
            case "exit":
                System.exit(0);
        }
    }

    private static boolean checkIfEnoughIngredients(Beverages beverage) {
        if (amountOfWater - beverage.getWater() < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (amountOfMilk - beverage.getMilk() < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (amountOfBeans - beverage.getBeans() < 0) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else if (amountOfCups == 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        } else {
            return true;
        }
    }

    private static void makeBeverage(Beverages beverage) {
        amountOfWater -= beverage.getWater();
        amountOfMilk -= beverage.getMilk();
        amountOfBeans -= beverage.getBeans();
        amountOfCups--;
        amountOfMoney += beverage.getPrice();
        System.out.println("I have enough resources, starting to make a coffee!");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        if (beverage.getMilk() != 0) {
            System.out.println("Pouring some milk into the cup");
        }
        System.out.println("Coffee is ready!");
    }

    private static void sellBeverage() {
        Beverages beverage;
        while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String choice = scn.next();
            if (choice.equalsIgnoreCase("back")) {
                return;
            }
            beverage = Beverages.getBeverage(choice);
            if (beverage == null) {
                System.out.println("Wrong answer, try again.");
                continue;
            } else {
                break;
            }
        }
        boolean enough = checkIfEnoughIngredients(beverage);
        if (enough) {
            makeBeverage(beverage);
        } else {
            return;
        }

    }

    private static void fillMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        amountOfWater += scn.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        amountOfMilk += scn.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        amountOfBeans += scn.nextInt();
        System.out.println("Write how many cups of coffee do you want add:");
        amountOfCups += scn.nextInt();
    }

    private static void giveMoney() {
        System.out.printf("I gave you $%d\n", amountOfMoney);
        amountOfMoney = 0;
    }
}
