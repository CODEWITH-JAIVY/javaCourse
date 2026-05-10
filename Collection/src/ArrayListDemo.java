import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArrayListDemo {
    public static void main(String[] args) throws  Exception  {
        ArrayList<Integer>list = new ArrayList<>() ;
        list.add(10 ) ;
        list.add(20) ;
        list.add(30) ;
        list.add(50) ;
//         <Integer> list1  =   list.subList( 1 , 3)
        System.out.println(list);
        ListIterator<Integer > listIterator = list.listIterator() ;
//        while (listIterator.hasNext() ) {
//            System.out.println(listIterator. hasNext()  );
//        }
         for ( int  E : list ) {
             System.out.println(listIterator.next() );
         }
        try  {
            List<Integer> list1  = list.subList(0 ,  3  ) ;

//            boolean result  =  listIterator.hasNext() ;

//            System.out.println(result);
                    System.out.println(list1);
        } catch(Exception e  ){
            System.out.println(  " check the index error   " + e.getMessage() );
        }

//    try(List<Integer> list1 = list.subList(0 , 2 )) {
//        System.out.println(list1);
//    }
     }
}
