package com.ThreadPoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class factorail    {
    public static int  factoraill(int n) {
        int fac  = 1 ;
        for (int i = 1; i <=n ; i++) {
            fac *= i   ;

        }

        return fac ;
    }
    public static void main(String[] args) {
        ExecutorService executor  = Executors.newFixedThreadPool(10) ;

        for (int i  = 0; i <100 ; i++) {
            int finalI = i;
            int finalI1 = i;
            executor.submit( () ->{
                long  result  = factoraill(finalI) ;
                System.out.println(    "Factorail  of "  + finalI1 +result );
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }) ;
        }
     executor.shutdown();
    }


}
