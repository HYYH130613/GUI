public interface NotificationListiner {
    void onSuccess(String type, String message);
    void onFailure(String type, String message, String reason);
}
