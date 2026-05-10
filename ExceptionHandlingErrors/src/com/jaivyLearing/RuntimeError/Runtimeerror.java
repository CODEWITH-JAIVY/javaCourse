package com.jaivyLearing.RuntimeError;

public class Runtimeerror {
//    int arr  [] = new int[Integer.MAX_VALUE ] ; // it's run time error where memory has been full in the stack
    public   void method() {
        method();
    }

    public static void main(String[] args) {
       Runtimeerror obj  = new Runtimeerror() ;
    obj.method() ;
    }

}


