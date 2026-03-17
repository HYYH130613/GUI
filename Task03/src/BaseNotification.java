abstract class BaseNotification implements Notification{
    private String recipient;

    public BaseNotification(String recipient){
        this.recipient = recipient;
    }

    public String getRecipient(){
        return recipient;
    }

    protected abstract String formatMessage(String message);

    protected abstract void doSend(String formatted);

    public void send(String message){
        String formatted = formatMessage(message);
        doSend(formatted);
    }
}
