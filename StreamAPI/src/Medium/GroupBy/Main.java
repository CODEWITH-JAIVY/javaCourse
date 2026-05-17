package Medium.GroupBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", "Delhi", "F", 28, 90000, true),
                new Employee("Bob", "Engineering", "Mumbai", "M", 35, 85000, true),
                new Employee("Charlie", "HR", "Delhi", "M", 40, 60000, false),
                new Employee("Diana", "HR", "Mumbai", "F", 32, 62000, true),
                new Employee("Eve", "Marketing", "Delhi", "F", 27, 70000, true),
                new Employee("Frank", "Engineering", "Chennai", "M", 45, 95000, true),
                new Employee("Grace", "Marketing", "Mumbai", "F", 30, 72000, false),
                new Employee("Hank", "HR", "Chennai", "M", 38, 58000, true),
                new Employee("Ivy", "Engineering", "Delhi", "F", 26, 88000, true),
                new Employee("Jack", "Marketing", "Chennai", "M", 33, 75000, true)
        );


        ///  Problem 1 — Department wise employees की List बनाओ
        ///  Concept: Basic groupingBy — default VALUE हमेशा List<T> होती है

        Map<String, List<Employee>> grpByEmployye = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        grpByEmployye.forEach((dept, empt) ->
                System.out.println(dept + " -> " + empt)
        );


        ///Problem 2 — Department wise Employee COUNT करो
        Map<String, Long> empCount = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        )
                );
        // count employee according to the department wise
        System.out.println(empCount);
    }
}