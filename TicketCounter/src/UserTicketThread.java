public class UserTicketThread extends Thread {

    private final String username;

    TicketCounter ticketCounter;

    UserTicketThread(TicketCounter counter, String username) {
        this.ticketCounter = counter;
        this.username = username;
    }


    @Override
    public void run() {
        try {
            ticketCounter.bookTicket(username);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}