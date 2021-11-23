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


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 1000;
    public final double HEIGHT = 650;
    // current selected, student and courses
    Student currentStudent;
    Course selectedCourseToDrop;
    Course selectedCourseToRegister;
    TextField IDTextField = new TextField();

    public StudentPain() {
        // fill the openCourses and the closedCourses
        HBox buttonsHBox = createButtonsHBox();
        VBox studentVBox = createStudentVBox(); // for Fahad
        VBox coursesVBox = createCoursesVBox(); // for Jamal

        // HBox for VBoxes
        HBox centerHBox = new HBox(50, studentVBox, coursesVBox);
        centerHBox.setPadding(new Insets(20, 20, 20, 20));
        centerHBox.setAlignment((Pos.CENTER));

        // Add elements into the pane
        this.setPadding(new Insets(10));
        this.setBottom(buttonsHBox);
        this.setCenter(centerHBox);
    }

    // a function that fill the list with the open courses, and the closed courses
    private VBox createStudentVBox() {
        // TODO: 23/11/2021 createStudentVBox by fahad
        // Label and text field for student ID
        // TextField IDTextField = new TextField();
        IDTextField.setPromptText("Enter ID");
        IDTextField.setText(CommonClass.studentList.get(0).getStudID());

        Label IdLabel = new Label("Student ID:             ", IDTextField);
        IdLabel.setContentDisplay(ContentDisplay.RIGHT);

        // getting the current student
        for (int i = 0; i < CommonClass.studentList.size(); i++){
            if (IDTextField.getText().equals(CommonClass.studentList.get(i).getStudID())){
                currentStudent = CommonClass.studentList.get(i);
            }
        }


        // Label and ListView for registered courses

        // TODO: 23/11/2021 make the registered courses ListView
        ListView<Course> courseListView = new ListView<> (FXCollections.observableArrayList(currentStudent.getCourses()));

        Label courseLabel = new Label("Registered courses:", courseListView);
        courseLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Vbox for StudentID and registered Courses
        VBox studentVbox = new VBox(20, IdLabel, courseLabel);

        studentVbox.setPadding(new Insets(20, 20, 20, 20));
        studentVbox.setAlignment(Pos.CENTER_LEFT);

        return studentVbox;
    }

    // a function that return a Vbox that contains the courses List Views
    private VBox createCoursesVBox() {
        // get the list of the closed and open courses
        ArrayList<String>[] openAndClosedCourses = getOpenAndClosedCourses();
        ArrayList<String> openCourses = openAndClosedCourses[0];
        ArrayList<String> closedCourses = openAndClosedCourses[1];


        // TODO: 23/11/2021 create the openListView, and the closedListView, By Abdulrahman

        // TODO: 23/11/2021 create openCoursesListView, closedCoursesListView
        // initialize the openCoursesListView, and the closedCoursesListView
        ListView<String> openCoursesListView = new ListView<>(FXCollections.observableArrayList(openCourses));
        ListView<String> closedCoursesListView = new ListView<>(FXCollections.observableArrayList(closedCourses));

        // set the maximum height to the ListView
        openCoursesListView.setMaxHeight(200);
        closedCoursesListView.setMaxHeight(200);
        openCoursesListView.setMinWidth(350);
        closedCoursesListView.setMinWidth(350);

        // make the label for the listView
        Label openCoursesLabel = new Label("Open courses:", openCoursesListView);
        openCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);
        Label closedCoursesLabel = new Label("Closed courses:", closedCoursesListView);
        closedCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);



        // Vbox for open and closed courses Lists View
        VBox coursesVbox = new VBox(15, openCoursesLabel, closedCoursesLabel);
        coursesVbox.setPadding(new Insets(20,20,20,20));
        coursesVbox.setAlignment(Pos.CENTER);

        return coursesVbox;
    }

    // a function that return a Vbox that contains the studentIDTextField and the Student courses List View
    private HBox createButtonsHBox() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            CommonClass.setMainPain();
        });
        // The previous button
        Button previousButton = new Button("<Previous");
        previousButton.setOnAction(e -> {

        });
        // The next button
        Button nextButton = new Button("Next>");
        nextButton.setOnAction(e -> {

        });
        // The register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {

        });
        // The drop button
        Button dropButton = new Button("Drop");
        dropButton.setOnAction(e -> {

        });
        // The search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {

        });

        // HBox
        // for buttons
        HBox buttonsHBox = new HBox(10, backButton, previousButton, nextButton, registerButton, dropButton, searchButton);
        buttonsHBox.setPadding(new Insets(20, 20, 20, 20));
        buttonsHBox.setAlignment(Pos.CENTER);

        return buttonsHBox;
    }

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
