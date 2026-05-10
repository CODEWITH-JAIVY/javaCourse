package com.lambadaexpression;

import java.util.concurrent.atomic.AtomicInteger;

public class localvariable {
    public static void main(String[] args) {
         AtomicInteger a = new AtomicInteger(10);
        Runnable runnable  = () -> {
           a.set(10);
        } ;
    }
}
