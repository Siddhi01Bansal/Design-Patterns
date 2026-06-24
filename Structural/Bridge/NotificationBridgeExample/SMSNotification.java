class SMSNotification extends Notification{
    private String message;
    public SMSNotification(NotificationSender notificationSender){
        super(notificationSender);
    }
    @Override
    public void send(String message){
        System.out.println("[SMS Notification]");
        notificationSender.sendMessage(message);
    }
}
