package com.practisecodewithjaivy;

public class synchronization {

    public static void main(String[] args) {
        count con = new count();
        // task is assing in thread 1
        Thread t1 = new Thread ( () -> {
            for (int i = 0; i <1000 ; i++) {
                con.increment ();  ;
            }
        })  ;
        //  task is assing in other thread
        Thread t2 = new Thread( () ->{
            for (int i = 0; i <1000 ; i++) {
                con.increment(); ;
            }
        }) ;
        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(con.count);
    }
}
