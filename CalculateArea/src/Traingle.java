import java.util.Scanner;

  class Traingle implements CaclculationOfArea {

    Scanner scanner = new Scanner(System.in ) ;
    // area of the Trainghle is 1/2 * l   * b
    int l  ,  b  ;
      @Override
   public   void calculationDouble () {
         System.out.println("Enter length of the traingle ");
      l = scanner.nextInt() ;
         System.out.println("Enter breath of the traingle ");
         b  = scanner.nextInt() ;

         int area  =    (l * b) / 2  ;
         System.out.println("Area of the traingle is  " +  area );

    }
}
