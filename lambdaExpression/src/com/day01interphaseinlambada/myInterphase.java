package com.day01interphaseinlambada;

@FunctionalInterface
public interface myInterphase {
    void hello() ;

    default void bye() {
        System.out.println("Hii i am   interphase default methods ");
    }
    public  static void goodbye() {
        System.out.println("Hii i am static method of the interphase " );
    }
}
