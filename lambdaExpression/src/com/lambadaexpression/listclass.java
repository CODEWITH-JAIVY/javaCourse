package com.lambadaexpression;

import java.util.*;

public class listclass {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>() ;
//        list.add(20) ;
//        list.add(1) ;
//        list.add(2) ;
//        list.add(15) ;
//
//        Collections.sort(list , (a , b ) ->  b-a   );
//        System.out.println(list);
//        Set<Integer>set = new TreeSet<>( (a  , b )-> b-a ) ;
        Set<Integer>set  =  new HashSet<>() ;
        set.add(1);
       set.add(10) ;
       set.add(3) ;
        set.add(5 );
        List<Integer> list = new ArrayList<>(set) ;
       list.sort( (a ,b )-> b -a );
        System.out.println (list  ) ;
    }
}
