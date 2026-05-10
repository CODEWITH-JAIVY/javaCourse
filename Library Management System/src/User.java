public class User extends Person {

    private final UserType userType;

    public User(String persionId, String name, UserType userType) {
        super(persionId, name);
        this.userType = userType;
    }

    public void borrowBook(Library library, Books book) {
        library.issueBook(book, this);
    }

    public void returnBook(Library library, Books book) {
        library.returnBook(book, this);
    }

    public UserType getUserType() {
        return userType;
    }
}