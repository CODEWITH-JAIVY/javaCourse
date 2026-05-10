package com.lambadaExpressionPassingFunctionasAgument;

public class ImplementCalculation  {

    int Calculate(int a  , int b , lambadaExpresionAsFunction obj ) {
        return obj.operate(a , b ) ;
    }
// this is not reccomanded for the production in the industry becase there us hard to maintain
    //  so the seperate the methods and implementaion
    public static void main(String[] args) {
        ImplementCalculation calculation = new ImplementCalculation() ;

        lambadaExpresionAsFunction sumOperation = (a, b) -> a+b ;
        lambadaExpresionAsFunction mulOperation = (a , b )-> a*b ;
        lambadaExpresionAsFunction subOperation = (a , b )->a-b ;

        int sumresult = calculation.Calculate(5,8 , sumOperation) ;
        int mulResult = calculation.Calculate( 5,8, mulOperation) ;
        int subResult = calculation.Calculate(8,5 , subOperation) ;

        System.out.println("Sum result is " + sumresult);
        System.out.println("Multiply res is  " + mulResult);
        System.out.println("Subtract restult " + subResult);
    }
}
