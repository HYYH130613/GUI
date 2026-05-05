public class LetterPrinter implements Runnable {
    private char letter;
    private boolean running = true;

    public LetterPrinter(char letter) {
        this.letter = letter;
    }

    public synchronized void stopRunning() {
        running = false;
    }

    private synchronized boolean isRunning() {
        return running;
    }

    public void run(){
        while (isRunning()) {
            System.out.println(letter);

            try{
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                break;
            }
        }
    }

}
