class MovieTicket extends Ticket {

    private String movieName;

    public MovieTicket(int id, String customer,
                       double price, String movieName) {

        super(id, customer, price);
        this.movieName = movieName;
    }

    @Override
    public double calculatePrice() {

        return getBasePrice() + 30;
    }

    @Override
    public void displayTicket() {

        System.out.println("\n--- MOVIE TICKET ---");

        super.displayTicket();

        System.out.println("Movie: " + movieName);
    }
}