package com.predicates;

import java.util.List;

import java.util.function.Predicate;

public class predicatebasic {
    public static void main(String[] args) {
        // predicatre with collection
        List<Integer> arr = List.of(1,2,3,4,5,6,7,8,9,10) ;
        Predicate<Integer>iseven= num -> num  % 2 == 0 ;
        Predicate<Integer>isgreter = num -> num > 5  ;

        // predicate  using or
        Predicate<Integer>evenOrisgreater = iseven.or(isgreter) ;

        arr.stream()
                        .filter(iseven)
                        .forEach(System.out::println );

        System.out.println(iseven.test(6));
        System.out.println(evenOrisgreater.test(10));


    }
}
