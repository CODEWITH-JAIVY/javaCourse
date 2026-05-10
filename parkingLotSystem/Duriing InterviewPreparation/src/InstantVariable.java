/*
* . What do you understand by an instance variable and a local variable?
Instance variables are those variables that are accessible by all the methods in the class.
*  They are declared outside the methods and inside the class.
*  These variables describe the properties of an object and remain bound to it at any cost.

All the objects of the class will have their copy of the variables for utilization.
* If any modification is done on these variables, then only that instance will be impacted by it,
*  and all other class instances continue to remain unaffected.
*
* */

public class InstantVariable {

    int n = 10;

    public void sum() {
        int s = 10;
        System.out.println(" s = " + s);
        s = s + 10;
        System.out.println(" s After updating  " + s);
        System.out.println(n);
        n = n + 10;
        System.out.println(n);
    }

    public void sub() {
        System.out.println("n value in the sub method " + n);
        n = n - 1;
        System.out.println("in sub method " + n);
    }

    public static void main(String[] args) {
        InstantVariable obj = new InstantVariable();
//        System.out.println();
        InstantVariable obj1 = new InstantVariable();

        System.out.println("For the object one ");
        System.out.println("*************************************************");
        obj.sum();
        obj.sub();
        System.out.println("For the object 2 ");
        System.out.println("******************************************************");
        obj1.sum();
        obj1.sub();
    }
}