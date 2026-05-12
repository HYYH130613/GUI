public class Robot {

    private static int counter = 0;
    private final int id;
    private final String name;
    private String currentStatus;
    private final long createdAt;

    public Robot(){
        counter++;
        id = counter;
        name = String.format("Robot-%03d", id);
        currentStatus = "Created";
        createdAt = System.currentTimeMillis();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public long getCreatedAt(){
        return createdAt;
    }

    public String getCurrentStatus(){
        return currentStatus;
    }

    public void setCurrentStatus(String status){
        currentStatus = status;
    }

    @Override
    public String toString(){
        return name;
    }

}
