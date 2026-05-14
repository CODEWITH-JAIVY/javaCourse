package Basic;

import java.util.List;
import java.util.Optional;

public class Problem2 {
    /**
     * Find the first name starting with "S"
     * Given List.of("Riya","Sameer","Tom","Sneha","Arjun"), find the first name that starts with "S" using streams.
     */

    public static void main(String[] args) {
        List<String> name = List.of("Riya", "Sameer", "Tom", "Sneha", "Arjun");
        Optional<String> result =
                name.stream()
                        .filter(s -> s.startsWith("S"))
//                        .collect(Collectors.toList());
                        .findFirst();


        result.ifPresent(System.out::println);
    }
}