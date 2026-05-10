package com.lambadaexpression;

public class test {
    public static void main(String[] args) {
//         softwarEengineer softwarEengineer = new softwarEengineer();
//
//         softwarEengineer softwareengineer = new softwarEengineer();
//        System.out.println( softwareengineer.getnmae() );


        // lambada expression
        Employe employe = ()->  {
            return   "jaivy  roy " ;
        };
        Employe sde =() -> {
          return "Sanjeet sde1"   ;
        };
        System.out.println(  "Software name " +employe.getname());
        System.out.println("SDe software " + sde.getname() );

    }
}
