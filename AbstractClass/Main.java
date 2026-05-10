package AbstractClass;

public class Main {
    public static void main(String[] args) {
        Animal animal  ;
        animal = new Dog()  ;
        animal.MakeNoise();
        animal = new Lion()  ;
        animal.MakeNoise();
    }
}
