import java.util.*;
import java.util.stream.* ;

public class duplicate_elements {
    public static void main(String[] args) {
        // find the duplicate in the list using Stream api

        List<Integer> list  = Arrays.asList(1, 2, 3, 4, 2, 3, 5) ;
            Set<Integer> result =list.stream()
                   .filter(n-> Collections.frequency(list , n ) > 1)
                    .collect(Collectors.toSet());
        System.out.println(result);
    }
}
