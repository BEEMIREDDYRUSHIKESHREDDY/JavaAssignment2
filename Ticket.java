abstract class Ticket {

    private int ticketId;
    private String customerName;
    private double basePrice;

    public Ticket(int ticketId, String customerName,
                  double basePrice) {

        this.ticketId = ticketId;
        this.customerName = customerName;
        this.basePrice = basePrice;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract double calculatePrice();

    public void displayTicket() {

        System.out.println("Ticket ID: " + ticketId);
        System.out.println("Customer: " + customerName);
        System.out.println("Base Price: " + basePrice);
        System.out.println("Final Price: " + calculatePrice());
    }
}