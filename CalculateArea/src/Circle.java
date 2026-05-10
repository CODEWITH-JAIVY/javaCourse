import java.util.Scanner;

public class Circle implements CaclculationOfArea {
    Scanner scanner = new Scanner(System.in ) ;
    // area of the circle is pi * r * r
    final double  pi  = 3.14 ;
    @Override
    public  void calculationDouble () {
        System.out.println("Enter radius of the circle  ");
         double r = scanner.nextDouble() ;
         double area = pi * r * r  ;
        System.out.println("Area of the Circle is " + area  + " unit ");
    }

}
