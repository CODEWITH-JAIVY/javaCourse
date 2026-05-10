package com.codewithjaivy.Synchronization;

public class countSynchronisation  {
    int cout = 0  ;
    public  synchronized   void  increament() {
        cout++ ;
    }

    public static void main(String[] args) throws InterruptedException {
        countSynchronisation Count = new countSynchronisation() ;
        Thread t1 = new Thread( ()->
        {
            for (int i = 0; i <4000 ; i++) {
                Count.increament();
            }
        }) ;

        Thread t2 = new Thread( ()-> {
            for (int i = 0; i <2000 ; i++) {
                Count.increament();
            }
        }) ;
        t1.start();
        t2.start();
        t1.join();
        System.out.println("Final count " + Count.cout );
    }
}
