package com.day01interphaseinlambada;

    public class MAINImplimentiona implements myInterphase {



//    interface obj = ()->{
//        System.out.println("Hello je kiase ho app  ");
//    }

    public static void main(String[] args) {
       myInterphase helloJeKaiseHaApa = () -> {
            System.out.println("Hello je kaise hai apa ");
        };
        MAINImplimentiona obj = new MAINImplimentiona() ;
        helloJeKaiseHaApa.hello();
    }


        @Override
        public void hello() {

        }
    }
