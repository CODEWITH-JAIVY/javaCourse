package Medium;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployees {

    // Employee class
    record Employee(String name, String department, double salary) {
        // Constructor

        @Override
        public String toString() {
            return name + " (" + department + ", ₹" + salary + ")";
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 90000),
                new Employee("Bob", "Engineering", 85000),
                new Employee("Charlie", "HR", 60000),
                new Employee("Diana", "HR", 62000),
                new Employee("Eve", "Marketing", 70000),
                new Employee("Frank", "Engineering", 95000),
                new Employee("Grace", "Marketing", 72000)
        );

        // Group employees by department
        Map<String, List<Employee>> groupByemployee = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));   /// WITH OUT DOWNSTREAM

        ///   WITH DOWN STREAM
        // counting()
        Map<String, Long> groupByemployeeWithDownStream = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,  // KEY   → String
                        Collectors.counting()     // VALUE → Long
                ));


        // Count employees in each department


        // Print grouped employees
        System.out.println("Employees grouped by department:");
        groupByemployee.forEach((dept, emplist) ->

                System.out.println(dept + " -> " + emplist));

        ///   // Print grouped employees

        System.out.println(" Nothing change but interanl Working  of the group by  ");
        groupByemployeeWithDownStream.forEach((dept, emplist) ->

                System.out.println(dept + " -> " + emplist));


        // Print employee counts

    }
}