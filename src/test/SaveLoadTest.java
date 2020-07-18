import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saveandload.Load;
import saveandload.Save;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveLoadTest {
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
        testComment2 = new Comment("test2");
        testCommentList = new CommentList();
        testCommentList.setUp();
        testRatingList = new RatingList();
        testRatingList.setUp();
        testRating = new Rating(0);
        testRatingList.addRating(testRating);
        testCommentList.addComment(testComment);
        testCommentList.addComment(testComment2);
        testCourse.changeCommentList(testCommentList);
        testCourse.changeRatingList(testRatingList);
        courses.insertCourse(testCourse);
    }

    @Test
    public void testSaveAndLoad() throws IOException {
        courses.getCourseList().get(0).getRatingList().insertReview("2", "sam");
        courses.getCourseList().get(0).getCommentList().
                insertReview("This class is very boring.", "bob");
        Save save;
        save = new Save();
        save.save(courses,"./data/testFile");
        Load load;
        load = new Load();
        assertEquals("name", load.load("./data/testFile").getCourseList().get(0).getName());
    }
}
