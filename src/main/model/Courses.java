package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Courses {
    ArrayList<Course> courseList = new ArrayList<>();
    public HashMap<Course, ArrayList<ReviewList>> coursesReviewMap = new HashMap<>();
    private ArrayList<Faculty> facultyList = new ArrayList<>();


    public void coursesSetUp() {
        courseSetUp("CPSC", 110, "Science");
        courseSetUp("CPSC", 210, "Science");
        courseSetUp("MATH", 200, "Science");
        courseSetUp("LFS", 100, "Lfs");
        courseSetUp("COMM", 310, "Business");
        courseSetUp("CONS", 101, "Forestry");
        courseSetUp("ECON", 101, "Arts");
    }

    //MODIFIES: this
    //EFFECTS: Add a new course with given name,number,and faculty into the courseList
    // and update CoursesReview Hash map
    // update the course offered for the course's faculty that is already in facultyList
    // otherwise add the course's faculty into facultyList and update its course offered
    public void courseSetUp(String name, int number, String facultyName) {
        Course course = new Course(name, number);
        Faculty faculty = new Faculty(facultyName);
        if (facultyList.contains(faculty)) {
            course.addFaculty(findFaculty(faculty.getName()));
        } else {
            course.addFaculty(faculty);
            facultyList.add(faculty);
        }
        insertCourse(course);
    }

    //MODIFIES: this
    //EFFECTS: Load the information from a given course into courses
    // update the course offered for the course's faculty that is already in facultyList
    // otherwise add the course's faculty into facultyList and update its course offered
    // insert the course into courseList and update CoursesReview Hash map
    public void loadCourse(Course course) {
        for (Faculty f : course.getFaculties()) {
            if (facultyList.contains(f)) {
                findFaculty(f.getName()).setCoursesOffered(course);
            } else {
                facultyList.add(f);
                f.setCoursesOffered(course);
            }
            insertCourse(course);
        }
    }


    // EFFECTS: returns the courseList
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Faculty> getFacultyList() {
        return facultyList;
    }

    //EFFECTS: Returns a Faculty with the given name in the courses' facultyList
    public Faculty findFaculty(String facultyName) {
        for (Faculty next : facultyList) {
            if (facultyName.equalsIgnoreCase(next.getName())) {
                return next;
            }
        }
        return null;
    }

    //EFFECTS: insert a new course into the courseList
    // add the course comments and ratings into the corresponding hash map key
    // create a new key if course key is not in the mmap
    public void insertCourse(Course course) {
        if (!courseList.contains(course)) {
            courseList.add(course);
        }
        if (!coursesReviewMap.containsKey(course)) {
            coursesReviewMap.put(course, new ArrayList<>());
            coursesReviewMap.get(course).add(course.getCommentList()); // CommentList at position 0
            coursesReviewMap.get(course).add(course.getRatingList());
            // RatingList at position 1
        }
    }
}
