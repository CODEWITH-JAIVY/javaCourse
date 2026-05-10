//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Adress adress  = new Adress("Karmasi " , "Siwan " , "b112" , "Bihar" ) ;
        Person person1  = new Person("sanjeet kumar"  , adress  )  ;
      //Adress (String Village_number , String city  , String block_number , String state )

        person1.ShowDetail();

    }
}