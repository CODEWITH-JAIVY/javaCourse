import java.util.Scanner;

public class Ractangle implements  CaclculationOfArea  {
    Scanner scanner  = new Scanner(System.in ) ;
    // area of  Ractangle is  l  * b

    @Override
    public void calculationDouble() {
         double l  , b ;
        System.out.println("Enter lenght and breath of the Ractangle  ");
        l = scanner.nextDouble()  ;
        b  =scanner.nextDouble()  ;
        double  area  = l * b  ;
        System.out.println("Aare of the ractangle is  " + area );
    }
}
