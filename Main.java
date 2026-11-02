
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingSystem system = new BookingSystem();

        int choice;

        do {
            System.out.println("\n===== TICKET BOOKING SYSTEM =====");
            System.out.println("1. Book Movie Ticket");
            System.out.println("2. Book Bus Ticket");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Display Bookings");
            System.out.println("5. Display Availability");
            System.out.println("6. Multiple Customers");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Customer Name: ");
                        String name = sc.nextLine();

                        System.out.print("Movie Name: ");
                        String movie = sc.nextLine();

                        system.book(
                            new MovieTicket(1, name, 150, movie),
                            "Movie"
                        );
                        break;

                    case 2:
                        System.out.print("Customer Name: ");
                        name = sc.nextLine();

                        System.out.print("Destination: ");
                        String destination = sc.nextLine();

                        system.book(
                            new BusTicket(2, name, 300, destination),
                            "Bus"
                        );
                        break;

                    case 3:
                        System.out.print("Booking ID: ");
                        system.cancel(sc.nextInt());
                        break;

                    case 4:
                        system.displayBookings();
                        break;

                    case 5:
                        system.displayAvailability();
                        break;

                    case 6:
                        Thread t1 = new BookingThread(system,
                            new MovieTicket(3, "Customer A", 150, "Avengers"), "Movie");

                        Thread t2 = new BookingThread(system,
                            new BusTicket(4, "Customer B", 300, "Hyderabad"), "Bus");

                        t1.start();
                        t2.start();

                        t1.join();
                        t2.join();
                        break;

                    case 0:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number!");
                sc.nextLine();
                choice = -1;

            } catch (BookingException e) {
                System.out.println(e.getMessage());
                choice = -1;

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted!");
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}
