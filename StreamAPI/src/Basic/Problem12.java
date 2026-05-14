package Basic;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Problem12 {

    /**
     * Chained Predicates + joining
     * Combine Predicate composition and a Collectors.joining() terminal.
     * <p>
     * <p>
     * Chained Predicates + joining
     * Combine Predicate composition and a Collectors.joining() terminal — a pattern used constantly in real code for building formatted output.
     *
     *
     */
    public static void main(String[] args) {

        List<String> words = List.of(
                " Java ", " ", "Stream", " API ", "", "rocks", " filter "
        );

        // Predicate to remove blank strings after trimming
        Predicate<String> notBlank = s -> !s.trim().isEmpty();

        // Predicate to keep words longer than 3 characters
        Predicate<String> lengthGreaterThan3 = s -> s.trim().length() > 3;

        String result = words.stream()
                .map(String::trim)
                .filter(notBlank.and(lengthGreaterThan3))
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}