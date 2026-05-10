package com.day01interphaseinlambada;


 interface parent {
//    void say() ;
  default void sayHello(){
      System.out.println("Hello ");
  }

}
 public class childs implements parent {

//     @Override
     public void sayHello() {
//         parent.super.sayHello();
         System.out.println("Child say hello ");
     }

 }

