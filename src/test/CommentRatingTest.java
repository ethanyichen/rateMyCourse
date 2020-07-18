import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentRatingTest {
    Course testCourse;
    Courses courses;
    Comment testComment;
    Review testComment2;
    CommentList testCommentList;
    RatingList testRatingList;
    Rating testRating;
    Review testRating2;

    @BeforeEach
    public void setup() {
        testCourse = new Course("name", 1);
        courses = new Courses();
        testComment = new Comment("test");
        testComment2 = new Comment("test2");
        testCommentList = new CommentList();
        testCommentList.setUp();
        testRatingList = new RatingList();
        testRatingList.setUp();
        testRating = new Rating(0);
        testRating2 = new Rating(0);
        testRatingList.addRating(testRating);
        testCommentList.addComment(testComment);
    }

    @Test
    public void testGetComment() {
        assertEquals("test", testComment.getReview());
    }

    @Test
    public void testGetRating() {
        assertEquals(0, testRating.getRating());
    }

    @Test
    public void testGetReview() {
        assertEquals("test2", testComment2.getReview());
    }

    @Test
    public void testGetReviewRating() {
        assertEquals("0", testRating2.getReview());
    }


}
