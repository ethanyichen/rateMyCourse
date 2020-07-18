package model;

import exception.UserNameNotFoundException;
import ui.AllScanner;

import java.util.ArrayList;

public class CommentList implements ReviewList {
    private ArrayList<Comment> comments;
    AllScanner scanner = new AllScanner();


    @Override
    public void setUp() {
        comments = new ArrayList<>();
    }


    public void addComment(Comment comment) {
        comments.add(comment);
    }

    //MODIFIES: courseToBeReview
    //EFFECTS : replace the Course's commentList with current commentList
    @Override
    public void addToCourse(Course courseToBeReview) {
        courseToBeReview.changeCommentList(this);
    }

    //EFFECTS: return a String with all the Comments Separated by ";"
    @Override
    public String allReviewsContent() {
        String result = "";
        for (Comment next : comments) {
            result = result + next.getUserName() + ":" + next.getReview() + ";";
        }
        return result;
    }

    //MODIFIES: this
    //EFFECTS: adds a new comment with given commentInput and userName
    @Override
    public void insertReview(String reviewInput, String userName) {
        Comment comment = new Comment(reviewInput);
        comment.setUserName(userName);
        comments.add(comment);
    }

    //EFFECTS: find and returns the string content of the comment with given username
    // throws an UserNameNotFoundException if cannot find
    @Override
    public String searchReview(String userName) throws UserNameNotFoundException {
        for (Comment next : comments) {
            if (next.getUserName().equals(userName)) {
                return next.getReview();
            }
        }
        throw new UserNameNotFoundException();
    }

    //EFFECTS: Returns the String result produced by searchReview()
    // returns "Cannot find comment with given username" if catch an UserNameNotFoundException
    @Override
    public String completeSearchReview(String userName) {
        String result = "";
        try {
            result = searchReview(userName);
        } catch (UserNameNotFoundException e) {
            result = "Cannot find comment with given username";
        } finally {
            return result;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes a comment with given userName
    @Override
    public void removeReviewWithGivenName(String userName) {
        ArrayList<Comment> toBeRemoved = new ArrayList<>();
        for (Comment next : comments) {
            if (next.getUserName().equals(userName)) {
                toBeRemoved.add(next);
            }
        }
        for (Comment r : toBeRemoved) {
            comments.remove(r);
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

}
