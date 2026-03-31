public class LoggerThreadSafe {
    private static LoggerThreadSafe instance;
    private LoggerThreadSafe(){}
    public static synchronized LoggerThreadSafe getInstance(){
        if(instance==null) instance = new LoggerThreadSafe();
        return instance;
    }
}
