package model;

import java.util.ArrayList;
import java.util.Objects;

public class Faculty {
    private String name;
    private ArrayList<Course> coursesOffered;

    public Faculty(String name) {
        this.name = name;
        coursesOffered = new ArrayList<>();
    }

    //MODIFIES: this, course
    //EFFECTS: add given course into coursesOffered if it is not in there
    // update the faculty for that course
    public void setCoursesOffered(Course course) {
        if (!coursesOffered.contains(course)) {
            coursesOffered.add(course);
            course.addFaculty(this);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getCoursesOffered() {
        return coursesOffered;
    }

    //EFFECTS: returns the string with all the names and numbers of the coursesOffered
    // separate different courses with a new line
    public String coursesOfferedNameAndNum() {
        String result = "";
        for (Course course : coursesOffered) {
            result = result + (course.getName() + " " + course.getNumber() + "\n");
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
        Faculty faculty = (Faculty) o;
        return name.equalsIgnoreCase(faculty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
