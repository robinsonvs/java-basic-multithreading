package part_08_wait_notify.demo1;


/**
 *
 * {@link Object#wait()} and {@link Object#notify()} in Java; low-level
 * multi-threading methods of the {@link java.lang.Object} class
 * that allow you to have one or more threads sleeping, only to be woken up by
 * other threads at the right moment. Extremely useful for avoiding those
 * processor-consuming "polling loops".
 * <br><br>
 *
 */
public class App {
    public static void main(String[] args) {

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

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}
    }
}
