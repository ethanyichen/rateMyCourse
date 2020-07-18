import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewListTest {
    Course testCourse;
    Courses courses;
    Comment testComment;
    Comment testComment2;
    CommentList testCommentList;
    RatingList testRatingList;
    Rating testRating;

    @BeforeEach
    public void setup() {
        testCourse = new Course("name", 1);
        courses = new Courses();
        testComment = new Comment("test");
        testComment.setUserName("Bob");
        testComment2 = new Comment("test2");
        testComment2.setUserName("Sam");
        testCommentList = new CommentList();
        testCommentList.setUp();
        testRatingList = new RatingList();
        testRatingList.setUp();
        testRating = new Rating(0);
        testRating.setUserName("Tom");
        testRatingList.addRating(testRating);
        testCommentList.addComment(testComment);
        testCommentList.addComment(testComment2);
    }

    @Test
    public void testCommentSetUp() {
        assertEquals("test", testComment.getReview());
    }

    @Test
    public void testAllReviewsContent() {
        assertEquals("Bob:test;Sam:test2;", testCommentList.allReviewsContent());
    }

    @Test
    public void testGetCommentList() {
        assertEquals(testCommentList.getComments().size(), 2);
    }

    @Test
    public void testCommentAddtoCourse() {
        testCommentList.addToCourse(testCourse);
        assertEquals("Bob:test;Sam:test2;", testCourse.getCommentList().allReviewsContent());
    }

    @Test
    public void testRatingAddtoCourse() {
        testCourse.changeRatingList(testRatingList);
        testRatingList.addToCourse(testCourse);
        assertEquals("Tom:0;", testCourse.getRatingList().allReviewsContent());
    }

    @Test
    public void testGetRATING() {
        assertEquals(0, testRating.getRating());
    }

    @Test
    public void testInsertReviewRating() {
        testRatingList.insertReview("2", "");
        assertEquals(2, testRatingList.getRatings().get(1).getRating());
    }

    @Test
    public void testRemoveReviewRating() {
        testRatingList.insertReview("2", "Dude");
        assertEquals(2, testRatingList.getRatings().get(1).getRating());
        testRatingList.removeReviewWithGivenName("Dude");
        assertEquals(1,testRatingList.getRatings().size());
    }

    @Test
    public void testRemoveReviewComment() {
        testCommentList.insertReview("good", "Dude");
        System.out.println(testCommentList.getComments().size());
        assertEquals("good", testCommentList.getComments().get(2).getReview());
        testCommentList.removeReviewWithGivenName("Dude");
        assertEquals(2,testCommentList.getComments().size());
    }


    @Test
    public void testInsertReviewComment() {
        testCommentList.insertReview("I LOVE IT", "");
        assertEquals("I LOVE IT", testCommentList.getComments().get(2).getReview());
    }

    @Test
    public void testSearchReviewComment() {
        testComment2.setUserName("comment2");
        assertEquals("test2", testCommentList.completeSearchReview("comment2"));
    }

    @Test
    public void testSearchReviewCommentWithException() {
        testComment2.setUserName("comment2");
            assertEquals("Cannot find comment with given username",
                    testCommentList.completeSearchReview("comment1"));
    }

    @Test
    public void testSearchReviewRating() {
        testRating.setUserName("rating1");
        assertEquals("0", testRatingList.completeSearchReview("rating1"));
    }

    @Test
    public void testSearchReviewRatingWithException() {
        testRating.setUserName("rating1");
        assertEquals("Cannot find rating with given username",
                testRatingList.completeSearchReview("rating2"));
    }

    @Test
    public void testAverageRating(){
        testRatingList.addRating(new Rating(0));
        testRatingList.addRating(new Rating(2));
        testRatingList.addRating(new Rating(3));
        assertEquals(1.25,testRatingList.averageRating());

    }

}
