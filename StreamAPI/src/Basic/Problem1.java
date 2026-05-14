package Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem1 {
    /**
     * Filter even numbers and double them
     * Given List.of(1,2,3,4,5,6,7,8,9,10), use streams to keep only even numbers, double each one, and collect into a list.
     */

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        List<Integer> res = arr.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());

        for (int val : res) {
            System.out.print(val + "  ");
        }

    }
}