package Com.runable;

public class ThreadCreation {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                System.out.println(i + "  worker 1 ");
            }
        }, " Lambada 1 ");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());

            for (int i = 0; i < 10; i++) {
                System.out.println(i + " worker 2 ");
            }
        }, "Lambada 2 ");

        t1.start();
        t2.start();
    }

}