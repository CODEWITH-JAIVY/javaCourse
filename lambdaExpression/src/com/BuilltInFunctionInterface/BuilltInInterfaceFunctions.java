package com.BuilltInFunctionInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BuilltInInterfaceFunctions {

    private static List<Integer> Filltrering(List<Integer> arr, Predicate<Integer> predicate) {

        List<Integer>res  = new ArrayList<>() ;
        for(int num  : arr ) {
            if(predicate.test(num)) {
                res.add(num) ;
            }
        }
        return res ;
    }
    public static void main(String[] args) {
        List<Integer>arr = List.of(1,21,3,4,5,6,10 , 100,50,80,30) ;

        Predicate<Integer>isEven = num-> num % 2 == 0  ;
        Predicate<Integer>isGreater  = num  -> num> 10  ;
        Predicate<Integer>isOdd = num -> num % 2 !=  0  ;
        Predicate<Integer>isLess = num->num < 50  ;

        System.out.println(Filltrering(arr , isEven ));
        System.out.println(Filltrering(arr , isOdd));
        System.out.println(Filltrering(arr , isGreater ));
        System.out.println(Filltrering( arr , isLess));
        System.out.println(Filltrering(arr , isGreater.and(isEven)));




    }


}
