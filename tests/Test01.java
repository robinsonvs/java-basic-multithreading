package tests;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println("Integer random is : " + random.nextInt(10));
        }
        //semaphore , number of permits
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        semaphore.release();
        semaphore.acquire();
        System.out.println("Available permits " + semaphore.availablePermits());

    }
}
