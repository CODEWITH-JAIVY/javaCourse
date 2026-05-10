package com.localDate;

import java.time.LocalDate;
import java.time.Month;

public class localDate {
    public static void main(String[] args) {
         LocalDate today = LocalDate.now();
        System.out.println(today);

        Month month = today.getMonth();
        int m = month.getValue() ;
        System.out.println(month + " "+ m );
        System.out.println(month);
         int year = today.getYear();
        System.out.println(year);

       LocalDate  mycustomeDate = LocalDate.of(2020, 2  , 12);
        System.out.println(mycustomeDate);


    }
}
