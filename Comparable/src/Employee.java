import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

///   Comparable is used for the natural sorting order not for the custome sorting order
///  Comparable is interface  , in this there is compareTo  (int x , int y  ) method Witch
///  take two integer argument for the natural sorting
///   NOTE :-  if  we want to sort this according to the custome sorting then we can do this
///  with  Comparable  interface compareTo method
public class Employee implements Comparable<Employee> {
    int id;
    String name;
    Double Salary;

    public Employee(int id, String name, Double salary) {
        this.id = id;
        this.name = name;
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Salary=" + Salary +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return Integer.compare(this.id, o.id);
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Sanjeet Kumar ", 20000.0));
        employees.add(new Employee(2, "Jaivy roy", 100000.0));
        employees.add(new Employee(3, "Krishana Kumar ", 325000.0));
        Collections.sort(employees);
        System.out.println(employees);
    }
}