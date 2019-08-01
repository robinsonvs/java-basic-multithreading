package part_09_a_worked_example_using_low_level.demo1;


/**
 *
 * How to implement the Producer-Consumer pattern using "low level" techniques;
 * namely, wait, notify and synchronized. This isn't the best way to implement a
 * Producer-Consumer pattern in Java
 * (see tutorial 7 use of {@link java.util.concurrent.BlockingQueue} for
 * the best way); but this tutorial will help you to understand how to use wait
 * and notify.
 * <br><br>
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException ignored) {}
            }
        });

        t1.start();
        t2.start();

        //t1.join();
        //t2.join();

        // Pause for 30 seconds and force quitting the app (because we're
        // looping infinitely)
        Thread.sleep(30000);
        System.exit(0);
    }
}
