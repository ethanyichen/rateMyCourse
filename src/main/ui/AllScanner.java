package ui;

import model.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class AllScanner {
    Scanner scanner;

    public AllScanner() {
        scanner = new Scanner(System.in);
    }

    //EFFECTS: prompt for user input course, return that as ArrayList<String>;
    public ArrayList<String> courseSearchScanner() {
        System.out.println("Please enter the course Name:");
        String name = scanner.nextLine();
        System.out.println("Please enter the course number:");
        String number = scanner.nextLine();
        System.out.println("you selected: " + name + " " + number);
        ArrayList<String> result = new ArrayList<>();
        result.add(name);
        result.add(number);
        return result;
    }

    //EFFECTS: prompt for user input name, return that as String;
    public String userNameScanner() {
        System.out.println("Enter a short user name");
        String name = scanner.nextLine();
        System.out.println("Your userName for this Review: " + name);
        return name;
    }

    //EFFECTS: prompt for user input rating, return that as String;
    public String rateCourseScanner() {
        System.out.println("Rate this Course from natural number 1-5: ");
        String inputRating = scanner.nextLine();
        return inputRating;
    }

    //EFFECTS: prompt for user input comment, return that as String;
    public String commentCourseScanner() {
        System.out.println("Write a Review for this Course: ");
        String commentInput = scanner.nextLine();
        return commentInput;
    }

    //EFFECTS: prompt for user input faculty name, return that as String;
    public String addFacultyScanner() {
        System.out.println("Enter Course Faculty:  (Capitalize First Character)");
        String courseFaculty = scanner.nextLine();
        return courseFaculty;
    }

    //EFFECTS: prompt for user input name and number, return that as a new course with input info;
    public Course addCourseScanner() {
        System.out.println("Enter Course Name: (All Capital Cases)");
        String courseName = scanner.nextLine();
        System.out.println("Enter Course Number: ");
        int courseNumber = Integer.parseInt(scanner.nextLine());
        return new Course(courseName, courseNumber);
    }

    //EFFECTS: prompt for user input userName, return that as String;
    public String userNameToFindScanner() {
        System.out.println("Search For UserName: ");
        String userNameToFind = scanner.nextLine();
        return userNameToFind;
    }

    //EFFECTS: prompt for user input choice for the menu options, return that as String;
    public String menuScanner() {
        System.out.println("Choose from one of the menu options");
        System.out.println("[1] search for a course");
        System.out.println("[2] Faculties");
        System.out.println("[3] Add Course");
        System.out.println("[4] Exit");
        String userChoice = scanner.nextLine();
        return userChoice;
    }

    //EFFECTS: prompt for user input choice for the faculty menu options, return that as String;
    public String facultiesScanner() {
        System.out.println("[1] Science");
        System.out.println("[2] Arts");
        System.out.println("[3] Lfs");
        System.out.println("[4] Forestry");
        System.out.println("[5] Business");
        String userChoice = scanner.nextLine();
        return userChoice;
    }

    //EFFECTS: prompt for user input choice for the course further actions menu options, return that as String;
    public String courseFurtherActionsScanner() {
        System.out.println("[1] Print Info");
        System.out.println("[2] Add Rating");
        System.out.println("[3] Add Comment");
        System.out.println("[4] Rating Search");
        System.out.println("[5] Comment Search");
        String userChoice = scanner.nextLine();
        return userChoice;
    }
}
