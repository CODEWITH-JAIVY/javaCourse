package AbstractClass;

public class AbstractDemoMethos {
    abstract public class Person {
        int a = 0;

        Person() {
            System.out.println("Abstract class construction  ");
        }


        public void display() {
            System.out.println("I am person in the abstract class  ");
            System.out.println(a);
        }
    }

    public class Student extends Person {
        Student() {
            System.out.println("Student class constructor  ");
        }

        public void print() {
            System.out.println("I the student  ");
        }


    }

    public static void main(String[] args) {
//        Person person = new Person();
//        Person p  =  new Student()   ;
//        Student std = new Student();
    }

}