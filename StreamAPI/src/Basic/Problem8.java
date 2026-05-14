package Basic;

import java.util.List;

public class Problem8 {
    /**
     * P5 — Extract names from User objects
     * You have a list of User objects. Map from the object to just the name field — a classic real-world pattern.
     * record User(String name, int age) {} List.of( new User("Alice", 30), new User("Bob", 17), new User("Carol", 25) )
     */

    public static void main(String[] args) {
        record User(String name, int age) {
        }

        List<User> users = List.of(
                new User("sanjeet", 30),
                new User("jaivy", 17),
                new User("Krishana", 25)
        );
        users.stream()
                .map(n -> n.name)
//                .collect(Collectors.toList()) ;
                .forEach(System.out::println);


    }
}