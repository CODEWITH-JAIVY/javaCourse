package com.jaivy.filehandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader  {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\skkar\\OneDrive\\Desktop\\javaCourse\\FileHandling\\src\\com\\jaivy\\filehandling\\mytext.txt");

//            int data  = fileInputStream.read();
           int data  ;
//            System.out.println(data );
            while (  (data=fileInputStream.read() ) != -1  )  {
                System.out.print( (char)data );
            }
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
