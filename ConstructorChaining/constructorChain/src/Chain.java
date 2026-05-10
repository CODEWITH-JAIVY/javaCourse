class parent {
    String name;

    parent(String name) {
        this.name = name;
    }

    public void age() {
        System.out.println("My age id " + 23 + " From the parent class ");
    }
}

class Child extends parent {

    String name;

    Child(String name) {
        super("Rajnath prasad ");
        this.name = name;
    }

    void childDetail() {
        System.out.println("My name is  " + name);
        System.out.println("My Father name is " + super.name);
    }

}

public class Chain {

    public static void main(String[] args) {
        Child child = new Child("Sanjeet kumar ");
        child.childDetail();

    }
}