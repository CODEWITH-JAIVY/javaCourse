public interface LibraryService {

    void issueBook(Books books, User user);

    void returnBook(Books books, User user);

    boolean isavlable(Books book);

    void addbook(Books book);
}