public class TreeType {
    private final String type;
    private final String color;
    private final String texture;
    private final int height;
    public TreeType(String type, String color, String texture, int height){
        this.type = type;
        this.color = color;
        this.texture = texture;
        this.height = height;
        System.out.println("Creating a new treetype: "+type);
    }
    void draw(int x,int y){
        System.out.println("Drawing " + type +
        " tree at (" + x + ", " + y + ")" +
        " | Color: " + color +
        " | Texture: " + texture +
        " | Height: " + height + "m");
    }
}
