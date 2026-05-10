class TestThread implements Runnable {
    @Override
    public void run() {
        System.out.println("I am running in a separate thread using interface!");
        for (;;) {
            System.out.println("Child: " + Thread.currentThread().getName());
        }
    }
}