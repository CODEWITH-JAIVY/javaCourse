

package com.jaivyLearing.RuntimeError;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class exceptoinHandling {
    public static void main(String[] args) throws IOException  {
//        FileReader reader   =  null ;
        try(FileReader reader = new FileReader ("C:\\Users\\skkar\\IdeaProjects\\FileOpening\\text.txt.txt") ;
            Scanner scanner = new Scanner( reader) ;
        ) {
            int num1  = scanner.nextInt()  ;
            int num2  = scanner.nextInt()  ;
            int num3  = scanner.nextInt()  ;
            int num4  = scanner.nextInt() ;

            System.out.println(num1  );
            System.out.println(num2 );
            System.out.println(num3);
            System.out.println(num4);

            System.out.println("End of fileReader ");
       } catch (FileNotFoundException e  ) {
            System.out.println(e.getMessage() );
        } catch (NullPointerException e ) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e ) {
            System.out.println(e.getMessage() );
        }
        catch (Exception e) {
            System.out.println("In the exception  class massage " );
            System.out.println(e.getMessage() );
        }
        System.out.println("End of main ");
    }
}

//package com.jaivyLearing.RuntimeError;
//
//import java.io.FileReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//public class exceptoinHandling {
//    public static void main(String[] args) {
//        FileReader reader = null;
//        try {
//            reader = new FileReader("C:\\Users\\skkar\\Documents\\myTExt.txt");
//            // You can add logic to read the file here
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + e.getMessage());
//        } finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                System.out.println("Error while closing file: " + e.getMessage());
//            }
//            System.out.println("End of finally block");
//        }
//    }
//}
