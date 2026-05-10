public class Person {
    String name  ;
    Adress adress  ;

    Person(String name , Adress adress   ) {
        this.name = name  ;
        this.adress = adress  ;

    }
    void ShowDetail()  {
        System.out.println("Name :- " + name );
        System.out.println("Addresh \n  *********** ");
        System.out.println("Block  :- " + adress.getBlock_number() + " \n Village  :-  " + adress.getVillage_name()
        + " \n city :- " + adress.getCity()  + " \n State :- " + adress.getState() );

    }
}
