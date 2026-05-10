public class threadusingInterface {
    public static void main(String[] args) {
        // Create Runnable object
        TestThread ref = new TestThread();

        // Wrap Runnable inside a Thread object and start it
        Thread t1 = new Thread(ref);
        t1.start();  // ✅ runs in a separate thread

        // Main thread work
//        for (;;) {
//            System.out.println("Main: " + Thread.currentThread().getName());
//        }
    }
}
