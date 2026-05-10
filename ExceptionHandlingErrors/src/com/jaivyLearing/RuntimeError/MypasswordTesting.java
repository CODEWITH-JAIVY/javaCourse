package com.jaivyLearing.RuntimeError;

public class  MypasswordTesting {
    public  static  void  Password ( String username , String password )throws  CustomeExceptionForPassword  {
        if( ( ! username.equals("jaivy123") || password.equals("123465") )  )  {
         throw  new CustomeExceptionForPassword("Password and username is anathorise") ;
        }else {
            System.out.println("You have enter successfully ");
        }
    }

    public static void main(String[] args) {
//        MypasswordTesting pass  = new MypasswordTesting() ;
        try {
            Password( "jaivy" , "123") ;
        } catch (CustomeExceptionForPassword e ) {
            System.out.println("Unable to log in " + e.getMessage() );
        }
    }
}
