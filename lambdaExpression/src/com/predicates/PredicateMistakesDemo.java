package com.predicates;

import java.util.List;
import java.util.function.Predicate;

public class PredicateMistakesDemo {

    static class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {

        // Sample data
        List<Employee> employees = List.of(
                new Employee("John", 50000),
                new Employee("Alice", 80000),
                new Employee("Bob", 30000),
                new Employee(null, 40000)
        );

        List<String> names = List.of("Alice", "Bob", null);

        List<Integer> numbers = List.of(1, 2, 3, 10, 20);

        // =========================================
        // 1️⃣ Type Mismatch in Predicate
        // Mistake: Using Predicate<Integer> on Stream<Employee>
        // Predicate<Integer> wrongPred = n -> n > 50000; // ❌
        Predicate<Employee> salaryGreaterThan50k = e -> e.salary > 50000; // ✅

        System.out.println("Employees with salary > 50k:");
        employees.stream()
                .filter(salaryGreaterThan50k)
                .forEach(e -> System.out.println(e.name));

        // =========================================
        // 2️⃣ Using compareTo incorrectly
        // Mistake: returns int instead of boolean
        // Predicate<String> wrongComp = s -> s.compareTo("Alice"); // ❌
        Predicate<String> nameAfterAlice = s -> s != null && s.compareTo("Alice") > 0; // ✅

        System.out.println("\nNames after 'Alice':");
        names.stream()
                .filter(nameAfterAlice)
                .forEach(System.out::println);

        // =========================================
        // 3️⃣ Mixing lambda and method reference incorrectly
        // Mistake: n -> System.out::println(n) // ❌
        System.out.println("\nPrint names correctly:");
        names.stream()
                .filter(n -> n != null)
                .forEach(System.out::println); // ✅ lambda or use method reference

        // =========================================
        // 4️⃣ Null checks not handled
        Predicate<String> startsWithA = s -> s != null && s.startsWith("A"); // ✅ safe

        System.out.println("\nNames starting with A:");
        names.stream()
                .filter(startsWithA)
                .forEach(System.out::println);

        // =========================================
        // 5️⃣ Using wrong field
        // Mistake: e -> e.age > 30 // ❌ Employee has no age
        Predicate<Employee> highSalary = e -> e.salary > 50000; // ✅ correct

        System.out.println("\nEmployees with high salary:");
        employees.stream()
                .filter(highSalary)
                .forEach(e -> System.out.println(e.name));

        // =========================================
        // 6️⃣ Filter on wrong type
        // Mistake: n -> n.length() > 1 on Integer // ❌
        Predicate<Integer> greaterThan10 = n -> n > 10; // ✅ correct

        System.out.println("\nNumbers > 10:");
        numbers.stream()
                .filter(greaterThan10)
                .forEach(System.out::println);

    }
}
