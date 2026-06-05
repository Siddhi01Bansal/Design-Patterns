public abstract class NotificationCreator {
    public void sendNotification(String message){
        Notification notification = createNotification();
        notification.sendNotification(message);
    }
    public abstract Notification createNotification();
}
 class EmailNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(){
        return new EmailNotification();
    }
 }
 class SMSNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(){
        return new SMSNotification();
    }
 }
 class PushNotificationCreator extends NotificationCreator {
    @Override
    public Notification createNotification(){
        return new PushNotification();
    }
 }