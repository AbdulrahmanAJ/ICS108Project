package project;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.util.ArrayList;

public class CoursePane extends BorderPane {
    public final double WIDTH = 900;
    public final double HEIGHT = 700;
    Course currentCourse = CommonClass.courseList.get(0);
    Text courseName;

    public CoursePane() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            CommonClass.setMainPain();
        });

        this.setBottom(backButton);

        // TODO: 14/11/2021 make the courses pain
        this.setCenter(new Text("Courses Pane"));
    }

    private VBox courseDataVBox() {
        courseName = new Text();
        return new VBox();
    }

    // a function that refresh the pane
    private void refreshPane() {
        // refresh for the course data
        courseName.setText(currentCourse.getCourseName());

    }

    // a function that takes a course and return a list of a students, that are taking the course
    private ArrayList<Student> courseStudents(Course course) {
        ArrayList<Student> courseStudents = new ArrayList<>();
        for (Student student: CommonClass.studentList){
            if (student.getCourses().contains(course)){
                courseStudents.add(student);
            }
        }
        return courseStudents;
    }
}
