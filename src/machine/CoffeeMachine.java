package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private static final int waterForOneCup = 200; //ml
    private static final int milkForOneCup = 50; //ml
    private static final int beansForOneCup = 15; //g

    public static void main(String[] args) {
        /*System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");*/

        Scanner scn = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        countIngredients(scn.nextInt());

    }

    public static void countIngredients(int cups) {
        System.out.printf("For %d cups of coffee you will need:\n", cups);
        System.out.println(waterForOneCup * cups + " ml of water");
        System.out.println(milkForOneCup * cups + " ml of milk");
        System.out.println(beansForOneCup * cups + " g of coffee beans");

    }
}
