package StudentComparator;

import java.util.Comparator;

public class sortByName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
//        return compare(o1.name, o2.name);
        return o1.name.compareTo(o2.name);
    }
}