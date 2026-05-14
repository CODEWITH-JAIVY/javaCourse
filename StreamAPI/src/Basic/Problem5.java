package Basic;

import java.util.List;

public class Problem5 {
    /**
     * P2 — Extract word lengths  List.of("Java", "Stream", "API", "is", "powerful")
     *
     */
    public static void main(String[] args) {
        List<String> name = List.of("Java", "Stream", "API", "is", "powerful");

        name.stream()
                .map(s -> s.length())
//                .collect(Collectors.toList());
                .forEach(System.out::println);

//        for (int val : ans) {
//            System.out.print(val + " ");
//        }
    }
}