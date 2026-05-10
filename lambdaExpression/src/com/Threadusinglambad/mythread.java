package com.Threadusinglambad;

public class mythread {
    public static void main(String[] args) {
//       myclass  my  = new myclass() ;
//        Thread t1  = new Thread( my) ;
        Runnable runnable = ()->{
            for (int i = 0; i <10 ; i++) {
                System.out.println("HEllo " + Thread.currentThread().getName() + " " +i );
            }
        };
//        t1.start();
        Thread t1  = new Thread(runnable) ;
        t1.run();

    }


}
