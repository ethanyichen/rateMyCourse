package model;

import exception.UserNameNotFoundException;
import ui.AllScanner;

import java.util.ArrayList;
import java.util.Arrays;

public class RatingList implements ReviewList {
    private ArrayList<Rating> ratings;
    public static final ArrayList<Integer> RATING_OPTIONS = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5));
    AllScanner scanner = new AllScanner();

    @Override
    public void setUp() {
        ratings = new ArrayList<>();
    }

    //EFFECTS: find and returns the string content of the Rating with given username
    // throws an UserNameNotFoundException if cannot find
    @Override
    public String searchReview(String userName) throws UserNameNotFoundException {
        for (Rating next : ratings) {
            String name = next.getUserName();
            if (next.getUserName().equals(userName)) {
                return next.getReview();
            }
        }
        throw new UserNameNotFoundException();
    }

    //EFFECTS: Returns the String result produced by searchReview()
    // returns "Cannot find rating with given username" if catch an UserNameNotFoundException
    @Override
    public String completeSearchReview(String userName) {
        String result = "";
        try {
            result = searchReview(userName);
        } catch (UserNameNotFoundException e) {
            result = "Cannot find rating with given username";
        } finally {
            return result;
        }
    }

    //EFFECTS : replace the Course's commentList with commentList
    @Override
    public void addToCourse(Course courseToBeReview) {
        courseToBeReview.changeRatingList(this);
    }

    //EFFECTS: return a String with all the Ratings Separated by ";"
    @Override
    public String allReviewsContent() {
        String result = "";
        for (Rating next : ratings) {
            result = result + next.getUserName() + ":" + next.getReview() + ";";
        }
        return result;
    }

    //REQUIRES: valid Input from one of RATING_OPTIONS
    //MODIFIES: this
    //EFFECTS: if input RATING is one of the element in RATING_OPTIONS
    // add it to the ratingList
    @Override
    public void insertReview(String reviewInput, String userName) {
        int rating = (int) Double.parseDouble(reviewInput);
        Rating userRating = new Rating(rating);
        userRating.setUserName(userName);
        ratings.add(userRating);
    }


    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    //EFFECTS: returns average value of ratings
    public double averageRating() {
        double sumOfRatings = 0;
        int count = 0;
        for (Rating next : ratings) {
            sumOfRatings = sumOfRatings + next.getRating();
            count++;
        }
        return sumOfRatings / count;
    }

    //MODIFIES: this
    //EFFECTS: removes ratings with given userName
    public void removeReviewWithGivenName(String userName) {
        ArrayList<Rating> toBeRemoved = new ArrayList<>();
        for (Rating next : ratings) {
            if (next.getUserName().equals(userName)) {
                toBeRemoved.add(next);
            }
        }
        for (Rating r : toBeRemoved) {
            ratings.remove(r);
        }
    }
}