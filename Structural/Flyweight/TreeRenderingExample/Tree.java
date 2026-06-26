public class Tree{
    private TreeType tree;
    private int x;
    private int y;
    public Tree(TreeType tree, int x,int y){
        this.tree = tree;
        this.x = x;
        this.y = y;
    }
    public void draw(){
        tree.draw(x,y);
    }
}