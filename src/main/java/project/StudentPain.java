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
import java.util.Arrays;


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 850;
    public final double HEIGHT = 650;
    // current selected, student and courses
    Student currentStudent;
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
        this.setPadding(new Insets(10));
        this.setBottom(buttonsHBox);
        this.setCenter(centerHBox);
    }

    // a function that fill the list with the open courses, and the closed courses
    private VBox createStudentVBox() {
        // TODO: 23/11/2021 createStudentVBox by fahad
        // Label and text field for student ID
        TextField IdTextField = new TextField();
        IdTextField.setPromptText("Enter ID");

        Label IdLabel = new Label("Student ID:             ", IdTextField);
        IdLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Label and ListView for registered courses

        // TODO: 23/11/2021 make the registered courses ListView
        ListView<Course> courseListView = new ListView<> (FXCollections.observableArrayList());
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
        String[][] openAndClosedCourses = getOpenAndClosedCourses();
        String[] openCourses = openAndClosedCourses[0];
        String[] closedCourses = openAndClosedCourses[1];
        System.out.println(Arrays.toString(openCourses));
        System.out.println(Arrays.toString(closedCourses));

        // TODO: 23/11/2021 create the openListView, and the closedListView, By Abdulrahman

        // TODO: 23/11/2021 create openCoursesListView, closedCoursesListView
        // initialize the openCoursesListView, and the closedCoursesListView
        ListView<String> openCoursesListView = new ListView<>(FXCollections.observableArrayList(openCourses));
        ListView<String> closedCoursesListView = new ListView<>(FXCollections.observableArrayList(closedCourses));

        // set the selection mode of the list view
//        openCoursesListView.setSelectionModel(SelectionMode.MULTIPLE);
//        closedCoursesListView.setSelectionModel(SelectionMode.SINGLE);

        // set the maximum height to the ListView
        openCoursesListView.setMaxHeight(HEIGHT/3);
        closedCoursesListView.setMaxHeight(HEIGHT/3);

        // make the label for the listView
        Label openCoursesLabel = new Label("Open courses:", openCoursesListView);
        openCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);
        Label closedCoursesLabel = new Label("Closed courses:", closedCoursesListView);
        closedCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);



        // Vbox for open and closed courses Lists View
        VBox coursesVbox = new VBox(50, openCoursesLabel, closedCoursesLabel);
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

    private String[][] getOpenAndClosedCourses() {
        ArrayList<String> openCoursesList = new ArrayList<>();
        ArrayList<String> closedCoursesList = new ArrayList<>();
        for (Course course:CommonClass.courseList) {
            if (course.getAvailableSeats() != 0) openCoursesList.add(course.toString());
            else closedCoursesList.add(course.toString());
        }
        // make an array from the arrayList
//        Object[] openCoursesObjectsList = openCoursesList.toArray();
//        Object[] closedCoursesObjectsList = openCoursesList.toArray();
        String[] openCoursesArray = new String[openCoursesList.size()];
        String[] closedCoursesArray = new String[closedCoursesList.size()];
        for (int i=0; openCoursesList.size()>i; i++) openCoursesArray[0] = openCoursesList.get(0);
        for (int i=0; closedCoursesList.size()>i; i++) closedCoursesArray[0] = closedCoursesList.get(0);

        return new String[][] {openCoursesArray, closedCoursesArray};
    }
}
