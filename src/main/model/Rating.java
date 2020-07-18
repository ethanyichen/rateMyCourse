package model;

public class Rating extends Review {
    private int rating;

    public Rating(int rating) {
        super("");
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String getReview() {
        return Integer.toString(rating);
    }

}
