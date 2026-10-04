package moviebooking;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Theatre {
    private final boolean[][] seats;
    private final int weekdayPrice;
    private final int weekendPrice;

    public Theatre(int rows, int columns, int weekdayPrice, int weekendPrice) {
        seats = new boolean[rows][columns];
        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
    }

    public int getRowCount() {
        return seats.length;
    }

    public int getColumnCount() {
        return seats[0].length;
    }

    public void showSeats() {
        System.out.println("\nAvailable seats (O = available, X = booked)");
        System.out.print("     ");
        for (int column = 1; column <= getColumnCount(); column++) {
            System.out.print(column + "   ");
        }
        System.out.println();

        for (int row = 0; row < getRowCount(); row++) {
            System.out.print("Row " + (row + 1) + " ");
            for (int column = 0; column < getColumnCount(); column++) {
                System.out.print(seats[row][column] ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }

    public boolean isValidSeat(int row, int column) {
        return row >= 1 && row <= getRowCount()
                && column >= 1 && column <= getColumnCount();
    }

    public boolean isBooked(int row, int column) {
        return seats[row - 1][column - 1];
    }

    public void setBooked(int row, int column, boolean booked) {
        seats[row - 1][column - 1] = booked;
    }

    public int getTicketPrice() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        boolean weekend = today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
        return weekend ? weekendPrice : weekdayPrice;
    }

    public boolean isWeekend() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
}
