package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class AddingStudentPane extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 400;
    public final double HEIGHT = 450;
    private TextField studentIDTextField;
    private ListView<Course> studentCoursesListView;

    public AddingStudentPane() {
        VBox studentVBox = createStudentVBox();
        HBox buttonsHBox = createButtonsHBox();

        this.setCenter(studentVBox);
        this.setBottom(buttonsHBox);
        this.setPadding(new Insets(5));
    }

    private VBox createStudentVBox() {
        // a text field and a label for the student id
        studentIDTextField = new TextField();
        studentIDTextField.setPromptText("Ex. 201915320");
        Label studentIDLabel = new Label("Student ID:", studentIDTextField);
        studentIDLabel.setContentDisplay(ContentDisplay.BOTTOM);

        // a listView for the courses of the student
        studentCoursesListView = new ListView<>();
        studentCoursesListView.setItems(FXCollections.observableList(getOpenCourses()));
        studentCoursesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentCoursesListView.setMaxHeight(220);
        studentCoursesListView.setMinWidth(330);

        // Label for ListView of the student's courses
        Label studentCoursesLabel = new Label("Available Courses:", studentCoursesListView);
        studentCoursesLabel.setContentDisplay(ContentDisplay.BOTTOM);

        VBox addingStudentVBox = new VBox(10, studentIDLabel, studentCoursesLabel);
        addingStudentVBox.setAlignment(Pos.BOTTOM_CENTER);

        return addingStudentVBox;
    }

    private HBox createButtonsHBox() {
        // create the buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {CommonClass.setStudentsPain();});

        Button addStudentButton = new Button("Add");
        addStudentButton.setOnAction(e -> {
            ArrayList<Course> selectedCourses = new ArrayList<>(studentCoursesListView.getSelectionModel().getSelectedItems());
            for (Course course : selectedCourses){course.register();}
            String studentID = studentIDTextField.getText();
            Student newStudent = new Student(studentID, selectedCourses);
            CommonClass.studentList.add(newStudent);
            CommonClass.setStudentsPain();
        });

        HBox buttonsHBox = new HBox(10, cancelButton, addStudentButton);
        buttonsHBox.setAlignment(Pos.CENTER);

        return buttonsHBox;
    }

    private static ArrayList<Course> getOpenCourses(){
        ArrayList<Course> openCourses = new ArrayList<>();
        for (Course course : CommonClass.courseList) {
            if (course.getAvailableSeats() != 0) openCourses.add(course);
        }
        return openCourses;
    }
}
