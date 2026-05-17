package IntegerStream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Problem3 {
    public static void main(String[] args) {
        // Find maximum number from list
        List<Integer> nums = Arrays.asList(5, 9, 2, 15, 7);
        OptionalInt max = nums.stream()
                .mapToInt(Integer::intValue)
                .max();

//        System.out.println(max.getAsInt());
        System.out.println(max);
    }
}