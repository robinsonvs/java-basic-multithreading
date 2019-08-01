package part_13_callable_and_future.demo1;

import java.util.concurrent.*;

/**
 *
 * Source:
 * <a href="http://java-x.blogspot.com.tr/2006/11/java-5-concurrency-callable-and-future.html">
 * http://java-x.blogspot.com.tr/2006/11/java-5-concurrency-callable-and-future.html
 * </a>
 * <p>
 * Till Java 1.4, threads could be implemented by either implementing
 * {@link java.lang.Runnable} or extending {@link java.lang.Thread}.
 * This was quite simple, but had a serious limitation;
 * They have a run method that cannot return values. In order to side-step this,
 * most programmers use side-effects (writing to a file etc.) to mimic returning
 * values to the invoker of the thread. Java 5 introduces the
 * {@link java.util.concurrent.Callable} interface, that allows users to
 * return values from a thread.
 * </p>
 * <p>
 * {@link java.lang.Runnable} vs {@link java.util.concurrent.Callable} :
 * <ul>
 * <li>
 * Runnable Introduced in Java 1.0. Callable<T> Introduced in Java 1.5 as
 * part of
 * {@link java.util.concurrent} library.
 * </li>
 * <li>
 * Runnable cannot be parametrized .Callable is a parametrized type whose type
 * parameter indicates the return type of its run method Classes implementing.
 * </li>
 * <li>
 * Runnable needs to implement run() method, classes implementing Callable needs
 * to implement call() method.
 * </li>
 * <li>
 * Runnable.run() returns no value, Callable.call() returns a value of Type T.
 * </li>
 * <li>
 * Runnable can not throw checked exceptions, Callable can throw checked
 * exceptions.
 * </li>
 * </ul>
 * </p>
 *
 */
class CallableTestImpl implements Callable<Integer> {
    private int name;

    CallableTestImpl(int name) {
        this.name = name;
    }

    @Override
    public Integer call() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread: " + getName() + " value is : " + i);
        }

        return getName();
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}


public class TestCallable {
    public static void main(String[] args) throws InterruptedException {

        Callable<Integer> callable = new CallableTestImpl(3);
        ExecutorService executor = new ScheduledThreadPoolExecutor(1);
        Future<Integer> future = executor.submit(callable);

        try {
            System.out.println("Future value : " + future.get());
        } catch (Exception ex) {}

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}