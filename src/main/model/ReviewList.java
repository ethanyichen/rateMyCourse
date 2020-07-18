package model;

import exception.UserNameNotFoundException;

public interface ReviewList {
    void addToCourse(Course courseToBeReview);

    String allReviewsContent();

    void insertReview(String reviewInput, String userName);

    void setUp();

    String searchReview(String username) throws UserNameNotFoundException, UserNameNotFoundException;

    String completeSearchReview(String userName);

    void removeReviewWithGivenName(String userName);
}
