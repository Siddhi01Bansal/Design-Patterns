import java.util.HashMap;

public class PrototypeRegistry<T extends Prototype<T>> {
    private HashMap<String, T>prototypes = new HashMap<>();
    public void register(String key, T prototype){
        prototypes.put(key,prototype);
    } 
    public T get(String Key){
        return prototypes.get(Key).clone();
    }
}
