package IntegerStream;

import java.util.Arrays;
import java.util.List;

public class Problem2 {
    /*
    Find the average of all numbers from the list.
    [10, 15, 20, 25, 30]
     */

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(10, 15, 20, 25, 30);
        int sum = arr.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int count = Math.toIntExact(arr.stream()
                .mapToInt(Integer::intValue)
                .count());
        System.out.println("sum " + sum);
        System.out.println("Count" + count);
        int val = sum / count;
        System.out.println("Average :- " + val);

    }
}