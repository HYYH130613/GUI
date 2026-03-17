public class SmsNotification extends BaseNotification{

    public SmsNotification(String phoneNumber) {
        super(phoneNumber);
    }

    @Override
    protected String formatMessage(String message) {
        String text;
        if(message.length() > 160){
             text = message.substring(0, 157) + "...";
        }
        text = message;

        return String.format(
                "SMS to %s %n %s", getRecipient(), text
        );
    }

    @Override
    protected void doSend(String formatted) {
        System.out.println("[SMS] " + formatted);
    }

    @Override
    public String getType() {
        return "SMS";
    }
}
