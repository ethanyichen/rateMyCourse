package model;

public abstract class Review {
    protected String username;

    public Review(String username) {
        this.username = username;
    }

    public abstract String getReview();

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getUserName() {
        return username;
    }
}
