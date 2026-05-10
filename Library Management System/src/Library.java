import java.util.ArrayList;
import java.util.List;

public class Library implements LibraryService {
    private String LibraryName;
    private List<Books> bookList;

    Library(String name) {
        this.bookList = new ArrayList<>();
        this.LibraryName = name;
    }


    public String getName() {
        return LibraryName;
    }

    public void setName(String name) {
        this.LibraryName = name;
    }

    public List<Books> getBookList() {
        return bookList;
    }

    public void setBookList(List<Books> bookList) {
        this.bookList = bookList;
    }


    Transaction transaction = new Transaction();

    /**
     * @param books
     * @param user
     * @return
     */
    @Override
    public void issueBook(Books books, User user) {
        // book is available
        System.out.println("Book issue in process ");
        boolean book = isavlable(books);
        // user already  borrow  this book or not  validation
        System.out.println("Book id avaible  " + book);
        boolean isBorrowed = transaction.isBorrowAlready(user.getPersionId(), books);
        // both condition are satisfied than issue book
        System.out.println(" is Borrowed " + isBorrowed);
        if (book && !isBorrowed) {
            transaction.borrowBook(user.getPersionId(), books);
            bookList.remove(books);
            System.out.println(books.getTitle() + "Book is borroed by " + user.getUserType());
        }

    }

    /**
     * @param books
     * @param user
     */
    @Override
    public void returnBook(Books books, User user) {
        transaction.returnBook(books, user.getPersionId());
        System.out.println("Book return succesfully by " + user.getName());
        bookList.add(books);
    }

    /**
     * @param book
     * @return
     */
    @Override
    public boolean isavlable(Books book) {
        for (Books val : bookList) {
            if (val == book) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param book
     */
    @Override
    public void addbook(Books book) {
        bookList.add(book);
    }

    // the responsibility of the class is


}