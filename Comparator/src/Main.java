import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Employee> list = new ArrayList<>();

        list.add(new Employee(3, "Ravi", 40000));
        list.add(new Employee(1, "Amit", 60000));
        list.add(new Employee(2, "Suresh", 50000));

        Collections.sort(list, new NameComparator());

        for (Employee e : list) {
            System.out.println(e);
        }
    }
}