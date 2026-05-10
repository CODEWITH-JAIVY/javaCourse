//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        UserTicketThread thread = new UserTicketThread(counter, "jaivy roy ");
        UserTicketThread thread1 = new UserTicketThread(counter, "jaivy ");
        UserTicketThread thread2 = new UserTicketThread(counter, "sanjeet kumar ");

        thread.start();
        thread1.start();
        thread2.start();
    }
}