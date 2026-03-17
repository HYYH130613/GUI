public class EmailNotification extends BaseNotification {

    public EmailNotification(String email){
        super(email);
    }

    @Override
    protected String formatMessage(String message) {
        String formatted = String.format("Subject: Notification | Recipient: %s %n %s", getRecipient(), message);
        return formatted;
    }

    @Override
    protected void doSend(String formatted) {
        System.out.println("[EMAIL] " + formatted);
    }

    @Override
    public String getType() {
        return "EMAIL";
    }

}
