package com.Threadusinglambad;

public class myclass implements  Runnable {

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println("HEllo " + Thread.currentThread().getName() + " " +i );
        }
    }
}
