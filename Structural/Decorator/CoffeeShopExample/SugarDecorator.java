public class SugarDecorator extends CoffeeDecorator{
    public SugarDecorator(Coffee coffee){
        super(coffee);
    }
    public int getCost(){
        return coffee.getCost() + 10;
    }
    public String getDesc(){
        return coffee.getDesc() + " + Sugar";
    }
}
