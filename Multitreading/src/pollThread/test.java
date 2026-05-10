package pollThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class pollThread implements Runnable {

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Work Done by " + Thread.currentThread().getName());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}

public class test {
    public static void main(String[] args) {
        Runnable r1 = new pollThread();
        Runnable r2 = new pollThread();
        Runnable r3 = new pollThread();
        Runnable r4 = new pollThread();

        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            pool.execute(new pollThread());
        }

        pool.shutdown();
    }

}