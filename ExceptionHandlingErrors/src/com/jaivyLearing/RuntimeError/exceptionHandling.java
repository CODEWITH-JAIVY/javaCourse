package com.jaivyLearing.RuntimeError;

import java.util.Scanner;

public class exceptionHandling {
    public static void main(String[] args) {
        try{
            Scanner scanner  = new Scanner(System.in) ;
            System.out.println("Enter 1st number ");
            int num1  = scanner.nextInt() ;
            System.out.println("Enter second number ");
            int num2  = scanner.nextInt() ;
            int result  = num1 / num2  ;
            System.out.println("Result is " +  result );
        }catch (ArithmeticException er ) {
            System.out.println("Number 2 cann't be negative or zero  " +  er );
        }


        System.out.println("Exixt  from  main");
    }
}
