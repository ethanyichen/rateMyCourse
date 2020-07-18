package ui;

import exception.CourseNotFoundException;
import exception.RatingInvalidException;
import model.Course;
import model.Courses;
import model.Faculty;
import model.RatingList;
import saveandload.Save;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;


public class CoursesToDos extends Observable {
    AllScanner scanner = new AllScanner();
    protected Courses courses;

    public CoursesToDos() {
        addObserver(new ReviewsChart());
    }

    public void setUp() {
        courses.coursesSetUp();
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    //MODIFIES: this
    //EFFECTS: add a new course with given userInput of name, number, faculty
    // create placeholder for the empty reviews sections,
    // load into courses
    public void addCourse() {
        Course courseToBeAdded = scanner.addCourseScanner();
        String facultyInPut = scanner.addFacultyScanner();
        Faculty faculty = new Faculty(facultyInPut);
        Faculty findResult = courses.findFaculty(facultyInPut);
        if (findResult != null) {
            courseToBeAdded.addFaculty(findResult);
        } else {
            courseToBeAdded.addFaculty(faculty);
        }
        addNewCourseReviewPlaceHolder(courseToBeAdded);
        courses.loadCourse(courseToBeAdded);
    }

    //EFFECTS: create placeholder for the empty reviews sections,
    public void addNewCourseReviewPlaceHolder(Course course) {
        course.getRatingList().insertReview("0", "Place Holder");
        course.getCommentList().insertReview("Place Holder", "Place Holder");
    }

    //MODIFIES: this, courseToAddRating
    //EFFECTS: add a new raing to the course with given userInput rating and userName
    public void addRating(Course courseToAddRating) {
        try {
            String ratingInput = scanner.rateCourseScanner();
            checkValid(ratingInput);
            String userNameInput = scanner.userNameScanner();
            courseToAddRating.getRatingList().insertReview(ratingInput, userNameInput);
            courses.coursesReviewMap.get(courseToAddRating).set(1, courseToAddRating.getRatingList());
            courseToAddRating.getRatingList().removeReviewWithGivenName("Place Holder");
        } catch (RatingInvalidException e) {
            System.out.println("Please Enter a Valid Rating(Natural Number 0-5)!");
        }
    }

    //MODIFIES: this, courseToAddComment
    //EFFECTS: add a new comment to the course with given userInput rating and userName
    // notify observer that new comment has been added
    public void addComment(Course courseToAddComment) {
        String commentInput = scanner.commentCourseScanner();
        String userNameInput = scanner.userNameScanner();
        courseToAddComment.getCommentList().insertReview(commentInput, userNameInput);
        courses.coursesReviewMap.get(courseToAddComment).set(0, courseToAddComment.getCommentList());
        courseToAddComment.getCommentList().removeReviewWithGivenName("Place Holder");
        String info = userNameInput + " add a comment to "
                + courseToAddComment.getName() + courseToAddComment.getNumber();
        setChanged();
        notifyObservers(info);
    }

    //EFFECTS: throws an exception if the userInput Rating is not one of the RATING_OPTIONS
    public void checkValid(String userInput) throws RatingInvalidException {
        for (int nextOption : RatingList.RATING_OPTIONS) {
            if ((double) nextOption == Double.parseDouble(userInput)) {
                return;
            }
        }
        throw new RatingInvalidException();
    }

    //EFFECTS: prints the result of searching Ratings with input userName in the given courseToSearch
    public void ratingSearch(Course courseToSearch) {
        String userNameToFind = scanner.userNameToFindScanner();
        System.out.println(courseToSearch.getRatingList().completeSearchReview(userNameToFind));
    }

    //EFFECTS: prints the result of searching Comments with input userName in the given courseToSearch
    public void commentSearch(Course courseToSearch) {
        String userNameToFind = scanner.userNameToFindScanner();
        System.out.println(courseToSearch.getCommentList().completeSearchReview(userNameToFind));
    }

    //EFFECTS: prints the result of searching Ratings with input userName in the given courseToSearch
    public Course searchCoursesWithInputName() throws CourseNotFoundException {
        ArrayList<String> userInputs = scanner.courseSearchScanner();
        String userInputCourseName = userInputs.get(0);
        String userInputCourseNumber = userInputs.get(1);
        return searchCourseWithOutInput(userInputCourseName, userInputCourseNumber);
    }

    //EFFECTS: find and return the course with given name and number in the courses
    // throws an exception if such course if not found
    public Course searchCourseWithOutInput(String name, String num) throws CourseNotFoundException {
        for (Course next : courses.getCourseList()) {
            if (next.equals(new Course(name, Integer.parseInt(num)))) {
                return next;
            }
        }
        throw new CourseNotFoundException();
    }

    //EFFECTS: notify observer and save the courses into the given file
    public void exit() throws IOException {
        setChanged();
        notifyObservers("exit");
        new Save().save(courses, "./data/coursesFile");
    }

}
