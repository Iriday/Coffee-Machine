package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int amountOfWater = 400;
    private int amountOfMilk = 540;
    private int amountOfBeans = 120;
    private int amountOfCups = 9;
    private int amountOfMoney = 550;
    private States state = States.CHOOSING_AN_ACTION;

    CoffeeMachine() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private enum States {
        /* SLEEP_MODE(States.InnerStates.SLEEP),*/ FILLING_MACHINE(InnerStates.ADD_WATER), CHOOSING_AN_ACTION(InnerStates.STANDARD), CHOOSING_A_VARIANT_OF_COFFEE(InnerStates.STANDARD);

        private enum InnerStates {
            //  SLEEP,
            STANDARD,
            //fill
            ADD_WATER, ADD_MILK, ADD_BEANS, ADD_CUPS,
            // choose action
            //BUY, FILL, TAKE, REMAINING, EXIT,
            // choose variant of coffee
            //ESPRESSO, LATTE, CAPPUCCINO, BACK;
        }

        InnerStates innerState;

        States(InnerStates innerState) {
            this.innerState = innerState;
        }

        States() {
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            coffeeMachine.instruction(scn.nextLine());
        }
    }

    public void instruction(String instruction) {
        switch (state) {
            case FILLING_MACHINE:
                fillMachine(instruction);
                break;
            case CHOOSING_AN_ACTION:
                doAction(instruction);
                if (state == States.CHOOSING_AN_ACTION) {
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                }
                break;
            case CHOOSING_A_VARIANT_OF_COFFEE:
                sellBeverage(instruction);
                if (state == States.CHOOSING_AN_ACTION) {
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                }
                break;
        }
    }


    private void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "$%d of money\n", amountOfWater, amountOfMilk, amountOfBeans, amountOfCups, amountOfMoney);
    }

   /* private static String askAction() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            //String chosenAction = scn.next();
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
    }*/

    private void doAction(String action) {
        switch (action) {
            case "buy":
                state = States.CHOOSING_A_VARIANT_OF_COFFEE;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case "fill":
                state = States.FILLING_MACHINE;
                state.innerState = States.InnerStates.ADD_WATER;
                System.out.println("Write how many ml of water do you want to add:");
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

    private boolean checkIfEnoughIngredients(Beverages beverage) {
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

    private void makeBeverage(Beverages beverage) {
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

    private void sellBeverage(String choice) {
        Beverages beverage;
        // while (true) {

        if (choice.equalsIgnoreCase("back")) {
            state = States.CHOOSING_AN_ACTION;
            return;
        }
        beverage = Beverages.getBeverage(choice);
        if (beverage == null) {
            System.out.println("Wrong answer, try again.");
            // continue;
        } // else {
//                break;
//            }
        //}
        boolean enough = checkIfEnoughIngredients(beverage);
        if (enough) {
            makeBeverage(beverage);
        }
        state = States.CHOOSING_AN_ACTION;
    }

    private void fillMachine(String instruction) {

        switch (state.innerState) {
            case ADD_WATER:
                amountOfWater += Integer.parseInt(instruction);
                state.innerState = States.InnerStates.ADD_MILK;
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case ADD_MILK:
                amountOfMilk += Integer.parseInt(instruction);
                state.innerState = States.InnerStates.ADD_BEANS;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case ADD_BEANS:
                amountOfBeans += Integer.parseInt(instruction);
                state.innerState = States.InnerStates.ADD_CUPS;
                System.out.println("Write how many cups of coffee do you want add:");
                break;
            case ADD_CUPS:
                amountOfCups += Integer.parseInt(instruction);
                state = States.CHOOSING_AN_ACTION;
                //state.innerState = States.InnerStates.ADD_WATER;
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
        }
    }

    private void giveMoney() {
        System.out.printf("I gave you $%d\n", amountOfMoney);
        amountOfMoney = 0;
    }
}
