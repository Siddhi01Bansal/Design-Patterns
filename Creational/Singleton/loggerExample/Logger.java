package loggerExample;

public class Logger {
    private LogLevel currLevel;
    private Logger(){
        this.currLevel = LogLevel.INFO;
    }
    private static class Holder{
        private static Logger instance = new Logger();
    }
    public static Logger getInstance(){
        return Holder.instance;
    }
    public void setLogLevel(LogLevel level){
        this.currLevel = level;
    }
    public void log(LogLevel level, String message){
        if(level.getPriority()>=this.currLevel.getPriority()){
            System.out.println("["+level+"]: "+message);
        }
    }
}
