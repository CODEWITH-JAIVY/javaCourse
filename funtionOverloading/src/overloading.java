public class overloading { // overloading there is many function with same name but the their argument are
//    different
// this is also called polymorphis there is many form , this function is called according to  matching function  signature
    public  void display(int num ) {
        System.out.println("There is single number " + num );
    }
    public void display( int num1 , int num2 ) {
        System.out.println( " There is two number " + num1  + " or " + num2   );
    }
    public  void display (int num1 , int num2 , int num3 ) {
        System.out.println("There is three number " +  num1 + "  or  " + num2  + "   or " + num3 );
    }

    public static void main(String[] args) {
        overloading obj = new overloading() ;
        obj.display(3);
        obj.display(5,8);
        obj.display(1,2,3);
    }
}
