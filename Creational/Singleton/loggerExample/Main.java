package loggerExample;

public class Main {
    public static void main (String[] args){
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println(logger1 == logger2);
        logger1.setLogLevel(LogLevel.ERROR);
        logger1.log(LogLevel.INFO, "This won't print");
        logger1.log(LogLevel.DEBUG, "This won't print");
        logger1.log(LogLevel.ERROR, "This will print");
    }
}
