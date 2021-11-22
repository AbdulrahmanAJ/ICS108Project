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


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public static final double WIDTH = 700;
    public static final double HEIGHT = 500;

    public StudentPain() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
           CommonClass.setMainPain();
        });

        // The previous button
        Button previousButton = new Button("<Previous");

        // The next button
        Button nextButton = new Button("Next>");

        // The register button
        Button registerButton = new Button("Register");
        // The drop button
        Button dropButton = new Button("Drop");

        // The search button
        Button searchButton = new Button("Search");

        // Hbox for buttons
        HBox hBoxForButtons = new HBox(10, backButton, previousButton, nextButton, registerButton, dropButton, searchButton);
        hBoxForButtons.setPadding(new Insets(20, 20, 20, 20));
        hBoxForButtons.setAlignment(Pos.CENTER);

        // Label and text field for student ID
        TextField IdTextField = new TextField();
        IdTextField.setPromptText("Enter ID");

        Label IdLabel = new Label("Student ID:", IdTextField);

        IdLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Label and ListView for registered courses
        ListView<Course> courseListView = new ListView<>
                (FXCollections.observableArrayList());
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Label courseLabel = new Label("Registered courses:", courseListView);
        courseLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Vbox for StudentID and registered Courses
        VBox studentVbox = new VBox(20, IdLabel, courseLabel);
        studentVbox.setPadding(new Insets(20, 20, 20, 20));
        // studentVbox.setAlignment(Pos.CENTER);

        // Label and ComboBox for open courses

        ComboBox<String> openComboBox = new ComboBox<>();

        ObservableList<String> openCourses =
                FXCollections.observableArrayList();

        openComboBox.getItems().addAll(openCourses);

        Label openCourseLabel = new Label("Open courses:", openComboBox);
        openCourseLabel.setContentDisplay(ContentDisplay.RIGHT);
        // Label and ComboBox for closed courses

        ComboBox<String> closedComboBox = new ComboBox<>();

        ObservableList<String> closedCourses =
                FXCollections.observableArrayList();

        closedComboBox.getItems().addAll();

        Label closedCourseLabel = new Label("Closed courses:", closedComboBox);
        closedCourseLabel.setContentDisplay(ContentDisplay.RIGHT);

        // Vbox for open and closed courses
        VBox coursesVbox = new VBox(10, openCourseLabel, closedCourseLabel);
        coursesVbox.setPadding(new Insets(20,20,20,20));
        coursesVbox.setAlignment(Pos.CENTER);

        // HBox for VBoxes

        HBox hBoxForCenter = new HBox(50, studentVbox, coursesVbox);
        hBoxForCenter.setPadding(new Insets(20, 20, 20, 20));
        hBoxForCenter.setAlignment((Pos.CENTER));

        // TODO: 14/11/2021 make the students pain

        // Add elements into scene
        this.setBottom(hBoxForButtons);
        this.setCenter(hBoxForCenter);

    }
}
