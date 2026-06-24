public class Client {
    public static void main(String[] args) {
        NotificationSender snsSender = new SNSSender();
        NotificationSender twilioSender = new TwilioSender();
        Notification notification = new EmailNotification(snsSender);
        notification.send("Hello! Welcome to CarrerGrow");
        System.out.println();
        Notification notification2 = new SMSNotification(twilioSender);
        notification2.send("Your OTP to login is 2311");
        System.out.println("\nSwitching provider...\n");
        Notification notification3 =new EmailNotification(twilioSender);
        notification3.send("Your account has been verified.");
    }
}
