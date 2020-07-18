package ui;

import exception.CourseNotFoundException;
import model.Course;
import model.Faculty;
import model.RatingList;
import saveandload.Load;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI extends JFrame {
    static CoursesToDos coursesToDos;
    static Course foundCourse;
    static Load load = new Load();

    static JComboBox comboBox;

    //EFFECTS: load the courses from the given file
    // start the startMenu UI
    public static void main(String[] args) throws IOException {
        coursesToDos = new CoursesToDos();
        coursesToDos.setCourses(load.load("./data/coursesFile"));
        javax.swing.SwingUtilities.invokeLater(() -> startMenuUi());
    }

    private static void startMenuUi() {
        JFrame menuFrame = jframeGeneratorr("Menu", 100);
        menuButton(menuFrame);
        picture(menuFrame);
    }

    private static void menuButton(JFrame frame) {
        JButton searchCourses = new JButton("Search Courses");
        searchCourses.setBounds(125, 105, 150, 45);
        JButton faculties = new JButton("Faculties");
        faculties.setBounds(125, 155, 150, 45);
        JButton addCourses = new JButton("Add Courses");
        addCourses.setBounds(125, 205, 150, 45);
        JButton exit = new JButton("Exit");
        exit.setBounds(125, 265, 150, 45);
        frame.add(searchCourses);
        frame.add(faculties);
        frame.add(addCourses);
        frame.add(exit);

        searchCourses.addActionListener(l -> searchCourses());
        faculties.addActionListener(l -> faculties());
        addCourses.addActionListener(l -> addCourse());
        exit.addActionListener(l -> exitUi(frame));
    }


    private static void exitUi(JFrame frame) {
        try {
            coursesToDos.exit();
            frame.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void searchCourses() {
        JTextField textFieldName = new JTextField(10);
        JTextField textFieldNum = new JTextField(10);
        JFrame searchFrame = jframeGeneratorr("Search Courses", 100);
        JPanel namePanel = panel("Course Name:", 80, textFieldName);
        JPanel numPanel = panel("Course Number:", 150, textFieldNum);
        JButton arrowButton = arrowButton(300, 250);
        searchFrame.add(namePanel);
        searchFrame.add(numPanel);
        searchFrame.add(arrowButton);
        arrowButton.addActionListener(l -> searchAction(textFieldName, textFieldNum, searchFrame));
    }

    private static void searchAction(JTextField textFieldName, JTextField textFieldNum, JFrame searchFrame) {
        try {
            foundCourse = coursesToDos.searchCourseWithOutInput(textFieldName.getText(), textFieldNum.getText());
            courseActions();
            searchFrame.dispose();
        } catch (CourseNotFoundException e) {
            courseNotFound();
        }
    }

    private static void courseNotFound() {
        JFrame courseNotFound = jframeGeneratorr("Course Not Found", 100);

        warning(courseNotFound);
        JLabel notFoundLabel = new JLabel("Course Not Found");
        JPanel notFoundPanel = new JPanel();
        notFoundPanel.setBounds(50, 175, 300, 50);

        notFoundPanel.add(notFoundLabel);
        courseNotFound.add(notFoundPanel);
    }

    private static void courseActions() {
        JFrame foundTheCourse = jframeGeneratorr("Found The Course", 100);

        courseButton(foundTheCourse);
    }

    private static void courseButton(JFrame frame) {
        JButton printInfo = button("Print Info", 50);
        JButton addRating = button("Add Rating", 100);
        JButton addComment = button("Add Comment", 150);
        JButton ratingSearch = button("Rating Search", 200);
        JButton commentSearch = button("Comment Search", 250);

        frame.add(printInfo);
        frame.add(addRating);
        frame.add(addComment);
        frame.add(ratingSearch);
        frame.add(commentSearch);

        printInfo.addActionListener(l -> printInfo());
        addRating.addActionListener(l -> addRating());
        addComment.addActionListener(l -> addComment());
        ratingSearch.addActionListener(l -> searchReview("Rating Search", "Rating"));
        commentSearch.addActionListener(l -> searchReview("Comment Search", "Comment"));
    }

    private static JButton button(String s, int y) {
        JButton button = new JButton(s);
        button.setBounds(125, y, 150, 45);
        return button;
    }

    private static void printInfo() {
        JFrame allCoursesFrame = jframeGeneratorr("Print Info", 100);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 25, 350, 275);
        JTextArea courses = new JTextArea(foundCourse.getName() + "\n" + foundCourse.getNumber() + "\n"
                + foundCourse.facultiesNames() + "\n" + foundCourse.getCommentList().allReviewsContent() + "\n"
                + foundCourse.getRatingList().allReviewsContent() + "\n"
                + "Average Rating:" + averageRating());

        scroll.setViewportView(courses);
        allCoursesFrame.add(scroll);
    }

    private static void addRating() {
        JFrame addRatingFrame = jframeGeneratorr("Add Rating", 100);

        JPanel ratingPanel = new JPanel();
        JLabel ratingLabel = new JLabel("Rating");
        String[] ratings = {"0", "1", "2", "3", "4", "5"};
        comboBox = new JComboBox(ratings);
        ratingPanel.setBounds(50, 100, 200, 50);
        ratingPanel.add(ratingLabel);
        ratingPanel.add(comboBox);

        JTextField textFieldUserName = new JTextField(10);
        JPanel userNamePanel = panel("User Name", 160, textFieldUserName);
        JButton arrowButton = arrowButton(300, 225);

        addRatingFrame.add(ratingPanel);
        addRatingFrame.add(userNamePanel);
        addRatingFrame.add(arrowButton);

        arrowButton.addActionListener(l -> addRatingAction(textFieldUserName, addRatingFrame));
    }

    private static void addRatingAction(JTextField textFieldUserName, JFrame frame) {
        foundCourse.getRatingList().insertReview((String) comboBox.getSelectedItem(),
                textFieldUserName.getText());
        coursesToDos.courses.coursesReviewMap.get(foundCourse).set(1, foundCourse.getRatingList());
        foundCourse.getRatingList().removeReviewWithGivenName("Place Holder");
        reviewsAdded();
        frame.dispose();

    }


    private static void addComment() {
        JFrame addCommentFrame = jframeGeneratorr("Add Rating", 100);

        JTextField textFieldComment = new JTextField(10);
        JTextField textFieldUserName = new JTextField(10);
        JPanel commentPanel = panel("Comment", 100, textFieldComment);
        JPanel userNamePanel = panel("User Name", 160, textFieldUserName);
        JButton arrowButton = arrowButton(300, 225);


        addCommentFrame.add(commentPanel);
        addCommentFrame.add(userNamePanel);
        addCommentFrame.add(arrowButton);

        arrowButton.addActionListener(l -> addCommentAction(textFieldComment, textFieldUserName, addCommentFrame));
    }

    private static void addCommentAction(JTextField commentField, JTextField nameField, JFrame frame) {
        foundCourse.getCommentList().insertReview(commentField.getText(), nameField.getText());
        coursesToDos.courses.coursesReviewMap.get(foundCourse).set(0, foundCourse.getCommentList());
        foundCourse.getCommentList().removeReviewWithGivenName("Place Holder");
        reviewsAdded();
        frame.dispose();
    }

    private static void reviewsAdded() {
        JFrame reviewsAddedFrame = jframeGeneratorr("Review Added", 100);
        JLabel ratingAddedLabel = new JLabel("Review Successfully Added!");
        JPanel panel = new JPanel();
        panel.setBounds(20, 230, 350, 50);
        panel.add(ratingAddedLabel);
        reviewsAddedFrame.add(panel);
    }

    private static void searchReview(String title, String option) {
        JFrame searchingResultFrame = jframeGeneratorr(title, 100);

        JTextField textFieldUserName = new JTextField(10);
        JPanel userNamePanel = panel("Search for a username", 100, textFieldUserName);
        JButton arrowButton = arrowButton(300, 225);

        searchingResultFrame.add(userNamePanel);
        searchingResultFrame.add(arrowButton);


        arrowButton.addActionListener(e -> searchingResult(textFieldUserName, option));
    }

    private static void searchingResult(JTextField userNameField, String option) {
        String searchResult = "No Result";
        if (option.equalsIgnoreCase("Comment")) {
            searchResult = foundCourse.getCommentList().completeSearchReview(userNameField.getText());

        } else if (option.equalsIgnoreCase("Rating")) {
            searchResult = foundCourse.getRatingList().completeSearchReview(userNameField.getText());
        }
        JFrame searchingResultFrame = jframeGeneratorr("Searching Result", 100);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 25, 350, 275);
        JTextArea courses = new JTextArea(searchResult);

        scroll.setViewportView(courses);
        searchingResultFrame.add(scroll);
    }


    private static void faculties() {
        JFrame facultiesFrame = jframeGeneratorr("Faculties", 100);

        JPanel facultiesPanel = facultiesPanel(100);
        JButton arrowButton = arrowButton(300, 100);

        facultiesFrame.add(facultiesPanel);
        facultiesFrame.add(arrowButton);

        arrowButton.addActionListener(e -> facultySearch());
    }

    private static JPanel facultiesPanel(int y) {
        JPanel facultiesPanel = new JPanel();
        JLabel facultiesLabel = new JLabel("Faculty");
        String[] faculties = {"Science", "Arts", "LFS", "Forestry", "Business"};
        comboBox = new JComboBox(faculties);
        facultiesPanel.setBounds(100, y, 200, 55);
        facultiesPanel.add(facultiesLabel);
        facultiesPanel.add(comboBox);
        return facultiesPanel;
    }

    private static void facultySearch() {
        JFrame allCoursesFrame = jframeGeneratorr("All Courses", 100);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 25, 350, 275);

        String facultyName = comboBox.getSelectedItem().toString();
        JTextArea courses = new JTextArea(coursesToDos.courses.findFaculty(facultyName).coursesOfferedNameAndNum());
        scroll.setViewportView(courses);
        allCoursesFrame.add(scroll);
    }

    private static void addCourse() {
        JFrame addCourseFrame = jframeGeneratorr("Add Course", 100);

        JPanel textPanel = new JPanel();
        JLabel textLabel = new JLabel("Please enter the following info about the new course");
        textPanel.setBounds(25, 50, 350, 50);
        textPanel.add(textLabel);
        JTextField textFieldName = new JTextField(10);
        JTextField textFieldNum = new JTextField(10);
        JPanel namePanel = panel("Course Name:", 100, textFieldName);
        JPanel numPanel = panel("Course Number:", 150, textFieldNum);
        JPanel facultiesPanel = facultiesPanel(200);
        JButton arrowButton = arrowButton(300, 250);
        addCourseFrame.add(textPanel);
        addCourseFrame.add(namePanel);
        addCourseFrame.add(numPanel);
        addCourseFrame.add(facultiesPanel);
        addCourseFrame.add(arrowButton);

        arrowButton.addActionListener(l -> addCourseAction(textFieldName, textFieldNum));

    }

    private static void addCourseAction(JTextField nameField, JTextField numField) {
        Course courseToBeAdded = new Course(nameField.getText(), Integer.parseInt(numField.getText()));
        String facultyInPut = (String) comboBox.getSelectedItem();
        Faculty faculty = new Faculty(facultyInPut);
        Faculty findResult = coursesToDos.courses.findFaculty(facultyInPut);
        if (findResult != null) {
            courseToBeAdded.addFaculty(findResult);
        } else {
            courseToBeAdded.addFaculty(faculty);
        }
        coursesToDos.addNewCourseReviewPlaceHolder(courseToBeAdded);
        coursesToDos.courses.loadCourse(courseToBeAdded);
        courseAdded(courseToBeAdded.getName() + courseToBeAdded.getNumber());
    }

    private static void courseAdded(String courseInfo) {
        JFrame courseAddedFrame = jframeGeneratorr("Course Added", 100);
        JLabel courseAddedLabel = new JLabel(courseInfo + " Successfully Added!");
        JPanel panel = new JPanel();
        panel.setBounds(20, 230, 350, 50);
        panel.add(courseAddedLabel);
        courseAddedFrame.add(panel);
    }

    private static JFrame jframeGeneratorr(String s, int i) {
        JFrame addCourseFrame = new JFrame(s);
        addCourseFrame.setBounds(i, i, 390, 360);
        addCourseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCourseFrame.setLayout(null);
        addCourseFrame.setVisible(true);
        return addCourseFrame;
    }

    private static JPanel panel(String s, int y, JTextField textField) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(s);
        textField.setBounds(75, 80, 50, 30);
        panel.setBounds(75, y, 250, 50);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private static void picture(Frame frame) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("./data/CourseReview.jpg");
        label.setIcon(img);
        panel.setBounds(25, 20, 350, 75);
        frame.add(panel);
        panel.add(label);
    }

    private static JButton arrowButton(int x, int y) {
        JButton arrowButton = new JButton();
        arrowButton.setIcon(new ImageIcon("./data/Arrow.jpg"));
        arrowButton.setBounds(x, y, 40, 40);
        return arrowButton;
    }

    private static void warning(JFrame frame) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon img = new ImageIcon("./data/Warning.jpg");
        label.setIcon(img);
        panel.setBounds(150, 50, 100, 100);
        frame.add(panel);
        panel.add(label);
    }

    private static double averageRating() {
        RatingList ratings = (RatingList) foundCourse.getRatingList();
        return ratings.averageRating();
    }
}
