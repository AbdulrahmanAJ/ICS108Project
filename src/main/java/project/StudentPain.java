package project;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class StudentPain extends BorderPane {
    // the width and the height of the pane
    public final double WIDTH = 775;
    public final double HEIGHT = 350;
    // current selected, student and courses
    private Student currentStudent = CommonClass.studentList.get(0);
    private Course selectedCourseToDrop;
    private Course selectedCourseToRegister;
    private ListView<Course> studentCoursesListView;
    private ListView<Course> openCoursesListView;
    private Text currentStudentID;

    public StudentPain() {
        // fill the openCourses and the closedCourses
        HBox buttonsHBox = createButtonsHBox();
        VBox studentVBox = new VBox(10,createSearchHBox(), createStudentVBox());
        VBox coursesVBox = createCoursesListView();

        refreshPane();

        // HBox for VBoxes
        HBox centerHBox = new HBox(25, coursesVBox, studentVBox);
        centerHBox.setPadding(new Insets(0));
        centerHBox.setAlignment((Pos.CENTER));

        // Add elements into the pane
        this.setPadding(new Insets(5,0,0,0));
        this.setBottom(buttonsHBox);
        this.setCenter(centerHBox);
    }

    // a function that fill the list with the open courses, and the closed courses
    private VBox createStudentVBox() {
        // create an HBox for the current student id and next and previous

        // Label and text field for student ID
        currentStudentID = new Text();
        currentStudentID.setFont(Font.font("A GOOGLE", FontWeight.EXTRA_BOLD, 15));

        // create the label for the student id
        Text idLabel = new Text("Student ID:");
        idLabel.setFont(Font.font("A GOOGLE", 15));

        // Vbox for the current Student
        VBox studentIDInfoVBox = new VBox(2, idLabel, currentStudentID);
        studentIDInfoVBox.setPadding(new Insets(0, 20, 0, 20));

        // The previous button
        Button previousButton = new Button("<Previous");
        previousButton.setMinWidth(75);
        previousButton.setMinHeight(50);
        previousButton.setOnAction(e -> {
            // it will make the current student go to the back
            int currentStudentIndex = CommonClass.studentList.indexOf(currentStudent);
            if (currentStudentIndex != 0) currentStudent = CommonClass.studentList.get(currentStudentIndex-1);
            else currentStudent = CommonClass.studentList.get(CommonClass.studentList.size()-1);
            refreshPane();
        });

        // The next button
        Button nextButton = new Button("Next>");
        nextButton.setMinWidth(75);
        nextButton.setMinHeight(50);
        nextButton.setOnAction(e -> {
            int currentStudentIndex = CommonClass.studentList.indexOf(currentStudent);
            if (currentStudentIndex != CommonClass.studentList.size()-1) currentStudent = CommonClass.studentList.get(currentStudentIndex+1);
            else currentStudent = CommonClass.studentList.get(0);
            refreshPane();
        });

        HBox studentIDHBox = new HBox(0, previousButton, studentIDInfoVBox, nextButton);
        studentIDHBox.setAlignment(Pos.BOTTOM_CENTER);

        // Label and ListView for registered courses
        studentCoursesListView = new ListView<>();
        studentCoursesListView.setMinWidth(330);
        studentCoursesListView.setMaxHeight(100);

        Text courseLabel = new Text("Registered Courses: ");
        courseLabel.setFont(Font.font("A GOOGLE", 13));

        VBox RegisteredCourseVBox = new VBox(0,courseLabel,studentCoursesListView);

        // Vbox for StudentID and registered Courses
        VBox studentVbox = new VBox(15, studentIDHBox, RegisteredCourseVBox);
        studentVbox.setAlignment(Pos.BOTTOM_CENTER);

        return studentVbox;
    }

    // a function that return a Vbox that contains the courses List Views
    private VBox createCoursesListView() {
        // initialize the openCoursesListView, and the closedCoursesListView
        openCoursesListView = new ListView<>();

        // set the maximum height to the ListView
        openCoursesListView.setMaxHeight(220);
        openCoursesListView.setMinWidth(330);

        // make the label for the listView
        Text openCoursesLabel = new Text("Courses To Register: ");
        openCoursesLabel.setFont(Font.font("A GOOGLE", 13));


        // Vbox for open and closed courses Lists View
        VBox coursesVbox = new VBox(0, openCoursesLabel, openCoursesListView);
        coursesVbox.setPadding(new Insets(5, 0,0,0));


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

        // The register button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            // it will check for selectedCourseToRegister
            selectedCourseToRegister = openCoursesListView.getSelectionModel().getSelectedItem();
            selectedCourseToRegister.register();
            currentStudent.getCourses().add(selectedCourseToRegister);
            refreshPane();
        });

        // The drop button
        Button dropButton = new Button("Drop");
        dropButton.setOnAction(e -> {
            // it will check for selectedCourseToDrop
            selectedCourseToDrop = studentCoursesListView.getSelectionModel().getSelectedItem();
            selectedCourseToDrop.drop();
            currentStudent.getCourses().remove(selectedCourseToDrop);
            refreshPane();
        });

        // a Button for adding student
        Button addingStudentButton = new Button("Add Student");
        addingStudentButton.setOnAction(e -> {
            CommonClass.setAddingStudentPane();
            refreshPane();
        });


        // HBox for buttons
        HBox buttonsHBox = new HBox(10, backButton, registerButton, dropButton, addingStudentButton);
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
            refreshPane();
        });

        // creating the search HBox
        HBox searchHBox = new HBox(10, searchIDTextField, searchButton);
        searchHBox.setAlignment(Pos.CENTER);
        searchHBox.setPadding(new Insets(10));

        return searchHBox;
    }

    // a function the refresh all the list views
    private void refreshPane() {
        // it will refresh the student courses
        studentCoursesListView.setItems(FXCollections.observableList(currentStudent.getCourses()));

        // it will refresh the closed and open courses list views
        ArrayList<Course> openCourses = getOpenCourses();
        openCoursesListView.setItems(FXCollections.observableList(openCourses));

        // it will refresh the text field
        currentStudentID.setText(currentStudent.getStudID());
    }

    // a function that returns a two Array list one for closed and one for open courses
    private ArrayList<Course> getOpenCourses() {
        ArrayList<Course> openCoursesList = new ArrayList<>();
        for (Course course:CommonClass.courseList) {
            if (course.getAvailableSeats() != 0 && !currentStudent.getCourses().contains(course)) openCoursesList.add(course);
        }
        return openCoursesList;
    }
}
