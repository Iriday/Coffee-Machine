package machine;

public enum Beverages {
    ESPRESSO(250, 0, 16, 4 /*, "Espresso"*/), LATTE(350, 75, 20, 7/*, "Latte"*/), CAPPUCCINO(200, 100, 12, 6/*, "Cappuccino"*/);

    Beverages(int water, int milk, int beans, int price/*, String name*/) {//String recipe
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.price = price;
        // this.name = name;
    }

    private int water;
    private int milk;
    private int beans;
    private int price;
    private String name;

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getPrice() {
        return price;
    }

    public static Beverages getBeverage(String name) {
        switch (name) {
            case "1":
                return ESPRESSO;
            case "2":
                return LATTE;
            case "3":
                return CAPPUCCINO;
            default:
                return null;
        }
    }
}
