package com.day01interphaseinlambada;

@FunctionalInterface
public interface child  {
    void sayhello();

    public static void main(String[] args) {
        child ch = new child() {
            @Override
            public void sayhello() {

            }
        };
    }
}
