package part_04_multiple_locks_using_synchronized.demo1;

public class App {
    public static void main(String[] args) {
        System.out.println("Synchronized objects");
        Worker worker = new Worker();
        worker.main();
        System.out.println("Synchronized methods");
        WorkerSynchronizedMethods worker2 = new WorkerSynchronizedMethods();
        worker2.main();
    }
}
