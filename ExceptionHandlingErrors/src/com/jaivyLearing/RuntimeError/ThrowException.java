package com.jaivyLearing.RuntimeError;

import java.util.Scanner;

//  find the area of the where redius of the circle  is enter by the user
public class ThrowException {

    Double Area (double redius ) throws  Exception  {
        double pi  = 3.14  ;
        if( redius <= 0 ) {
//            throw new Exception () ;
            throw new IllegalArgumentException("Radius cannot be negative.");

        }
        return  redius* redius * pi  ;
    }
    public static void main(String[] args) throws  Exception {
        ThrowException obj  = new ThrowException()  ;
        Scanner scanner  = new Scanner(System.in) ;
        try {
            double redius ;
            System.out.println("Enter  radius of the circle to find the area ");

            redius = scanner.nextDouble();
            double Areas = obj.Area(redius);
            System.out.println("ARea of the circle " + Areas );
        }catch (IllegalAccessException e ) {
            System.out.println( " Error " + e.getMessage() );
        } catch (NullPointerException  e) {
            System.out.println(e.getMessage() );
        } catch (Exception e ) {
            System.out.println("Error" + e.getMessage() );
        }finally {
            scanner.close();
        }



    }


}
