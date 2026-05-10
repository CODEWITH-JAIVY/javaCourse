package com.predicates;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class predicate {
    private static List<Integer> Filletering (List<Integer>arr , Predicate<Integer>predicate){

        List<Integer>list = new ArrayList<>() ;
        for( int num : arr) {
          if(predicate.test(num)){
              list.add(num) ;
          }
        }
        return  list ;
    }
    public static void main(String[] args) {
        List<Integer>arr  = List.of(1,2,3) ;

        Predicate<Integer> isEven = num-> num % 2 == 0  ;
        Predicate<Integer>isodd = num -> num % 2 != 0  ;
        Predicate<Integer>greater = num -> num > 5  ;

        System.out.println( " List of odd number \n "+Filletering(arr, isodd));
        System.out.println( "Listof even number \n"+Filletering( arr , isEven));
        System.out.println( "List of greter than tha 5 all number list  \n "+ Filletering (arr , greater));

    }
}
