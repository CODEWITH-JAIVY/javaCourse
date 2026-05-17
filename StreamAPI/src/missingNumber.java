import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class missingNumber {
/*
Java Streams API program to print missing numbers

Example:
Array:
1,2,4,6

Missing:
3,5
 */

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 4, 6);
        List<Integer> ans = IntStream
                .rangeClosed(arr.get(0), arr.get(arr.size() - 1))
                .filter(num -> !arr.contains(num))
                .boxed()
                .collect(Collectors.toList());

        for (int val : ans) {
            System.out.print(val + " ");
        }

    }
}