package part_01_starting_threads.demo2;

/**
 * Starting Threads using Runnable Interface
 */
class Runner implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello = " + i + " Thread: " + Thread.currentThread().getName());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();
    }
}
