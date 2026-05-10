package projects;

import java.util.Scanner;

public class shortcuirt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in) ;
        System.out.println("Enter two number ");
        int num1 = scanner.nextInt() ;
        int num2  = scanner.nextInt() ;
        if(num2!= 0 && num1 != 0 ) {
            System.out.println(" Both number is not zero ");
        }else {
            System.out.println("Not  zero ");
        }
    }
}
