package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddingStudentStage {

    private static Button addStudentButton;
    private static ListView<Course> studentCoursesListView;
    private static ArrayList<Course> registeredCourses=new ArrayList<>();

    public static ArrayList<Course> display() {
        // crete the stage
        Stage primaryStage = new Stage();

        // get the courses
        addStudentButton.setOnAction(e -> {
            registeredCourses= (ArrayList<Course>) studentCoursesListView.getSelectionModel().getSelectedItems();

        });

        // set the pain to the scene
        BorderPane addingStudentPane = createAddingStudentPane();
        Scene mainScene = new Scene(addingStudentPane);

        // show the stage
        primaryStage.setScene(mainScene);
        primaryStage.show();

        return registeredCourses;
    }

    private static BorderPane createAddingStudentPane() {
        BorderPane addingStudentPane = new BorderPane();

        // a text field and a label for the student id
        TextField studentIDTextField = new TextField("Ex. 201915320");
        Label studentIDLabel = new Label("Student ID:", studentIDTextField);
        studentIDLabel.setContentDisplay(ContentDisplay.BOTTOM);

        // a listView for the
        studentCoursesListView = new ListView<>();
        studentCoursesListView.setItems(FXCollections.observableList(getOpenCourses()));
        studentCoursesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        Label studentCoursesLabel = new Label("Courses:", studentCoursesListView);
        studentIDLabel.setContentDisplay(ContentDisplay.BOTTOM);

        addingStudentPane.setCenter(new VBox(15, studentIDLabel, studentCoursesLabel));
        return addingStudentPane;
    }
    private static ArrayList<Course> getOpenCourses(){
        ArrayList<Course> openCourses = new ArrayList<>();
        for (Course course : CommonClass.courseList) {
            if (course.getAvailableSeats() != 0) openCourses.add(course);
        }
        return openCourses;
    }
}
