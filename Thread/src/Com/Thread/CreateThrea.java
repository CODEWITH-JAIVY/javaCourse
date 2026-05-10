package Com.Thread;

public class CreateThrea {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new EvenThread(), "Threas1");
        Thread t2 = new Thread(new OddThred());

        t1.start();
        t1.join(1000);
        t2.start();

    }
}