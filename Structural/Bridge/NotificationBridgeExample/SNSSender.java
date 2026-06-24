public class SNSSender implements NotificationSender{
    @Override 
    public void sendMessage(String message){
        System.out.println("AWS SNS Sender -> Sending message: " + message);
    }
}
