package com.ThreadPoolExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolExample1 {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit 5 tasks to the pool
        for (int i = 1; i <= 1000; i++) {
            int taskId = i;
                 Future<?>future  = executor.submit(() -> {
                System.out.println("Task " + taskId +
                        " is running on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000); // simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Shutdown the pool (important!)
        executor.shutdown();
    }
}
