import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HeighestSecondNumber {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 30, 20, 50, 40) ;
         int  SecondHeightest   = list.stream()
                 .sorted(Comparator.reverseOrder())
                 .skip(1)
                 .findFirst()
                 .get() ;
        System.out.println(SecondHeightest);
    }
}
