package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 800;
    public final double HEIGHT = 550;
    // current selected, student and courses
    Student currentStudent = CommonClass.studentList.get(0);
    Course selectedCourseToDrop;
    Course selectedCourseToRegister;
//    TextField IDTextField;
    ListView<Course> studentCoursesListView;
    ListView<String> openCoursesListView;
    ListView<String> closedCoursesListView;
    Text currentStudentID;

    public StudentPain() {
        // fill the openCourses and the closedCourses
        HBox buttonsHBox = createButtonsHBox();
        VBox leftVBox = new VBox(40,createSearchHBox(), createStudentVBox());
        VBox coursesVBox = createCoursesVBox();

        refreshListViews();

        // HBox for VBoxes
        HBox centerHBox = new HBox(25, leftVBox, coursesVBox);
        centerHBox.setPadding(new Insets(20));
        centerHBox.setAlignment((Pos.CENTER));

        // Add elements into the pane
        this.setPadding(new Insets(20));
        this.setBottom(buttonsHBox);
        this.setCenter(centerHBox);
    }

    // a function that fill the list with the open courses, and the closed courses
    private VBox createStudentVBox() {
        // Label and text field for student ID
        currentStudentID = new Text();

        Label IdLabel = new Label("Student ID:", currentStudentID);
        IdLabel.setContentDisplay(ContentDisplay.BOTTOM);

        // Label and ListView for registered courses
        studentCoursesListView = new ListView<>();
        studentCoursesListView.setMinWidth(330);
        studentCoursesListView.setMaxHeight(200);

        Label courseLabel = new Label("Registered courses:", studentCoursesListView);
        courseLabel.setContentDisplay(ContentDisplay.BOTTOM);

        // Vbox for StudentID and registered Courses
        VBox studentVbox = new VBox( IdLabel, courseLabel);

//        studentVbox.setPadding(new Insets(10));
        studentVbox.setAlignment(Pos.BOTTOM_CENTER);

        return studentVbox;
    }

    // a function that return a Vbox that contains the courses List Views
    private VBox createCoursesVBox() {
        // get the list of the closed and open courses
        ArrayList<String>[] openAndClosedCourses = getOpenAndClosedCourses();
        ArrayList<String> openCourses = openAndClosedCourses[0];
        ArrayList<String> closedCourses = openAndClosedCourses[1];

        // initialize the openCoursesListView, and the closedCoursesListView
        openCoursesListView = new ListView<>(FXCollections.observableArrayList(openCourses));
        closedCoursesListView = new ListView<>(FXCollections.observableArrayList(closedCourses));

        // set the maximum height to the ListView
        openCoursesListView.setMaxHeight(150);
        closedCoursesListView.setMaxHeight(150);
        openCoursesListView.setMinWidth(330);
        closedCoursesListView.setMinWidth(330);

        // make the label for the listView
        Label openCoursesLabel = new Label("Open courses:", openCoursesListView);
        openCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);
        Label closedCoursesLabel = new Label("Closed courses:", closedCoursesListView);
        closedCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);



        // Vbox for open and closed courses Lists View
        VBox coursesVbox = new VBox(0, openCoursesLabel, closedCoursesLabel);
        coursesVbox.setPadding(new Insets(10));
        coursesVbox.setAlignment(Pos.CENTER);

        return coursesVbox;
    }

    // a function that return a Vbox that contains the studentIDTextField and the Student courses List View
    private HBox createButtonsHBox() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // when the button is pressed the pane will change to the mane pane
            CommonClass.setMainPain();
            currentStudent = CommonClass.studentList.get(1);
        });

        // The previous button
        Button previousButton = new Button("<Previous");
        previousButton.setOnAction(e -> {
            // it will make the current student go to the back
            int currentStudentIndex = CommonClass.studentList.indexOf(currentStudent);
            currentStudent = CommonClass.studentList.get(currentStudentIndex-1);
            refreshListViews();
        });

        // The next button
        Button nextButton = new Button("Next>");
        nextButton.setOnAction(e -> {
            int currentStudentIndex = CommonClass.studentList.indexOf(currentStudent);
            if (currentStudentIndex != CommonClass.studentList.size()) currentStudent = CommonClass.studentList.get(currentStudentIndex+1);
            refreshListViews();
        });

        // The register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            // it will check for selectedCourseToRegister
        });

        // The drop button
        Button dropButton = new Button("Drop");
        dropButton.setOnAction(e -> {
            // it will check for selectedCourseToDrop
        });



        // HBox for buttons
        HBox buttonsHBox = new HBox(10, backButton, previousButton, nextButton, registerButton, dropButton);
        buttonsHBox.setPadding(new Insets(20, 20, 20, 20));
        buttonsHBox.setAlignment(Pos.CENTER);

        return buttonsHBox;
    }

    // a function that creates the search HBox, and changes the current students
    private HBox createSearchHBox() {

        // an HBox for searching in the students
        TextField searchIDTextField = new TextField("Enter Student ID:");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            for (Student student:CommonClass.studentList){
                if (searchIDTextField.getText().equals(student.getStudID())) currentStudent = student;
            }
            refreshListViews();
        });

        // creating the search HBox
        HBox searchHBox = new HBox(10, searchIDTextField, searchButton);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setPadding(new Insets(10));

        return searchHBox;
    }

    // a function the refresh all the list views
    private void refreshListViews() {
        // it will refresh the student courses
        studentCoursesListView.setItems(FXCollections.observableList(currentStudent.getCourses()));

        // it will refresh the closed and open courses list views
        ArrayList<String>[] openAndClosedCourses = getOpenAndClosedCourses();
        ArrayList<String> openCourses = openAndClosedCourses[0];
        ArrayList<String> closedCourses = openAndClosedCourses[1];
        openCoursesListView = new ListView<>(FXCollections.observableArrayList(openCourses));
        closedCoursesListView = new ListView<>(FXCollections.observableArrayList(closedCourses));

        // it will refresh the text field
        currentStudentID.setText(currentStudent.getStudID());
    }

    // a function that returns a two Array list one for closed and one for open courses
    private ArrayList<String>[] getOpenAndClosedCourses() {
        ArrayList<String> openCoursesList = new ArrayList<>();
        ArrayList<String> closedCoursesList = new ArrayList<>();
        for (Course course:CommonClass.courseList) {
            if (course.getAvailableSeats() != 0) openCoursesList.add(course.toString());
            else closedCoursesList.add(course.toString());
        }
        return new ArrayList[]{openCoursesList, closedCoursesList};
    }
}
