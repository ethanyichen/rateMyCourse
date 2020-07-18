package saveandload;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Load {

    //EFFECTS: returns a Courses object from reading the file
    // separate course's name, num, faculties, comments, and ratings with comma on every line of the file
    public Courses load(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        Courses courses = new Courses();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnComma(line);
            String courseName = partsOfLine.get(0);
            String courseNum = partsOfLine.get(1);
            String faculties = partsOfLine.get(2);
            String comments = partsOfLine.get(3);
            String ratings = partsOfLine.get(4);
            Course course = new Course(courseName, Integer.parseInt(courseNum));
            course.changeRatingList(parseReview(ratings,"rating"));
            course.changeCommentList(parseReview(comments,"comment"));
            course.setFaculties(parseFaculties(faculties));
            courses.loadCourse(course);
        }
        System.out.println("Load Successful");
        return courses;
    }

    // EFFECTS: Split a String into different parts by ";"
    public static ArrayList<String> splitOnSemiColon(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }


    //EFFECT: Split a String into different parts by ":"
    public static ArrayList<String> splitOnColon(String line) {
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static ArrayList<String> splitOnComma(String line) {
        String[] splits = line.split(",");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public ReviewList parseReview(String reviews, String option) {
        ArrayList<String> reviewList = splitOnSemiColon(reviews);
        ReviewList parseResult;
        if (option == "rating") {
            parseResult = new RatingList();
        } else {
            parseResult = new CommentList();
        }
        parseResult.setUp();
        for (String next : reviewList) {
            ArrayList<String> userNameAndReview = splitOnColon(next);
            String name = userNameAndReview.get(0);
            parseResult.insertReview(userNameAndReview.get(1), userNameAndReview.get(0));
        }
        return parseResult;
    }

    public ArrayList<Faculty> parseFaculties(String faculties) {
        ArrayList<String> nameList = splitOnSemiColon(faculties);
        ArrayList<Faculty> parseResult = new ArrayList<>();
        for (String next : nameList) {
            parseResult.add(new Faculty(next));
        }
        return parseResult;
    }
}
