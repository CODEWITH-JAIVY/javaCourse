package Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondHieghest {
    // 1. Find the Second Highest Number
    //Given a list of integers, find the second highest distinct number using streams.

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 8, 7, 3);
        Optional<Integer> secondHieght = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();

        System.out.print("secondHieght" + secondHieght);

        System.out.println();

//        numbers.stream()
//                .sorted()
//                .forEach(System.out::println);
    }
}