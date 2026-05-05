import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Parking {

    private final int LEVEL = 5;
    private final int SPOTS_PER_LEVEL = 10;
    private final int TOTAL_CAPACITY = LEVEL * SPOTS_PER_LEVEL;

    private Semaphore semaphore;
    private AtomicInteger[] spotsOccupiedPerLevel;
    private AtomicInteger totalOccupied;
    private Object displayLock = new Object();
    private AtomicInteger totalCarsServed;

    long simulationStartTime;

    public Parking() {

        semaphore = new Semaphore(TOTAL_CAPACITY, true);
        spotsOccupiedPerLevel = new AtomicInteger[LEVEL];

        for(int i = 0; i<LEVEL; i++){
            spotsOccupiedPerLevel[i] = new AtomicInteger(0);
        }

        totalOccupied = new AtomicInteger(0);
        totalCarsServed = new AtomicInteger(0);

        simulationStartTime = System.currentTimeMillis();

    }

    public int enter(String carNumber){

        System.out.println(String.format("Car %s is waiting for a free space", carNumber));

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int level = findFreeLevel();

        synchronized (displayLock) {

            spotsOccupiedPerLevel[level].incrementAndGet();
            totalOccupied.incrementAndGet();
            totalCarsServed.incrementAndGet();

            System.out.println("car has parked at level " + level+1);

            displayStatus();

        }
        return level;
    }

    public void exit(String carNumber, int level){

        synchronized (displayLock) {
            spotsOccupiedPerLevel[level].decrementAndGet();
            totalOccupied.decrementAndGet();
            semaphore.release();

            System.out.println(String.format("Car %s has left at level %d", carNumber, level));

            displayStatus();
        }
    }

    private int findFreeLevel(){
        for(int i =0; i<LEVEL; i++){
            int occupied = spotsOccupiedPerLevel[i].get();

            if(occupied < SPOTS_PER_LEVEL){
                return i;
            }
        }

        return 0;
    }

    public void displayStatus(){

        System.out.println("=== PARKING STATUS ===");

        for(int i = 0; i < LEVEL; i++){
            int occupied = spotsOccupiedPerLevel[i].get();
            System.out.println("Level " + (i + 1) + ": ");

            for(int j = 0; j < SPOTS_PER_LEVEL; j++){

                if(j<occupied){
                    System.out.print("X");
                }else{
                    System.out.print("-");
                }
            }

            System.out.println(occupied + "/" + SPOTS_PER_LEVEL );
        }

        System.out.println("Occupied spots: " + totalOccupied.get() + "/" + TOTAL_CAPACITY);
        System.out.println("==================");
        System.out.println();

    }

    public void displayStatistics(){

        long simulationTime = simulationStartTime - System.currentTimeMillis();

        System.out.println("=== FINAL STATIC ===");

        System.out.println("Simulation time: " + simulationTime/1000.0);
        System.out.println("Total cars served: " + totalCarsServed.get());

        int available = semaphore.availablePermits();
        System.out.println("Available spaces: " + available + "/" + TOTAL_CAPACITY);

        System.out.println("==================");
        System.out.println();

    }



}
