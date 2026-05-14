package Basic;

import java.util.List;
import java.util.stream.Collectors;

public class Problem7 {
    /**
     * Filter emails by domain
     * You have a list of email addresses. Keep only those from the "gmail.com" domain.
     * List.of("alice@gmail.com", "bob@yahoo.com", "carol@gmail.com", "dave@outlook.com", "eve@gmail.com")
     */

    public static void main(String[] args) {
        List<String> email = List.of("alice@gmail.com", "bob@yahoo.com", "carol@gmail.com", "dave@outlook.com", "eve@gmail.com");
        List<String> ans = email.stream()
                .filter(s -> s.endsWith("@gmail.com"))
                .collect(Collectors.toList());

        for (String val : ans) {
            System.out.println(val);
        }

    }
}