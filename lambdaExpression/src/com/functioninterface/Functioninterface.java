package com.functioninterface;

import java.util.List;
import java.util.function.Function;

public class Functioninterface {

    public static void main(String[] args) {
        List<String> name = List.of("SAnjeet kumar  ", "Jaivy roy ", " roy ");
        List<Integer> roll = List.of(101, 102, 103);

        Function<String, Integer> namelennght = nam -> nam.length();
        Function<String, String> nameUpper = nam -> nam.toLowerCase();

        Function<String, Integer> combined = nameUpper.andThen(namelennght);

        name.forEach(n -> System.out.println("Original: " + n + " -> Length after transformation: " + combined.apply(n)));
//        Function<String, String> upppercase = nm -> nm.toLowerCase();
//        List<String> newname = name.stream()
//                .map(upppercase)
//                .toList();
//        newname.forEach(System.out::println);


//        Function<String, Integer> namelenght = num -> num.length();

//        name.forEach(n -> System.out.print(namelenght.apply(n) + " "));
//        System.out.println();
//        List<Integer> res = name.stream()
//                .map(namelenght)
//                .toList();
//        res.forEach(System.out::println);

    }
}