package com.predicates;

import java.util.function.Predicate;

public class Real_World_Examples {
    public static void main(String[] args) {
        // validate username
//
//        Predicate<String>predicate = name -> name != null && name.length()>3  ;
//        System.out.println(predicate.test("AL"));

        // compare two String when the user inter there userid
        Predicate<String >predicateusername  = name ->name!= null &&  name.equals("sanjeet") ;
        System.out.println(predicateusername.test("sanjeet"));
        System.out.println(predicateusername.test("SAnjeet"));

    }
}
