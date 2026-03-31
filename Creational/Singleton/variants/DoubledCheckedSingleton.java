public class DoubledCheckedSingleton {
    private static volatile DoubledCheckedSingleton instance;
    private DoubledCheckedSingleton(){}
    public static DoubledCheckedSingleton getInstance(){
        if(instance==null){
            synchronized (DoubledCheckedSingleton.class){
                if(instance==null){
                    instance = new DoubledCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
