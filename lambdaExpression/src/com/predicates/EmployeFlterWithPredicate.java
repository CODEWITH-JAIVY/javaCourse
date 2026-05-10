package com.predicates;

import java.util.List;
import java.util.function.Predicate;

public class EmployeFlterWithPredicate {
    static class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
    }



    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("John", 50000),
                new Employee("Alice", 80000),
                new Employee("Bob", 30000)
        );
        Predicate<Employee>sallayGreteThan50k  = e ->  e.salary>= 50000  ;

       employees.stream()
               .filter(sallayGreteThan50k)
               .map(e->e.name)
               .forEach(System.out::println);
    }
}
