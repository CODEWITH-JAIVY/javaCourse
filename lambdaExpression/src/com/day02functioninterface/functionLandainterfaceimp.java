package com.day02functioninterface;


public class functionLandainterfaceimp {

    int calculate( int a , int b , lamdabainterface ob ) {
        return ob.display(a , b );
    }
 public  static int sub (int a , int b ) {
        return  a-b ;
 }

    public static int Compare ( String st , String st1 ) {
        return st.compareTo(st1) ;
    }
    public static void main(String[] args) {
        functionLandainterfaceimp obj = new functionLandainterfaceimp() ;
//        lamdabainterface obj = System.out::println;
//        obj.display("hii jaivy ");

      lamdabainterface add = Integer::sum; // referacing to static method
     lamdabainterface sub  = functionLandainterfaceimp::sub; // referacing to the instant mehtod


       int res = obj.calculate(1 , 4 , add) ;
        System.out.println(res);
        int subs = obj.calculate(2,3, sub);
        System.out.println(subs);


    }
}
