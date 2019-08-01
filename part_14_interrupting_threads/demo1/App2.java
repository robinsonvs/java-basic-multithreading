package part_14_interrupting_threads.demo1;

import java.util.concurrent.*;

public class App2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<?> future = executorService.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                for (int i = 0; i < 1E8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.printf("Interrupted at %d ", i);
                        break;
                    }
                }

                return null;
            }
        });

        executorService.shutdown();
        Thread.sleep(500);

        /*
        in this example, there are different ways you can interrupt a thread
        execution.
         */

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#cancel-boolean-
        //future.cancel(true);

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdownNow--
        executorService.shutdownNow();

        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished");
    }
}
