import java.util.Random;
import java.util.SortedMap;

public class Car implements Runnable{
    private String number;
    private Parking parking;
    private Random random = new Random();

    public Car(String number, Parking lot) {
        this.number = number;
        this.parking = lot;
    }

    public void run(){
        try{
            Thread.sleep(random.nextInt(3000));
            int level = parking.enter(number);
            int parkingTime = 2000+random.nextInt(3000);

            Thread.sleep(parkingTime);

            parking.exit(number, level);

        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error message");
        }
    }
}
