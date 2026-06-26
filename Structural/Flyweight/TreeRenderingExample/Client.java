public class Client {
    public static void main(String[] args) {
        Tree oak1 = new Tree(TreeTypeFactory.getTreeType(
                        "Oak","Green","OakTexture",10),10,20);

        Tree oak2 = new Tree(TreeTypeFactory.getTreeType(
                        "Oak","Green","OakTexture",10),50,80);

        Tree oak3 = new Tree(TreeTypeFactory.getTreeType(
                        "Oak","Green","OakTexture",10),120,150);

        Tree pine1 = new Tree(TreeTypeFactory.getTreeType(
                        "Pine","Dark Green","PineTexture",15),200,300);

        Tree pine2 = new Tree(TreeTypeFactory.getTreeType(
                        "Pine","Dark Green","PineTexture",15),250,350);

        System.out.println();

        oak1.draw();
        oak2.draw();
        oak3.draw();

        pine1.draw();
        pine2.draw();

        System.out.println();
        System.out.println("Total TreeTypes created: " + TreeTypeFactory.getTypeCount());
    }
}
