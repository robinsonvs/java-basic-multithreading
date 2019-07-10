package part_02_basic_thread_synchronization.demo1;

import java.util.Scanner;

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

        System.out.println("Press return to stop ...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }
}
