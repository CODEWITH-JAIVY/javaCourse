package com.jaivyLearing.RuntimeError;

import java.util.Scanner;

public class MultipleCatchBlock {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[5];

        System.out.println("Enter Array elements: ");
        try {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            System.out.println("Printing Array elements:");
            for (int i = 0; i <= arr.length; i++) {  // Intentionally going out of bounds
                System.out.println(arr[i]);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have crossed the index of the array: " + e);
        } catch (NullPointerException e) {
            System.out.println("There is a null pointer exception: " + e);
        } catch (Exception e) {
            System.out.println("Some other exception occurred: " + e);
        } finally {
            scanner.close();  // Best practice
        }
    }
}
