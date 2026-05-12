import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final Queue<Robot> queue;
    private final int capacity;
    private final String name;
    private boolean closed;

    public Buffer(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.closed = false;
    }

    public synchronized void put(Robot robot) throws InterruptedException {
        while(queue.size() >= capacity && !closed) {
            System.out.println("Buffer is full");
            wait();
        }
        if(closed) {
            throw new InterruptedException("Buffer " + name);
        }

        queue.add(robot);
        robot.setCurrentStatus("WAITING_IN_"+name);

        System.out.println("Adding the robot to the buffer "+queue.size()+" "+capacity);

        notifyAll();
    }

    public synchronized Robot take() throws InterruptedException {
        while(queue.isEmpty() && !closed) {
            System.out.println("Buffer is empty");
            wait();
        }

        if(queue.isEmpty() && closed) {
            return null;
        }

        Robot robot = queue.poll();
        System.out.println("Taking the robot from the buffer "+queue.size()+" "+capacity);
        notifyAll();
        return robot;
    }

    public synchronized void close() {
        closed = true;
        System.out.println("Buffer has been closed");
        notifyAll();
    }

    public synchronized int size(){
        return queue.size();
    }

    public synchronized boolean isClosed() {
        return closed;
    }

    public synchronized String getName() {
        return name;
    }

}
