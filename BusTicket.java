class BusTicket extends Ticket {

    private String destination;

    public BusTicket(int id, String customer,
                     double price, String destination) {

        super(id, customer, price);
        this.destination = destination;
    }

    @Override
    public double calculatePrice() {

        return getBasePrice() + 50;
    }

    @Override
    public void displayTicket() {

        System.out.println("\n--- BUS TICKET ---");

        super.displayTicket();

        System.out.println("Destination: " + destination);
    }
}