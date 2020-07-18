import model.Course;
import model.Faculty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacultyTest {
    Faculty testFaculty;
    Course testCourse1;

    @BeforeEach
    public void runBefore() {
        testFaculty = new Faculty("Test");
        testCourse1 = new Course("test", 1);
    }

    @Test
    public void testSetCoursesOffered() {
        testFaculty.setCoursesOffered(testCourse1);
        assertEquals("test", testFaculty.getCoursesOffered().get(0).getName());
    }

    @Test
    public void testCoursesOfferedNameAndNum() {
        testFaculty.setCoursesOffered(testCourse1);
        assertEquals(testCourse1.getName() + " " + testCourse1.getNumber() + "\n",
                testFaculty.coursesOfferedNameAndNum());
    }
}
