package part_05_thread_pools.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * ThreadPool ("number of workers in a factory")
 *
 */
class Processor implements Runnable {
    private int id;


    public Processor(int id) {
        this.id = id;
    }


    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        System.out.println("Completed: " + id);
    }
}

public class App {
    public static void main(String[] args) {

        //Created 2 threads, and assign tasks (Processor(i).run) to the threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executor.submit(new Processor(i));
        }

        executor.shutdown();
        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ignored) {}

        System.out.println("All tasks completed.");
    }
}
