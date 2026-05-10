class Test1 {
    static void hello() {
        System.out.println("Hello");
    }
}

public class tests {
    public static void main(String[] args) {
        Test1 t = null;

        t.hello();  // ✔ Allowed
        System.out.println(t == null); // ✔ Allowed
        System.out.println(t);         // ✔ Prints: null
    }
}
