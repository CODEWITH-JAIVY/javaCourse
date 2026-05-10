package com.Streamcreation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class streamCreation {

    public static void main(String[] args) {
        List<Integer>num  = Arrays.asList(24,12,52,21,1,2,3,4,5,6,7,8,9,10 , 20 ) ;
          Stream<Integer> l  =  num.stream() ;
          l.forEach(System.out::print );
        try {
            l.filter(x-> x % 2 == 0 )
                    .sorted()
                    .forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
