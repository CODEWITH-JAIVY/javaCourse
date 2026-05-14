package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem9 {
    /**
     * P6 — Get names of adult users
     * Classic real-world pattern: filter a list of objects, then extract a field from survivors.
     * <p>
     * <p>
     * record User(String name, int age) {}
     * List.of( new User("Alice", 30), new User("Bob", 17), new User("Carol", 25), new   User("Dave", 15), new User("Eve", 22) )
     *
     */

    public static void main(String[] args) {
        record User(String name, int age) {
        }
        List<User> users = List.of(
                new User("Alice", 30),
                new User("Bob", 17),
                new User("Carol", 25),
                new User("Dave", 15),
                new User("Eve", 22));

        List<String> ans =
                users.stream()
                        .filter(user -> user.age > 18)
                        .map(s -> s.name.toUpperCase())
                        .collect(Collectors.toList());
        for (String name : ans) {
            System.out.println(name + " ");
        }

    }
}