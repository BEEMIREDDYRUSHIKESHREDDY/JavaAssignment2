import java.util.*;
import java.nio.file.*;
import java.io.IOException;

class BookingSystem {

    private ArrayList<Booking> bookings =
            new ArrayList<>();

    private HashMap<String, Integer> availableTickets =
            new HashMap<>();

    private HashSet<Integer> bookingIds =
            new HashSet<>();

    private int nextBookingId = 1001;

    private Path filePath = Paths.get("bookings.txt");

    public BookingSystem() {

        availableTickets.put("Movie", 10);
        availableTickets.put("Bus", 10);
    }

    public synchronized void book(
            Ticket ticket, String category)
            throws BookingException {

        int available =
                availableTickets.getOrDefault(category, 0);

        if (available <= 0) {

            throw new BookingException(
                    "No tickets available for " + category);
        }

        int id = nextBookingId++;

        Booking booking = new Booking(id, ticket);

        bookings.add(booking);
        bookingIds.add(id);

        availableTickets.put(category, available - 1);

        System.out.println(
                "\nTicket booked successfully!");

        System.out.println("Booking ID: " + id);

        saveBooking(booking);
    }

    public synchronized void cancel(int id)
            throws BookingException {

        Booking found = null;

        for (Booking booking : bookings) {

            if (booking.getBookingId() == id) {

                found = booking;
                break;
            }
        }

        if (found == null) {

            throw new BookingException(
                    "Invalid Booking ID!");
        }

        String category;

        if (found.getTicket() instanceof MovieTicket) {

            category = "Movie";

        } else {

            category = "Bus";
        }

        bookings.remove(found);
        bookingIds.remove(id);

        availableTickets.put(
                category,
                availableTickets.get(category) + 1
        );

        System.out.println("Booking cancelled successfully!");
    }

    public void displayBookings() {

        if (bookings.isEmpty()) {

            System.out.println("No bookings available.");
            return;
        }

        for (Booking booking : bookings) {

            booking.displayBooking();
        }
    }

    public void displayAvailability() {

        System.out.println("\n--- AVAILABLE TICKETS ---");

        for (String category : availableTickets.keySet()) {

            System.out.println(
                    category + ": " +
                    availableTickets.get(category)
            );
        }
    }

    private void saveBooking(Booking booking) {

        String data =
                "Booking ID: " + booking.getBookingId() +
                ", Customer: " +
                booking.getTicket().getCustomerName() +
                ", Price: " +
                booking.getTicket().calculatePrice() +
                System.lineSeparator();

        try {

            Files.write(
                    filePath,
                    data.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {

            System.out.println(
                    "File writing error: " + e.getMessage()
            );
        }
    }
}
