package project;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


import java.util.ArrayList;

public class CoursePane extends BorderPane {
    public final double WIDTH = 900;
    public final double HEIGHT = 700;

    public CoursePane() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            CommonClass.setMainPain();
        });

        this.setBottom(backButton);

        // TODO: 14/11/2021 make the courses pain
        this.setCenter(new Text("Courses Pane"));
    }

    // a function that takes a course and return a list of a students, that are taking the course
    public static ArrayList<Student> courseStudents(Course course) {
        ArrayList<Student> courseStudents = new ArrayList<>();
        for (Student student: CommonClass.studentList){
            if (student.getCourses().contains(course)){
                courseStudents.add(student);
            }
        }
        return courseStudents;
    }
}
