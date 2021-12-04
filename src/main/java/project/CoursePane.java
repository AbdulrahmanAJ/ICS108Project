package project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.ArrayList;

public class CoursePane extends BorderPane {
    public final double WIDTH = 1075;
    public final double HEIGHT = 500;
    Course currentCourse = CommonClass.courseList.get(0);
    ListView<Course> allCoursesListView;
    ListView<String> courseStudentsListView;
    Text currentCourseID;
    Text currentCourseName;
    Text currentCourseDays;
    Text currentCourseTime;
    Text numberOfCourseStudents;
    Text currentCourseStatus;

    public CoursePane() {
        HBox buttonsHBox = createButtonsHBox();
        VBox centralVBox = createCentralVBox();
        VBox rightVBox = createRightVBox();
        VBox liftVBox = createLeftVBox();

        refreshCoursePane();
        // Add elements into the pane
        this.setPadding(new Insets(20));
        this.setBottom(buttonsHBox);
        this.setLeft(liftVBox);
        this.setCenter(centralVBox);
        this.setRight(rightVBox);
    }

    // a functions that create HBox for the nodes that in the left
    private VBox createLeftVBox() {
        allCoursesListView = new ListView<>();
        allCoursesListView.setItems(FXCollections.observableList(CommonClass.courseList));
        allCoursesListView.getSelectionModel().selectedItemProperty().addListener((observableValue, course, selectedCourse) -> {
            currentCourse = selectedCourse;
            refreshCoursePane();
        });
        allCoursesListView.setMinWidth(330);
        return new VBox(allCoursesListView);
    }

    // a functions that create HBox for the nodes that in the center
    private VBox createCentralVBox() {
        Font courseLabelsFont = Font.font("A GOOGLE", FontWeight.BOLD, 17);
        Font courseFieldsFont = Font.font("A GOOGLE", 14);

        Button previousButton = new Button("<Previous");
        previousButton.setOnAction(e -> {
            // it will make the current student go to the back
            int currentCourseIndex = CommonClass.courseList.indexOf(currentCourse);
            if (currentCourseIndex != 0) currentCourse = CommonClass.courseList.get(currentCourseIndex-1);
            else currentCourse = CommonClass.courseList.get(CommonClass.courseList.size()-1);
            refreshCoursePane();
        });
        // The next button
        Button nextButton = new Button("Next>");
        nextButton.setOnAction(e -> {
            int currentCourseIndex = CommonClass.courseList.indexOf(currentCourse);
            if (currentCourseIndex != CommonClass.courseList.size()-1) currentCourse = CommonClass.courseList.get(currentCourseIndex+1);
            else currentCourse = CommonClass.courseList.get(0);
            refreshCoursePane();
        });

        currentCourseID = new Text();
        currentCourseID.setFont(courseFieldsFont);
        Label courseIdentificationLabel = new Label("Course ID:", currentCourseID);
        courseIdentificationLabel.setContentDisplay(ContentDisplay.RIGHT);
        courseIdentificationLabel.setFont(courseLabelsFont);

        HBox courseIdentificationHBox = new HBox(10, previousButton, courseIdentificationLabel, nextButton);
        courseIdentificationHBox.setAlignment(Pos.CENTER);

        currentCourseName = new Text();
        currentCourseName.setFont(courseFieldsFont);
        Label courseNameLabel = new Label("Course name:", currentCourseName);
        courseNameLabel.setContentDisplay(ContentDisplay.RIGHT);
        courseNameLabel.setFont(courseLabelsFont);

        currentCourseDays = new Text();
        currentCourseDays.setFont(courseFieldsFont);
        Label courseDaysLabel = new Label("Course days:", currentCourseDays);
        courseDaysLabel.setContentDisplay(ContentDisplay.RIGHT);
        courseDaysLabel.setFont(courseLabelsFont);

        currentCourseTime = new Text();
        currentCourseTime.setFont(courseFieldsFont);
        Label courseTimeLabel = new Label("Course time:", currentCourseTime);
        courseTimeLabel.setContentDisplay(ContentDisplay.RIGHT);
        courseTimeLabel.setFont(courseLabelsFont);

        currentCourseStatus = new Text();
        currentCourseStatus.setFont(courseFieldsFont);
        Label courseStatusLabel = new Label("Course status:", currentCourseStatus);
        courseStatusLabel.setContentDisplay(ContentDisplay.RIGHT);
        courseStatusLabel.setFont(courseLabelsFont);

        VBox centralVBox = new VBox(createSearchHBox(), courseIdentificationHBox, courseNameLabel, courseDaysLabel,
                courseTimeLabel, courseStatusLabel);
        centralVBox.setAlignment(Pos.CENTER);
        return centralVBox;
    }

    // a function that refresh the pane
    private void refreshCoursePane() {
        currentCourseID.setText(currentCourse.getCourseID());
        currentCourseName.setText(currentCourse.getCourseName());
        currentCourseDays.setText(currentCourse.getCourseDays());
        currentCourseTime.setText(currentCourse.getCourseTime());
        courseStudentsListView.setItems(FXCollections.observableList(courseStudents(currentCourse)));
        numberOfCourseStudents.setText("Number of registered students is " + courseStudents(currentCourse).size());
        if (currentCourse.getAvailableSeats() == 0) {
            currentCourseStatus.setText("Closed");
            currentCourseStatus.setFill(Color.RED);
        }
        else {
            currentCourseStatus.setText("Open");
            currentCourseStatus.setFill(Color.GREEN);
        }
    }

    // a functions that create HBox for the nodes that in the right
    private VBox createRightVBox() {
        courseStudentsListView = new ListView<>();
        courseStudentsListView.setMaxWidth(200);
        numberOfCourseStudents = new Text("Number of registered students is " + courseStudents(currentCourse).size());
        numberOfCourseStudents.setFont(Font.font("A GOOGLE", FontWeight.BOLD, 12));
        VBox rightVBox = new VBox(numberOfCourseStudents, courseStudentsListView);
        return rightVBox;
    }

    // a functions that create a buttons HBox
    private HBox createButtonsHBox() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // when the button is pressed the pane will change to the mane pane
            CommonClass.setMainPain();
        });

        // HBox for buttons
        HBox backButtonHBox = new HBox(10, backButton);
        backButtonHBox.setPadding(new Insets(20, 20, 20, 150));

        backButtonHBox.setAlignment(Pos.CENTER);

        return backButtonHBox;
    }

    // a function that returns an HBox with the search nodes
    private HBox createSearchHBox() {
        // an HBox for searching in the courses
        TextField searchIDTextField = new TextField();
        searchIDTextField.setPromptText("Enter course ID:");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            for (Course course:CommonClass.courseList){
                if (searchIDTextField.getText().equals(course.getCourseID())) currentCourse = course;
            }
            refreshCoursePane();
        });

        // creating the search HBox
        HBox searchHBox = new HBox(10, searchIDTextField, searchButton);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setPadding(new Insets(10));

        return searchHBox;
    }

    // a function that returns a list of the students that taking the course
    private ArrayList<String> courseStudents(Course course) {
        ArrayList<String> courseStudents = new ArrayList<>();
        for (Student student: CommonClass.studentList){
            if (student.getCourses().contains(course)){
                courseStudents.add(student.getStudID());
            }
        }
        return courseStudents;
    }
}