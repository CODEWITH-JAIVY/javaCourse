import java.util.*;
import java.util.stream.Collectors;

public class salaries_by_department {
    public static void main(String[] args) {
        // ✅ Define Employee class
        class Employee {
            String dep;
            int salary;

            Employee(String dep, int salary) {
                this.dep = dep;
                this.salary = salary;
            }
        }

        // ✅ Create employee list OUTSIDE the class
        List<Employee> employees = Arrays.asList(
                new Employee("IT", 5000),
                new Employee("Account", 6000),
                new Employee("IT", 2000),
                new Employee("HR", 3000),
                new Employee("Account", 4000)
        );

        // ✅ Group by department and sum salaries
        Map<String, Integer> salaryByDep = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.dep,                        // group key
                        Collectors.summingInt(e -> e.salary) // aggregation
                ));

        System.out.println(salaryByDep);
    }
}
