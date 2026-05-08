public class syncronization {
    int count = 0;

    public void increment() {
        count++;
        System.out.println(count);
    }

    public static void main(String[] args) {
        syncronization obj = new syncronization();
        Thread t1 = new Thread(
                () -> {
                    for (int i = 0; i < 1001; i++) {
                        obj.increment();
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 1001; i++) {
                        obj.increment();
                    }
                }
        );
        t1.start();
        t2.start();
    }


}