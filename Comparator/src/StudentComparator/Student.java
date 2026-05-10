package StudentComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    int id;
    String name;
    float mark;

    public Student(int id, String name, float mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Sanjeet kumar ", 499));
        students.add(new Student(2, "Jaivy roy   ", 450));
        students.add(new Student(3, "Krishana  kumar ", 200));
        students.add(new Student(4, "Roy   kumar ", 360));
        students.add(new Student(5, "sunil  kumar ", 500));
        students.add(new Student(6, "kumar ", 1000));

        Collections.sort(students, new sortByName());
        System.out.println(students);

//        students.sort(Comparator.comparing(s -> s.name));
//        System.out.println(students);
//        students.sort(Comparator.comparing(student -> student.mark));
//        System.out.println("______________________________________________________");
//        System.out.println(students);

    }
}