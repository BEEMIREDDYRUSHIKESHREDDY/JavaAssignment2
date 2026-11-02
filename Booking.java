class Booking {

    private int bookingId;
    private Ticket ticket;

    public Booking(int bookingId, Ticket ticket) {

        this.bookingId = bookingId;
        this.ticket = ticket;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void displayBooking() {

        System.out.println("\nBooking ID: " + bookingId);

        ticket.displayTicket();
    }
}