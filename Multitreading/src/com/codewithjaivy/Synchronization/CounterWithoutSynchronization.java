package com.codewithjaivy.Synchronization;

// this is the example  of the race  condition where two thread t1 and t2  are increment  a
// share resourse count  variabe
public class CounterWithoutSynchronization {
    int count = 0;

    public void increament() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        CounterWithoutSynchronization cnt = new CounterWithoutSynchronization() ;
        Thread t1  = new Thread( () -> {
            for (int i = 0; i < 2000; i++) {
                cnt.increament();
            }
        });
        Thread t2 = new Thread( ()->
        {
            for (int i = 0; i < 2000; i++) {
                cnt.increament();
            }
        }) ;

        t1.start();
        t2.start();
        t1.join();   // ✅ wait until t1 finishes
        t2.join();   // ✅ wait until t2 finishes
        System.out.println( "Final count  "+cnt.count);
    }
}
