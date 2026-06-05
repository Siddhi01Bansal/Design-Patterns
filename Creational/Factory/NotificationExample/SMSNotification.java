public class SMSNotification implements Notification {
    public void sendNotification (String message){
        System.out.println("Message sent via SMS: "+ message);
    }
}