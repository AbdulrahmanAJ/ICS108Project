package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 800;
    public final double HEIGHT = 600;
    // current selected, student and courses
    Student currentStudent = CommonClass.studentList.get(0);
    Course selectedCourseToDrop;
    Course selectedCourseToRegister;

    public StudentPain() {
        // fill the openCourses and the closedCourses
        HBox buttonsHBox = createButtonsHBox();
        VBox studentVBox = createStudentVBox(); // for Fahad
        VBox coursesVBox = createCoursesVBox(); // for Jamal

        // HBox for VBoxes
        HBox centerHBox = new HBox(100, studentVBox, coursesVBox);
        centerHBox.setPadding(new Insets(20, 20, 20, 20));
        centerHBox.setAlignment((Pos.CENTER));

        // Add elements into the pane
        this.setBottom(buttonsHBox);
        this.setCenter(centerHBox);
    }

    // a function that fill the list with the open courses, and the closed courses
    private VBox createStudentVBox() {
        // TODO: 23/11/2021 createStudentVBox by fahad
        // Label and text field for student ID
        TextField IDTextField = new TextField();
        IDTextField.setPromptText("Enter ID");

        Label IdLabel = new Label("Student ID:             ", IDTextField);
        IdLabel.setContentDisplay(ContentDisplay.RIGHT);

        //getting the current student
        for (int i = 0; i < CommonClass.studentList.size(); i++){
            if (IDTextField.getText().equals(CommonClass.studentList.get(i).getStudID())){
                currentStudent = CommonClass.studentList.get(i);
            }
        }


        // Label and ListView for registered courses

        // TODO: 23/11/2021 make the registered courses ListView
        ListView<Course> courseListView = new ListView<> (FXCollections.observableArrayList(currentStudent.getCourses()));
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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
        ArrayList<Course>[] openAndClosedCourses = getOpenAndClosedCourses();
        ArrayList<Course> openCourses = openAndClosedCourses[0];
        ArrayList<Course> closedCourses = openAndClosedCourses[1];

        // TODO: 23/11/2021 By Abdulrahman

        // TODO: 23/11/2021 add open courses to ComboBox
        ComboBox<String> openComboBox = new ComboBox<>();

        ObservableList<String> openCoursesList = FXCollections.observableArrayList();

        openComboBox.getItems().addAll(openCoursesList);

        Label openCourseLabel = new Label("Open courses:", openComboBox);
        openCourseLabel.setContentDisplay(ContentDisplay.RIGHT);
        // Label and ComboBox for closed courses

        // TODO: 23/11/2021 add closed courses to ComboBox
        ComboBox<String> closedComboBox = new ComboBox<>();

        ObservableList<String> closedCoursesList =
                FXCollections.observableArrayList();

        closedComboBox.getItems().addAll();

        Label closedCourseLabel = new Label("Closed courses:", closedComboBox);
        closedCourseLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Vbox for open and closed courses
        VBox coursesVbox = new VBox(80, openCourseLabel, closedCourseLabel);
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

    private ArrayList<Course>[] getOpenAndClosedCourses() {
        ArrayList<Course>[] openAndClosedCourses = new ArrayList[]{new ArrayList<Course>(), new ArrayList<Course>()};
        for (Course course:CommonClass.courseList) {
            if (course.getAvailableSeats() != 0) {
                openAndClosedCourses[0].add(course);
            } else {
                openAndClosedCourses[0].add(course);
            }
        }
        return openAndClosedCourses;
    }

}
