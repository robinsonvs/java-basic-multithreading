package part_13_callable_and_future.demo1;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 *
 * {@link java.util.concurrent.Callable} and
 * {@link java.util.concurrent.Future}
 * in Java to get results from your threads and to allow
 * your threads to throw exceptions. Plus, Future allows you to control your
 * threads, checking to see if they’re running or not, waiting for results and
 * even interrupting them or de-scheduling them.
 * <p>
 * {@link java.lang.Runnable}
 * is the default abstraction for creating a task in Java. It has a single
 * method {@link Runnable#run()}
 * that accepts no arguments and returns no value, nor it can throw
 * any checked exception. To overcome these limitations, Java 5 introduced a new
 * task abstraction through {@link java.util.concurrent.Callable} interface.
 * <br><br>
 *
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //anonymous call of Callable
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);

                if (duration > 2000) {
                    throw new IOException("Sleeping for too long.");
                }

                System.out.println("Starting ...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");

                return duration;
            }
        });

        executor.shutdown();
        //executor.awaitTermination(1, TimeUnit.DAYS);

        try {
            System.out.println("Result is : " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}
