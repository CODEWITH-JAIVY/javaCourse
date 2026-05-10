import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in ) ;
        System.out.println("Select menu operation  ");
        System.out.println("*******************************************");
        System.out.println(  "For Addition press 1  ");
        System.out.println(" subtract 2 ");
        System.out.println("Multyply 3 ");
        System.out.println("Division 4 ");
        int Operatoin  ;
        Calculation cal ;
int a  , b  ;

         Operatoin = scanner.nextInt() ;
        switch (Operatoin ) {
            case 1 :
                System.out.println("Enter  two number to add this ");

                a = scanner.nextInt() ;
                b = scanner.nextInt() ;
                 cal = new Addition(  )  ;
                 cal.Calculate(a , b );
                 break;
            case 2  :
                System.out.println("Enter two number for the subtraction ");
                a  = scanner.nextInt() ;
                b = scanner.nextInt()   ;
                cal = new Substraction() ;
                cal.Calculate( a ,b  );
                break;
            case 3  :
                System.out.println( "Enter number to perform the multiplycation  ");
              a = scanner.nextInt()  ;
               b  = scanner.nextInt() ;
                cal = new Multiply()  ;
                cal.Calculate(a  , b );
                break;
            case 4  :
                System.out.println("Enter number to perform the Divison operation  ");
               a  = scanner.nextInt()  ;
                b  = scanner.nextInt()  ;
                cal  = new Division() ;
                cal.Calculate(a   , b  );
                break;
            default:
                System.out.println( "Choose a valid operation  ");
                System.out.println("Thank You   ");
        }
    }
}