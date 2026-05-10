public class Books {
    private String title;
    private String author;
    private String Isbn;
    private boolean isAvailable;

    public Books(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        Isbn = isbn;
        this.isAvailable = isAvailable;
    }

// class responsibility    provided the datail of the book , from where regrading book
    // information call

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}