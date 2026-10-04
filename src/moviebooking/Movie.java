package moviebooking;

public class Movie {
    private final String title;
    private final String couponCode;
    private int ratingTotal;
    private int ratingCount;

    public Movie(String title, String couponCode) {
        this.title = title;
        this.couponCode = couponCode;
    }

    public String getTitle() {
        return title;
    }

    public boolean isValidCoupon(String enteredCode) {
        return couponCode.equalsIgnoreCase(enteredCode);
    }

    public void addRating(int rating) {
        ratingTotal += rating;
        ratingCount++;
    }

    public double getAverageRating() {
        if (ratingCount == 0) {
            return 0.0;
        }
        return (double) ratingTotal / ratingCount;
    }
}
