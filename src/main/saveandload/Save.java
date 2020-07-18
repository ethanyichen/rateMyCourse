package saveandload;

import model.Course;
import model.Courses;

import java.io.IOException;
import java.io.PrintWriter;

public class Save {


    // save the information of the courses into the given file,
    // separate different sections with ","
    public void save(Courses courses, String file) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for (Course next : courses.getCourseList()) {
            writer.println(next.getName() + "," + next.getNumber() + "," + next.facultiesNames()
                    + "," + next.getCommentList().allReviewsContent()
                    + "," + next.getRatingList().allReviewsContent());
        }
        writer.close();
    }
}