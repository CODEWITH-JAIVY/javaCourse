public class TicketCounter {

    int availableTicket = 1;

    public synchronized void bookTicket(String username) throws InterruptedException {
        System.out.println(username + " is checking for ticket");

        if (availableTicket > 0) {
            Thread.sleep(300);
            availableTicket--;
            System.out.println("Ticket booked for " + username);
        } else {
            System.out.println("Sorry " + username + ", ticket not available");
        }
    }
}