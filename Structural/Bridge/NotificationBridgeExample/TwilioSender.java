public class TwilioSender implements NotificationSender{
    @Override 
    public void sendMessage(String message){
        System.out.println("Twilio Sender -> Sending message: " + message);
    }
}
