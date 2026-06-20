public class WhippedCreamDecorator extends CoffeeDecorator{
    public WhippedCreamDecorator(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return coffee.getCost() + 10;
    }
    public String getDesc(){
        return coffee.getDesc() + " + Whipped Cream";
    }
}
