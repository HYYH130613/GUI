public class CustomBarrier {

    private final int limit;
    private int count = 0;
    private int generation = 0;

    public CustomBarrier(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("check the program output");
        }

        this.limit = limit;
    }

    public synchronized void await() throws InterruptedException{
        int currentGeneration = generation;
        count++;

        System.out.println(String.format("current thread: %s current number: %d generation id: %d",
                Thread.currentThread().getName(),
                count,
                currentGeneration
                )
        );

        if(count < limit) {
            while(currentGeneration == generation && count < limit) {
                wait();
            }
        } else {
            System.out.println("the Barrier has been reached!");
            count = 0;
            generation++;
            notifyAll();
        }
    }

    public synchronized int getWaitingCount(){
        return count;
    }

    public synchronized int getGeneration(){
        return generation;
    }

    public synchronized void reset(){
        count = 0;
        generation++;
        notifyAll();
    }


}
