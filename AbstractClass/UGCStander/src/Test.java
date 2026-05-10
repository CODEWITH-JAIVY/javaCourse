class Test {

    public static void main(String[] args) {
        System.out.println("Main with String[] args");
        main(10);
        main("Hello");
    }

    // Overloaded main method
    public static void main(int a) {
        System.out.println("Main with int: " + a);
    }

    // Overloaded main method
    public static void main(String str) {
        System.out.println("Main with String: " + str);
    }

    public static void print() {
        Math.pow(2, 2);
    }
}