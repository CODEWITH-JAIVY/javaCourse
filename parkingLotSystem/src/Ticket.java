public class Ticket {
    private final long entry;
    private long exit;
    private final Vehicle vehicle;

    Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.entry = System.currentTimeMillis();
    }

    public void ClosedTicket() {
        this.exit = System.currentTimeMillis();
    }

    public double calculateFee() {  // charge logic may be change accoring to the vehicle type
        long duration = exit - entry;
        long hour = duration / (1000 * 60 * 60);
        return hour * 20;
    }
}