package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem6 {
    /**
     * P3 — Filter evens then double them
     * List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
     */
    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> ans =
                arr.stream()
                        .filter(n -> n % 2 == 0)
                        .map(n -> n * 2)
                        .collect(Collectors.toList());

        for (int val : ans) {
            System.out.print(val + "  ");
        }

    }
}