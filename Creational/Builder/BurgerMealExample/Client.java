public class Client{
    public static void main(String[] args) {
        BurgerMeal meal = new BurgerMeal.Builder(PattyType.VEG).addCheese().addColdDrink(ColdDrink.COKE).build();
        System.out.println("Meal is: "+meal);
    }
}