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
        service.addFilter(msg -> !msg.isBlank());
        service.addFilter(msg -> msg.length()<=200);
        service.addFilter(msg -> !msg.toLowerCase().contains("spam"));

//TODO 05
        service.addListener(new NotificationListiner(){
            private int successCount;
            private int failCount;

            @Override
            public void onSuccess(String type, String message) {
                successCount++;
                System.out.printf("[AUDIT] OK #%d via %s%n", successCount, type);
            }

            @Override
            public void onFailure(String type, String message, String reason) {
                failCount++;
                System.out.printf("[AUDIT] FAIL #%d via %s -- %s%n", failCount, type, reason);            }

        });


        service.sendAll("Your order #1234 has been shipped!");
        service.sendAll(" ");
        service.sendAll("This is SPAM content");
        service.sendAll("Welcome to our store!");

        service.printHistory();

//TODO 11
        System.out.println("\n--- EMAIL only");
        NotificationService.Result[] emailResults;
        emailResults = service.getByChannel("EMAIL");
        for (NotificationService.Result r : emailResults) {
            System.out.println(r);
        }


//TODO 13
        System.out.println("\n--- Sorted by timestamp (newest first)");
        NotificationService.Result[] successful = service.getSuccessful();
        NotificationService.Result[] sorted = NotificationService.sort(successful, (a, b) ->
                b.getTimestamp().compareTo(a.getTimestamp()));
        for (NotificationService.Result r : sorted) {
            System.out.println(r);
        }

    }
}