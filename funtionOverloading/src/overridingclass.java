//public class overridingclass {
//    public  class car {
//        void display () {
//            System.out.println("It is normal behavior for the all car function ");
//        }
//    }
//    public class  soprtCar extends  car {
//        void display() {
//            System.out.println("It is a soprt car ");
//        }
//    }
//
//    public static void main(String[] args) {
//        soprtCar CAR = new soprtCar() ;
//        CAR.display();
//    }
//}
public class overridingclass {

    // Parent class
    public class Car {
        void display() {
            System.out.println("It is normal behavior for all car functions");
        }
    }

    // Child class that overrides the parent class method
    public class SportCar extends Car {
        @Override
        void display() {
            System.out.println("It is a sport car");
        }
    }

    public static void main(String[] args) {
        overridingclass obj = new overridingclass(); // Create outer class instance
        SportCar car = obj.new SportCar(); // Create inner class object using outer
        car.display(); // Calls overridden method
    }
}
