public class Addition implements  Calculation {
@Override
   public void  Calculate ( int a , int b ) {
        int sum  = 0  ;
        sum = a + b  ;
        System.out.println( "Result is :- ");
        System.out.println( a + " +  " + b + "  = " + sum  );

    }
}
