    public class Tesing {
        public static void main(String[] args) {

            myThresing t1 = new myThresing() ;
    //        myThresing t2  = new myThresing() ;


    //        t2.run();


            for (int i =0 ; i < 100000 ; i++ ) {
                System.out.println("Hello " + Thread.currentThread().getName() );
            }

            System.out.println("Run in " + Thread.currentThread().getName());
            for (int i = 0; i < 100000; i++) {
                t1.run();
            }
        }
    }
