package Com.runable;

public class MySecond implements Runnable {
    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}