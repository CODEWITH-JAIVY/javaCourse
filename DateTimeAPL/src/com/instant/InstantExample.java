package com.instant;

import java.time.Duration;
import java.time.Instant;

public class InstantExample {
    public static void main(String[] args) {
        Instant start = Instant.now();

        int sum = 0;
        for (double  i = 0; i < 1000000000; i++) {

        }
        for (double  i = 0; i < 1000000000; i++) {

        }
        for (double  i = 0; i < 1000000000; i++) {

        }

        Instant end = Instant.now();
        Duration d1 = Duration.between(start, end);

        System.out.println("Total Sum: " + sum);
        System.out.println("Duration in millis: " + d1.toMillis() + " ms");
        System.out.println("Duration in seconds: " + d1.toSeconds() + " sec");
    }
}
