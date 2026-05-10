package com.jaivy.filehandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class reader {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader( new InputStreamReader(System.in)) ;
        System.out.println("Enter you name ");
        String name  = bf.readLine() ;
        System.out.println(name);
    }


}
