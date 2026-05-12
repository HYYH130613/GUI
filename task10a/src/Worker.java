public class Worker extends Thread {

    private final CustomBarrier barrier;
    private final int taskDuration;
    private final String taskName;

    public Worker(String name, CustomBarrier barrier, int taskDuration, String taskName){

        super (name);
        this.barrier = barrier;
        this.taskDuration = taskDuration;
        this.taskName = taskName;
    }

    @Override
    public void run(){
        try{
            executeTask(1);
            barrier.await();

            executeTask(2);
            barrier.await();

            executeTask(3);
            barrier.await();

            System.out.println("Current thread has complete all phases");
        } catch (InterruptedException e){
            System.out.println("Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }

    private void executeTask(int phase) throws InterruptedException{
        System.out.println("Current thread is starting the specified phase: "+phase);
        Thread.sleep(taskDuration);
        System.out.println("Current thread has complete current phase");
    }

}
