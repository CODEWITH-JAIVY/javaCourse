//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       // referance of the CalculationOfArea class
        CaclculationOfArea calculate  ;
        // calculation the area if the circle
        calculate = new Circle() ;
        calculate.calculationDouble();

        // area of the Traingle
        calculate  = new Traingle()  ;
        calculate.calculationDouble();

        // area  of the ractangle
        calculate = new Ractangle() ;
        calculate.calculationDouble();
    }
}