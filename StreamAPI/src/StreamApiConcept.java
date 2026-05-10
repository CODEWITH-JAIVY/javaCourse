import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util. stream.Collectors ;
import java.util.stream.*;

public class StreamApiConcept {
    public static void main(String[] args) {
     List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10) ;
     List<Integer> result = list.stream()
             .filter(n-> n % 2 == 0 )
//             .map(n-> n *n )
             .collect (Collectors.toList()) ;

        System.out.println(result  );
       List<Integer>secondResult =  result.stream().filter(n->n< 50  )
                .collect(Collectors.toList()) ;
        System.out.println(secondResult);
        result.stream().forEach(n-> System.out.print(n + " "));
        System.out.println(" ");
        int sum  = result.stream().reduce(0 , ( a ,  b  ) -> a+b ) ;
        System.out.println(sum);
    }
}
