package ExamCalculatorCreation;

import java.util.Scanner;

public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public static void main(String[] args) {
        Calculator calculat = new Calculator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 1st number ");
            int a = scanner.nextInt();
            System.out.println("Enter 2nd number ");
            int b = scanner.nextInt();
            System.out.println("Enter opeation  + - * / ");
            String operator = scanner.next();

            int ans = switch (operator) {
                case "+" -> calculat.sum(a, b);
                case "-" -> calculat.sub(a, b);
                case "*" -> calculat.mul(a, b);
                case "/" -> {
                    if (b == 0) {
                        System.out.println("Cannot divide by zero!");
                        yield 0;
                    }
                    yield calculat.div(a, b);
                }
                default -> {
                    System.out.println("Invalid operator!");
                    yield 0;
                }
            };
            System.out.println("The result is " + a + operator + b + " =   " + ans);
            System.out.println("do you want to countiue 0 ( no ) yes ( except  0 anything )   ");
            int con = scanner.nextInt();
            if (con == 0) {
                break;
            }
        }


    }
}