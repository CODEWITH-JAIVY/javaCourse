package Com.JoinMethod;

public class Join {
    static int count = 0;

    public static synchronized void count() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count();
            }
        });

        thread2.start();
        thread1.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}