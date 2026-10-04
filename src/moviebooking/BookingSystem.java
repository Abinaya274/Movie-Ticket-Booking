package moviebooking;

import java.util.Scanner;

public class BookingSystem {
    private final Theatre theatre;
    private final Movie movie;

    public BookingSystem(Theatre theatre, Movie movie) {
        this.theatre = theatre;
        this.movie = movie;
    }

    public void showPricing() {
        int price = theatre.getTicketPrice();
        String pricingType = theatre.isWeekend() ? "weekend" : "weekday";
        System.out.println("Movie: " + movie.getTitle() + ". Ticket price: Rs. " + price
                + " (" + pricingType + " pricing)");
    }

    public void showSeats() {
        theatre.showSeats();
    }

    public void bookTicket(Scanner scanner) {
        showSeats();
        int row = readInteger(scanner, "Enter row number (1-" + theatre.getRowCount() + "): ");
        int column = readInteger(scanner,
                "Enter seat number (1-" + theatre.getColumnCount() + "): ");

        if (!theatre.isValidSeat(row, column)) {
            System.out.println("Invalid seat. Please choose a row and seat shown above.");
        } else if (theatre.isBooked(row, column)) {
            System.out.println("That seat is already booked.");
        } else {
            int price = theatre.getTicketPrice();
            System.out.print("Enter coupon code, or press Enter to skip: ");
            String coupon = scanner.nextLine().trim();
            int discount = movie.isValidCoupon(coupon) ? price / 10 : 0;

            theatre.setBooked(row, column, true);
            System.out.println("Ticket booked for Row " + row + ", Seat " + column + ".");
            System.out.println("Ticket price: Rs. " + price);
            if (discount > 0) {
                System.out.println("Coupon discount (10%): Rs. " + discount);
            } else if (!coupon.isEmpty()) {
                System.out.println("Invalid coupon code. No discount was applied.");
            }
            System.out.println("Amount to pay: Rs. " + (price - discount));
        }
    }

    public void cancelTicket(Scanner scanner) {
        showSeats();
        int row = readInteger(scanner,
                "Enter row number of the ticket to cancel (1-" + theatre.getRowCount() + "): ");
        int column = readInteger(scanner,
                "Enter seat number of the ticket to cancel (1-" + theatre.getColumnCount() + "): ");

        if (!theatre.isValidSeat(row, column)) {
            System.out.println("Invalid seat. Please choose a row and seat shown above.");
        } else if (!theatre.isBooked(row, column)) {
            System.out.println("That seat is not currently booked.");
        } else {
            theatre.setBooked(row, column, false);
            System.out.println("Ticket cancelled for Row " + row + ", Seat " + column + ".");
        }
    }

    public void rateMovie(Scanner scanner) {
        int rating = readInteger(scanner, "Rate the movie from 1 to 5: ");

        if (rating >= 1 && rating <= 5) {
            movie.addRating(rating);
            System.out.printf("Thank you for rating the movie %d/5. Average rating: %.1f/5.%n",
                    rating, movie.getAverageRating());
        } else {
            System.out.println("Invalid rating. Please enter a number from 1 to 5.");
        }
    }

    private int readInteger(Scanner scanner, String prompt) {
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
