import java.util.*;

public class arraysort {

    class mycomparator implements Comparator {

//        @Override
//        public int compare(Integer o1, Integer o2) {
//            return  o2 - o1 ;
//        }

        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }

        @Override
        public Comparator reversed() {
            return Comparator.super.reversed();
        }
    }

    public static void main(String[] args) {
////        int  [] arr  = { 9,8,7,6,5,4,1,2,3 } ;
//        List<Integer> list = new ArrayList<>() ;
//        list.add(2) ;
//        list.add(3) ;
//        list.add(1) ;
////        Collections.sort(list);
//        list.sort(null);
//        System.out.println(list);
//        Collections.sort(list , (a , b )->  b - a  );
////        System.out.println(list);
//    List<String>str  = new ArrayList<>() ;
//    str.add("Sanjeet kumar ") ;
//    str.add("Jaivy roy ") ;
//    str.add("Kumar") ;
//    str.add("Roy jaivy ") ;
//    Collections.sort(str);
//        System.out.println(str);

//        Collections.sort( str , ( a , b ) ->  'a' - 'b' );
//        System.out.println(str);

        List<Integer> list5 = new ArrayList<>();
        list5.add(1);
        list5.add(2);
        list5.add(3);
        list5.add(4);
        int ans = Collections.binarySearch(list5, 10);
        System.out.println(ans);
        int[] arr = {1, 2, 3, 4, 5, 6, 100, 250, 7, 8, 9, 10};
//        Collections.sort(arr);
        Arrays.sort(arr);
        for (int val : arr) {
            System.out.print(val + " ");
        }


    }
}