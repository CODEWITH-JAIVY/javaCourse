package com;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class Consumers {
    static class Student {
        String name;
        double marks;

        Student(String name, Double marks) {
            this.name = name;
            this.marks = marks;
        }
    }


    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(

                new Student("SAnjeet kumar ", 60.0),
                new Student("jaivy roy", 80.0),
                new Student("Jaivy Sanjeet kumar ", 100.2)
        );
//        Consumer<Student> studentConsumer = std -> System.out.println(std.name + " " + std.marks);
//        studentConsumer.accept(studentList);

        // using combine the student is fail or pass
        Consumer<Student> studentname = std1 -> System.out.print(std1.name + " ");
        Consumer<Student> studenresult = std2 -> {
            if (std2.marks < 60) System.out.println("Fail");
            else System.out.println("Pass");
        };

        Consumer<Student> combined = studentname.andThen(studenresult);

        studentList.forEach(combined);

//        studentList.forEach(std -> System.out.println(std.name + " " + std.marks));
//        List<String>list = Arrays.asList("Sanjeet kumar " , "jaivy roy " , "Roy") ;
        //Consumer<String >consumer  = System.out::println;

//        consumer.accept("Sanjeet kuamr ");
        //   list.forEach(name-> System.out.println(list));
//        Consumer<String>names = name-> System.out.println(name) ;

//        list.forEach(name-> System.out.println(name));
    }


}