package com.jaivyLearing.RuntimeError;

public class TestEceptionHandling {
    public  static  void ValidateAge ( int age ) throws  MyCustomEception {
        if( age  < 18  ) {
            throw new MyCustomEception(" age must be 18 or above ") ;
        }else  {
            System.out.println("You are eligiable ");
        }
    }

    public static void main(String[] args) {
        try {
            ValidateAge(18  );
        }catch (MyCustomEception e ) {
            System.out.println("Custom Exception caught " + e.getMessage() );
        }
        System.out.println("End of main  ");
    }
}
