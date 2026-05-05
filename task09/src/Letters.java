import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Letters implements Iterable<Thread> {

    private List<Thread> threads;
    private List<LetterPrinter> printers;

    Letters(String letters) {

        printers = new ArrayList<>();
        threads = new ArrayList<>();

        for (int i = 0; i < letters.length(); i++) {
            LetterPrinter meow =  new LetterPrinter(letters.charAt(i));

            Thread thread = new Thread(meow, "Thread "+letters);
            threads.add(thread);
            printers.add(meow);
        }
    }

    public void start(){
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop(){
        for(LetterPrinter printer : printers) {
            printer.stopRunning();
        }

        for(Thread thread : threads) {
            thread.interrupt();
        }
    }

    public Iterator<Thread> iterator() {
        return threads.iterator();
    }

}
