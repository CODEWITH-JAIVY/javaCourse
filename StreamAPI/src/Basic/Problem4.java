package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem4 {
    /**
     * Keep only positive numbers
     * List.of(4, -1, 7, -3, 0, 9, -5, 2)
     */
    public static void main(String[] args) {
        List<Integer> list = List.of(4, -1, 7, -3, 0, 9, -5, 2);
        List<Integer> ans = list.stream()
                .filter(n -> n > 0)
                .collect(Collectors.toList());
        for (int val : ans) {
            System.out.print(val + "  ");
        }
    }
}