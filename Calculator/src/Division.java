public class Division  implements  Calculation {
    @Override
    public void    Calculate ( int  a , int b  ) {
        if( a == 0 || b == 0 ) {
            System.out.println("Divisoon is not possible try again  ");
        }else {
            System.out.println(a + " / " +   b  + "  =  " + ( a / b  ));
        }
    }

}
