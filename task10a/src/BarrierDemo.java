public
    class BarrierDemo {
    
    public static void main(String[] args) {
        System.out.println("=== SYNCHRONIZATION BARRIER DEMO ===\n");

        final int NUMBER_OF_WORKERS = 5;
        CustomBarrier barrier = new CustomBarrier(NUMBER_OF_WORKERS);

        Worker[] workers = new Worker[NUMBER_OF_WORKERS];

        workers[0] = new Worker("Worker-1", barrier, 1000, "Loading data");
        workers[1] = new Worker("Worker-2", barrier, 1500, "Processing");
        workers[2] = new Worker("Worker-3", barrier, 2000, "Validation");
        workers[3] = new Worker("Worker-4", barrier, 1200, "Analysis");
        workers[4] = new Worker("Worker-5", barrier, 1800, "Reporting");

        for (Worker worker : workers) {
            worker.start();
        }

        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=== ALL THREADS COMPLETED ===");
    }
}