import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
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
    }

    @Test
    public void testGetName() {
        assertEquals("name", testCourse.getName());
    }

    @Test
    public void testSetName() {
        assertEquals("name", testCourse.getName());
        testCourse.setName("name2");
        assertEquals("name2", testCourse.getName());
    }

    @Test
    public void testReturnCourseList() {
        courses.coursesSetUp();
        assertEquals(7, courses.getCourseList().size());
    }

    @Test
    public void testGetFacultylist() {
        courses.coursesSetUp();
        assertEquals(5,courses.getFacultyList().size());
    }

    @Test
    public void testCoursesSetUp() {
        courses.coursesSetUp();
        assertEquals(7, courses.getCourseList().size());
        assertEquals("CPSC", courses.getCourseList().get(0).getName());
        assertEquals("CPSC", courses.getCourseList().get(1).getName());
        assertEquals("MATH", courses.getCourseList().get(2).getName());
        assertEquals("LFS", courses.getCourseList().get(3).getName());
        assertEquals("COMM", courses.getCourseList().get(4).getName());
    }

    private void setUpTestCourses(ArrayList<Course> testCourseList) {
        Course cpsc110 = new Course("CPSC", 110);
        Course cpsc210 = new Course("CPSC", 210);
        Course biol121 = new Course("BIOL", 121);
        Course econ101 = new Course("ECON", 101);
        Course math200 = new Course("MATH", 200);
        testCourseList.add(cpsc110);
        testCourseList.add(cpsc210);
        testCourseList.add(biol121);
        testCourseList.add(econ101);
        testCourseList.add(math200);
    }
}
