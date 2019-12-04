package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final int waterOneCup = 200; //ml
    private static final int milkOneCup = 50; //ml
    private static final int beansOneCup = 15; //g
    private static int amountOfWater = 0;
    private static int amountOfMilk = 0;
    private static int amountOfBeans = 0;
    private static int numOfCups = 0;
    private static final Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        /*System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");*/

        fillMachineWithIngredients();

        System.out.println("Write how many cups of coffee you will need:");
        numOfCups = scn.nextInt();
        checkIfEnoughIngredientsToMakeSpecifiedAmountOfCups(numOfCups);

//        countIngredients(scn.nextInt());

    }

    public static void checkIfEnoughIngredientsToMakeSpecifiedAmountOfCups(int cups) {
        int water = amountOfWater;
        int milk = amountOfMilk;
        int beans = amountOfBeans;
        int maxCups = 0;

        // check how many cups machine can make
        for (; true; ) {
            if (water >= waterOneCup && milk >= milkOneCup && beans >= beansOneCup) {
                water -= waterOneCup;
                milk -= milkOneCup;
                beans -= beansOneCup;
                maxCups++;
            } else {
                break;
            }
        }
        if (maxCups == cups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (maxCups > cups) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", maxCups - cups);
        } else {
            System.out.printf("No, I can only make %d cup(s) of coffee\n", maxCups);
        }
    }

    /*public static void countIngredients(int cups) {
        System.out.printf("For %d cups of coffee you will need:\n", cups);
        System.out.println(waterForOneCup * cups + " ml of water");
        System.out.println(milkForOneCup * cups + " ml of milk");
        System.out.println(beansForOneCup * cups + " g of coffee beans");
    }*/

    private static void fillMachineWithIngredients() {
        System.out.println("Write how many ml of water the coffee machine has:");
        amountOfWater = scn.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        amountOfMilk = scn.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        amountOfBeans = scn.nextInt();
    }
}
