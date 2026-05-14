package Basic;

import java.util.List;
import java.util.OptionalInt;

public class Problem10 {
    /**
     * Find the longest word (anyMatch + map)
     * Combine filter with a boolean terminal operation — no collect needed here.
     * List.of("apple", "kiwi", "banana", "fig", "watermelon", "plum")
     *
     */

    public static void main(String[] args) {
        List<String> fruits = List.of(
                "apple", "kiwi", "banana", "fig", "watermelon", "plum"
        );

        OptionalInt max = fruits.stream()
                .mapToInt(String::length) // String to Integer
                .max();
//        int maxlength = max.getAsInt();


        String longest = fruits.stream()
                .max((a, b) -> Integer.compare(a.length(), b.length()))
                .orElse(" ");
        System.out.println(longest);


    }
}