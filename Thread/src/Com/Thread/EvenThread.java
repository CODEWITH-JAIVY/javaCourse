package Com.Thread;

public class EvenThread extends Thread {

    @Override
    public void run() {
        Thread.yield();

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}