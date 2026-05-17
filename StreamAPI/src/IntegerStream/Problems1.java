package IntegerStream;

import java.util.Arrays;
import java.util.List;

public class Problems1 {
    // Find the sum of numbers in a list using Streams.

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(10, 20, 30, 40);

        int sum = arr.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }
}