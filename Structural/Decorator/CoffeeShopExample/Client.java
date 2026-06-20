public class Client {
    public static void main(String[] args) {
        Coffee order = new Simplecoffee();
        System.out.println("Coffee Cost with order details: " + order.getDesc() + " is: " + order.getCost());
        
        Coffee order1 = new SugarDecorator(new MilkDecorator(new Simplecoffee()));
        System.out.println("Coffee Cost with order details: " + order1.getDesc() + " is: " + order1.getCost());

        Coffee order2 = new WhippedCreamDecorator(new SugarDecorator(new MilkDecorator(new MilkDecorator(new Simplecoffee()))));
        System.out.println("Coffee Cost with order details: " + order2.getDesc() + " is: " + order2.getCost());
    }
}
