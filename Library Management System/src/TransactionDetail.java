import java.util.Date;

class TransactionDetail {

    private Books book;
    private String userId;
    private Date issueDate;
    private Date returnDate;

    public TransactionDetail(Books book, String user, Date issueDate, Date returnDate) {
        this.book = book;
        this.userId = user;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public String getUser() {
        return userId;
    }

    public void setUser(String user) {
        this.userId = user;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


}