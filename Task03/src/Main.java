public
    class Main {

    public static void main(String[] args) {

        NotificationService service = new NotificationService("OnlineStore");

        service.addChannel(new EmailNotification("jan@pj.edu"));
        service.addChannel(new SmsNotification("+48 22 58 44 500"));

//TODO 01: implements Notification interface inline
        service.addChannel(new Notification(){
            @Override
            public void send(String message) {
                System.out.println("[PUSH] "+message);
            }

            @Override
            public String getType() {
                return "PUSH";
            }
        });

//TODO 09: NotificationFilter is @FunctionalInterface

//TODO 05
        service.addListiner(new NotificationListiner(){
            private int successCount;
            private int failCount;

            @Override
            public void onSuccess(String type, String message) {
                successCount++;
                System.out.println(String.format("[%s] %s", type, successCount ));
            }

            @Override
            public void onFailure(String type, String message, String reason) {
                failCount++;
                System.out.println(String.format("[%s] %s %n %s", type, failCount, reason));
            }

        });


        service.sendAll("Your order #1234 has been shipped!");
        service.sendAll("");
        service.sendAll("This is SPAM content");
        service.sendAll("Welcome to our store!");

        service.printHistory();

//TODO 11

//TODO 13

    }
}