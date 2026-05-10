//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Library library = new Library("Roy library ");
        Books book = new Books("Java Full Stack Develoopement ", "Jaivy roy ", "abc31", true);
        User user = new User("1", "Sanjeet kumar ", UserType.STUDENT);

        library.addbook(book);

        user.borrowBook(library, book);

        user.returnBook(library, book);
    }
}