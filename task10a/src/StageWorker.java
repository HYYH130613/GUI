public class StageWorker extends Thread{

    private final Stage stage;
    private final Buffer inputBuffer;
    private final Buffer outputBuffer;
    private volatile boolean running;

    public StageWorker(String workerName,Stage stage, Buffer inputBuffer, Buffer outputBuffer) {
       super(workerName);
       this.stage = stage;
       this.inputBuffer = inputBuffer;
       this.outputBuffer = outputBuffer;
       running = true;
    }

    @Override
    public void run() {
        System.out.println("Worker has started, stage: " + stage);

        try{
            while(running){

            }

        }finally {
            stage.release(robot);
        }

    }

}
