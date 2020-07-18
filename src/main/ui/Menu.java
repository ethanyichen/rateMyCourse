package ui;

import exception.CourseNotFoundException;
import model.Course;
import model.Faculty;
import saveandload.Load;
import saveandload.Save;

import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    AllScanner scanner;
    CoursesToDos coursesToDos;
    private Course userSelectedCourse;
    Save save = new Save();
    Load load = new Load();

    public Menu() throws IOException {
        scanner = new AllScanner();
        coursesToDos = new CoursesToDos();
//        coursesToDos.setUp(); // uncomment this if want to wipe all the existing reviews and added courses data
        coursesToDos.setCourses(load.load("./data/coursesFile"));
    }


    public void run() throws IOException {
        startMenu();
    }

    private void startMenu() throws IOException {
        switch (scanner.menuScanner()) {
            case "1":
                courseAction();
                break;
            case "2":
                facultiesMenu();
                startMenu();
                break;
            case "3":
                coursesToDos.addCourse();
                startMenu();
                break;
            default:
                coursesToDos.exit();
//                save.save(coursesToDos.courses, "./data/coursesFile");
                break;
        }
    }

    private void courseAction() throws IOException {
        try {
            userSelectedCourse = coursesToDos.searchCoursesWithInputName();
            System.out.println("You have " + userSelectedCourse.getName() + userSelectedCourse.getNumber());
            courseFurtherActionMenu();
            startMenu();
        } catch (CourseNotFoundException e) {
            System.out.println("Cannot Find Such Course");
            startMenu();
        }
    }

    private void facultiesMenu() {
        switch (scanner.facultiesScanner()) {
            case "1":
                System.out.println(courseOfferedStringList(coursesToDos.courses.findFaculty("Science")));
                break;
            case "2":
                System.out.println(courseOfferedStringList(coursesToDos.courses.findFaculty("Arts")));
                break;
            case "3":
                System.out.println(courseOfferedStringList(coursesToDos.courses.findFaculty("Lfs")));
                break;
            case "4":
                System.out.println(courseOfferedStringList(coursesToDos.courses.findFaculty("Forestry")));
                break;
            default:
                System.out.println(courseOfferedStringList(coursesToDos.courses.findFaculty("Business")));
                break;
        }
    }

    private void courseFurtherActionMenu() {
        switch (scanner.courseFurtherActionsScanner()) {
            case "1":
                userSelectedCourse.printInfo();
                break;
            case "2":
                coursesToDos.addRating(userSelectedCourse);
                break;
            case "3":
                coursesToDos.addComment(userSelectedCourse);
                break;
            case "4":
                coursesToDos.ratingSearch(userSelectedCourse);
                break;
            default:
                coursesToDos.commentSearch(userSelectedCourse);
                break;
        }
    }

    private ArrayList<String> courseOfferedStringList(Faculty faculty) {
        ArrayList<String> nameAndNumberList = new ArrayList<>();
        for (Course next : faculty.getCoursesOffered()) {
            nameAndNumberList.add(next.getName() + " " + Integer.toString(next.getNumber()));
        }
        return nameAndNumberList;
    }
}
