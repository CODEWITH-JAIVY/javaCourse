package com.executorFramwork;

import java.util.concurrent.*;

public class Main {

    public static int factorial(int num) {

        if (num <= 1) { // handles 0! and 1!
            return 1;
        }
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        long  current_time = System.currentTimeMillis() ;
        ExecutorService executorService = Executors.newFixedThreadPool(9) ;
//        Future<Integer>[] futures = new Future[11];

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit( ()->{
                long result = factorial(finalI) ;
                System.out.println(result);
            }) ;
//            int fac = factorial(i);
//            System.out.println(i + "! = " + fac);
        }
        System.out.println( "Total time " + (System.currentTimeMillis() - current_time ) );
        executorService.shutdown();
    }
}
