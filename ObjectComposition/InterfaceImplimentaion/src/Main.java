//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        A obj = new A()  ;  // obj is object of the class A ,
//        obj.f1();
//        obj.f2();
//        obj.f3();
//  we can take the refernce of the Interface of the I1  , I2 than we can create the object  of the class A
        I1 obj1 = new A() ;
        obj.f1();  // here only F1 function can be call because there is refernce of the I1 interecace  if we
        // try to access the f2 , f3 function than it will be give error


        }
    }
