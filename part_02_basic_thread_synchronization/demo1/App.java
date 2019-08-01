package part_02_basic_thread_synchronization.demo1;

import java.util.Scanner;

/**
 *
 * Volatile Keyword, the volatile modifier guarantees that any thread that
 * reads a field will see the most recently written value.
 */
class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {

        while(running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();

        // Wait for the enter key
        System.out.println("Press return to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        processor.shutdown();
    }
}
