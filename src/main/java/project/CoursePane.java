package project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class CoursePane extends BorderPane {
    public final double WIDTH = 900;
    public final double HEIGHT = 700;
    Course currentCourse = CommonClass.courseList.get(0);
    ListView<Course> allCoursesListView = new ListView<>();
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
        allCoursesListView.setItems(FXCollections.observableList(CommonClass.courseList));
        allCoursesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course selectedCourse) {
                currentCourse = selectedCourse;
                refreshCoursePane();
            }
        });

        refreshCoursePane();
        // Add elements into the pane
        this.setPadding(new Insets(20));
        this.setBottom(buttonsHBox);
        this.setLeft(allCoursesListView);
        this.setCenter(centralVBox);
        this.setRight(rightVBox);
    }

    private VBox createCentralVBox() {


        currentCourseID = new Text();
        Label courseIdentificationLabel = new Label("Course ID:", currentCourseID);
        courseIdentificationLabel.setContentDisplay(ContentDisplay.RIGHT);

        currentCourseName = new Text();
        Label courseNameLabel = new Label("Course name:", currentCourseName);
        courseNameLabel.setContentDisplay(ContentDisplay.RIGHT);

        currentCourseDays = new Text();
        Label courseDaysLabel = new Label("Course days:", currentCourseDays);
        courseDaysLabel.setContentDisplay(ContentDisplay.RIGHT);

        currentCourseTime = new Text();
        Label courseTimeLabel = new Label("Course time:", currentCourseTime);
        courseTimeLabel.setContentDisplay(ContentDisplay.RIGHT);

        currentCourseStatus = new Text();
        Label courseStatusLabel = new Label("Course status:", currentCourseStatus);
        courseStatusLabel.setContentDisplay(ContentDisplay.RIGHT);

        VBox centralVBox = new VBox(createSearchHBox(), courseIdentificationLabel, courseNameLabel, courseDaysLabel,
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
        if (currentCourse.getAvailableSeats() == 0)
            currentCourseStatus.setText("Closed");
        else
            currentCourseStatus.setText("Open");
    }

    private VBox createRightVBox() {
        courseStudentsListView = new ListView<>();
        numberOfCourseStudents = new Text("Number of registered students is " + courseStudents(currentCourse).size());
        VBox rightVBox = new VBox(courseStudentsListView, numberOfCourseStudents);
        return rightVBox;
    }

    private HBox createButtonsHBox() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // when the button is pressed the pane will change to the mane pane
            CommonClass.setMainPain();
        });

        // The previous button
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

        // HBox for buttons
        HBox buttonsHBox = new HBox(10, backButton, previousButton, nextButton);
        buttonsHBox.setPadding(new Insets(20, 20, 20, 20));
        buttonsHBox.setAlignment(Pos.CENTER);

        return buttonsHBox;
    }

    private HBox createSearchHBox() {

        // an HBox for searching in the courses
        TextField searchIDTextField = new TextField("Enter Course ID:");
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

    // a function that takes a course and return a list of a students, that are taking the course
//    private ArrayList<Student> courseStudents(Course course) {
//        ArrayList<Student> courseStudents = new ArrayList<>();
//        for (Student student: CommonClass.studentList){
//            if (student.getCourses().contains(course)){
//                courseStudents.add(student);
//            }
//        }
//        return courseStudents;
//    }

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