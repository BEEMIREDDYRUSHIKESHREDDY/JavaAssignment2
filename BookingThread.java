class BookingThread extends Thread {

    private BookingSystem system;
    private Ticket ticket;
    private String category;

    public BookingThread(
            BookingSystem system,
            Ticket ticket,
            String category) {

        this.system = system;
        this.ticket = ticket;
        this.category = category;
    }

    @Override
    public void run() {

        try {

            System.out.println(
                    Thread.currentThread().getName() +
                    " is trying to book a ticket."
            );

            system.book(ticket, category);

        } catch (BookingException e) {

            System.out.println(e.getMessage());
        }
    }
}