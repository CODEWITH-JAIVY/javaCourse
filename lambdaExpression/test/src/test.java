class Test {
    static void hello() {
        System.out.println("Hello");
    }
}

public class test {
    public static void main(String[] args) {
        Test t = null;

        t.hello();  // ✔ Allowed
        System.out.println(t == null); // ✔ Allowed
        System.out.println(t);         // ✔ Prints: null
    }
}
