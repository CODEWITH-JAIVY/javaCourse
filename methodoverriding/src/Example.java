class Parent {
    public void showDetails() {
        System.out.println("I am the parent.");
    }
}

class Child extends Parent {
    @Override
    public void showDetails() {
        System.out.println("I am the child.");
    }
}

public class Example {
    public static void main(String[] args) {
        Parent p = new Parent();
        Child c = new Child();
        Parent pc = new Child(); // Parent reference, Child object

        p.showDetails();   // ➜ I am the parent.
        c.showDetails();   // ➜ I am the child.
        pc.showDetails();  // ➜ I am the child. (This is overriding in action)
    }
}
