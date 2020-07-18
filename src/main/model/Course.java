package model;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
    private int courseNumber;
    private String courseName;
    private ReviewList ratingList;
    private ReviewList commentList;
    private ArrayList<Faculty> faculties;

    // MODIFIES: this
    // EFFECTS: constructs a course object with Name and Number
    public Course(String courseName, int courseNumber) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        ratingList = new RatingList();
        commentList = new CommentList();
        faculties = new ArrayList<>();
        ratingList.setUp();
        commentList.setUp();
    }

    // REQUIRES: the name of one course in the list is same with the string operation
    // EFFECTS: prints the Name ,Number and reviews of the course
    public void printInfo() {
        ArrayList<String> nameList = new ArrayList<>();
        for (Faculty next : faculties) {
            nameList.add(next.getName());
        }
        System.out.println("Faculties:" + nameList);
        System.out.println(commentList.allReviewsContent());
        System.out.println(ratingList.allReviewsContent());

    }

    // MODIFIES: this
    // EFFECTS: set the name of the course to the given string
    public void setName(String courseName) {
        this.courseName = courseName;
    }

    // EFFECTS: returns the name of the course
    public String getName() {
        return courseName;
    }

    public int getNumber() {
        return courseNumber;
    }

    public ReviewList getRatingList() {
        return ratingList;
    }

    public ReviewList getCommentList() {
        return commentList;
    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }

    //MODIFIES: this
    //EFFECTS: change the RatingList to the given RatingList
    public void changeRatingList(ReviewList newList) {
        this.ratingList = newList;
    }

    //MODIFIES: this
    //EFFECTS: change the CommentList to the given CommentList
    public void changeCommentList(ReviewList newList) {
        this.commentList = newList;
    }

    //MODIFIES: this,faculty
    //EFFECTS: add a new faculty in to faculties if it is not already contained,
    // update the offered courses in the faculty
    public void addFaculty(Faculty faculty) {
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
            faculty.setCoursesOffered(this);
        }
    }

    public void setFaculties(ArrayList<Faculty> faculties) {
        this.faculties = faculties;
    }

    //EFFECTS: returns the String with all the faculties' name separated by ";"
    public String facultiesNames() {
        String result = "";
        for (Faculty f : faculties) {
            result = result + f.getName() + ";";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return courseNumber == course.courseNumber
                && courseName.equalsIgnoreCase(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseNumber, courseName);
    }
}




