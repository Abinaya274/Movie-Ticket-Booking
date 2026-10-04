package moviebooking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BookingSystem bookingSystem = new BookingSystem(
                    new Theatre(5, 6, 200, 250),
                    new Movie("Movie", "MOVIE10"));
            boolean running = true;

            System.out.println("=== Movie Ticket Booking System ===");
            bookingSystem.showPricing();

            while (running) {
                System.out.println("\n1. Show available seats");
                System.out.println("2. Book ticket");
                System.out.println("3. Cancel ticket");
                System.out.println("4. Rate movie");
                System.out.println("5. Exit");

                int choice = readInteger(scanner, "Choose an option: ");

                switch (choice) {
                    case 1 ->
                        bookingSystem.showSeats();
                    case 2 ->
                        bookingSystem.bookTicket(scanner);
                    case 3 ->
                        bookingSystem.cancelTicket(scanner);
                    case 4 ->
                        bookingSystem.rateMovie(scanner);
                    case 5 -> {
                        running = false;
                        System.out.println("Thank you for using the Movie Ticket Booking System.");
                    }
                    default ->
                        System.out.println("Invalid option. Please choose a number from 1 to 5.");
                }
            }
        }
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}
