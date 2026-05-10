import java.util.Date;
import java.util.HashMap;

public class Transaction {
    HashMap<String, TransactionDetail> transactions;

    public Transaction() {
        this.transactions = new HashMap<>();
    }

    // class ki responsibity hai ki user ki data ko store kar ke rakhna
    // so that we can find witch is brrow by witch user  with the help of bookid or userid

    public boolean isBorrowAlready(String userId, Books book) {
        return transactions.containsKey(userId) && transactions.containsValue(book);
    }

    public void borrowBook(String userId, Books book) {
        TransactionDetail transactionDetail = new TransactionDetail(book, userId, new Date(), null);
        transactions.put(userId, transactionDetail);
    }

    public void returnBook(Books book, String userID) {
        transactions.remove(book);
    }
}