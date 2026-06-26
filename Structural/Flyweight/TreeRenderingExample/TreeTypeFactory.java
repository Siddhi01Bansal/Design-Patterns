import java.util.Map;
import java.util.HashMap;
public class TreeTypeFactory {
    private static Map<String, TreeType> treeTypes = new HashMap<>();
    public static TreeType getTreeType(String type, String color, String texture, int height){
        String key = type+"_"+color+"_"+texture+"_"+height;
        treeTypes.putIfAbsent(key, new TreeType(type, color, texture, height));
        return treeTypes.get(key);
    }
    public static int getTypeCount(){
        return treeTypes.size();
    }
}
