class EmailNotification extends Notification{
    public EmailNotification(NotificationSender notificationSender){
        super(notificationSender);
    }
    @Override
    public void send(String message){
        System.out.println("[Email Notification]");
        notificationSender.sendMessage(message);
    }
}