public class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return coffee.getCost() + 20;
    }
    public String getDesc(){
        return coffee.getDesc() + " + Milk";
    }
}
