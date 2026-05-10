package com.day01interphaseinlambada;

 interface A {
   default  void sayhello() {
       System.out.println("I am parent ");
   }
}

interface B extends A {
     default void sayhello() {
         System.out.println("I am child ");
     }
}
public class fordefaultMethos implements A , B {
    public static void main(String[] args) {
        fordefaultMethos obj = new fordefaultMethos();
        obj.sayhello();
    }

        @Override
        public void sayhello () {
            B.super.sayhello();
        }
    }

