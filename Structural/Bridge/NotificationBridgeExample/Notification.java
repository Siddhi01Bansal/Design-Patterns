abstract class Notification {
    protected NotificationSender notificationSender;
    public abstract void send(String message);
    public Notification(NotificationSender notificationSender){
        this.notificationSender = notificationSender;
    }
}