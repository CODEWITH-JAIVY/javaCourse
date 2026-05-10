public class TrowsndeTrow {
//    public  void method2() {
//        int a = 10  , b = 0 ;
//        int result  = a / b  ;
//        System.out.println("Result " + result  );
//
//    }
//    public  void method1 () {
//        method2();
//    }
//
//    public static void main(String[] args) {
//        TrowsndeTrow obj = new TrowsndeTrow();
//            obj.method1();
//
//        System.out.println("End of main  ");
//    }


    public static void main(String[] args) {
        method1();
        System.out.println("End of main  ");
    }

    static void method1() {
        method2();
        System.out.println("method a ");
    }

    static void method2() {
        try {
            int a = 10, b = 0;
            System.out.println(a / b);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.println(e);
        }
        System.out.println("End of metohd b ");
    }
}
