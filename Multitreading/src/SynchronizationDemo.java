public class SynchronizationDemo {

    int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {

        SynchronizationDemo obj = new SynchronizationDemo(); // shared object

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                obj.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                obj.increment();
            }
        });

        t1.start();
        t2.start();
    }
}