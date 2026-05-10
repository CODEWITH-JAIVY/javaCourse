public class A implements I1 ,I2 {
    @Override
    public void f1() {
        System.out.println("Implement in class A from interface to I1 ");
    }

    @Override
    public void f2() {
        System.out.println("Implement in calss A from interaface to I2 ");
    }

    public  void f3 () {
        System.out.println("It is concrete  method of the calss A ");
    }
}
