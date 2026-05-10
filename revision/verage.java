package revision;

 
public class verage {
    public static void display(int... nums) {

        for (int val : nums) {
            System.out.println(val);
        }
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 15;
        int c = 50, d = 10;
        display(a, b, c, d);


    }
}