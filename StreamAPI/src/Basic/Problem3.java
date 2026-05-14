package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem3 {
    /**
     * Count words longer than 3 characters
     * Given List.of("hi","java","is","cool","and","fun","streams","rock"), count how many words have more than 3 characters.
     */

    public static void main(String[] args) {
        List<String> name = List.of("hi", "java", "is", "cool", "and", "fun", "streams", "rock");

        List<String> result = name.stream()
                .filter(n -> n.length() > 2)
                .collect(Collectors.toList());

        for (String val : result) {
            System.out.println(val);
        }

    }
}