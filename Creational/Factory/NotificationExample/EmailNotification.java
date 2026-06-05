public class EmailNotification implements Notification {
    public void sendNotification (String message){
        System.out.println("Message sent via email: "+ message);
    }
}
