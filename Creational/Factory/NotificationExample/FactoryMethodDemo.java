public class FactoryMethodDemo {
    public static void main (String[] args){
        NotificationCreator creator;
        creator = new EmailNotificationCreator();
        creator.sendNotification(
            "Welcome to our Channel!!\nLearn Design Patterns with Siddhi"
        );

        creator = new SMSNotificationCreator();
        creator.sendNotification("Your OTP to login is 4352");

        creator = new PushNotificationCreator();
        creator.sendNotification("Siddhi will be live with design Patterns in 10 minutes");
    }
}
