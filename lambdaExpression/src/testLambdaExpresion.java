

    @FunctionalInterface
    interface Testable {
        void test();
    }

    public class testLambdaExpresion  {
        public static void main(String[] args) {
            // Assigning lambda to functional interface
            Testable t = () -> {
                System.out.println("Tested lambda");
            };
            // Call method
            t.test();
        }
    }

