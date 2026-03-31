public class basicSingleton {
    private static basicSingleton instance;
    private basicSingleton(){}
    public static basicSingleton getInstance(){
        if(instance==null) instance = new basicSingleton();
        return instance;
    }
}
