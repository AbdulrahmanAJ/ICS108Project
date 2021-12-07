package project;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CommonClass {
    // set the static common variables
	public static ArrayList<Course> courseList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static MainPane mainPane = new MainPane();
    public static Scene mainScene = new Scene(mainPane);
    public static Stage primaryStage;
    private static final  File FILE = new File("src/main/resources/project/Registration.dat");

    // the function that reads the file, It will fill the "courseList" and the "studentList"
	public static void loadBinaryData() {
        System.out.println("start loading");
        try (
            FileInputStream fileInputStream = new FileInputStream(FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            CommonClass.courseList = (ArrayList<Course>) objectInputStream.readObject();
            CommonClass.studentList = (ArrayList<Student>) objectInputStream.readObject();
            for (int i = 0; i < CommonClass.courseList.size(); i++) {
                System.out.println(CommonClass.courseList.get(i));
            }
            System.out.println(CommonClass.studentList.size());
            for (int i = 0; i < CommonClass.studentList.size(); i++) {
                System.out.println(CommonClass.studentList.get(i));
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // a function that save the data
    public static void saveData() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE) )) {
            // write the students and the course to file
            output.writeObject(CommonClass.courseList);
            output.writeObject(CommonClass.studentList);
            System.out.println("Data Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // functions for setting the panes
    public static void setMainPane() {
        mainScene.setRoot(mainPane);
        primaryStage.setWidth(mainPane.WIDTH);
        primaryStage.setHeight(mainPane.HEIGHT);
        primaryStage.centerOnScreen();
    }
    public static void setStudentsPane() {
        StudentPane studentPane = new StudentPane();
        mainScene.setRoot(studentPane);
        primaryStage.setWidth(studentPane.WIDTH);
        primaryStage.setHeight(studentPane.HEIGHT);
        primaryStage.centerOnScreen();
    }
    public static void setCoursesPane() {
        CoursePane coursesPane = new CoursePane();
        mainScene.setRoot(coursesPane);
        primaryStage.setWidth(coursesPane.WIDTH);
        primaryStage.setHeight(coursesPane.HEIGHT);
        primaryStage.centerOnScreen();
    }
    public static void setAddingStudentPane() {
        AddingStudentPane addingStudentPane = new AddingStudentPane();
        mainScene.setRoot(addingStudentPane);
        primaryStage.setWidth(addingStudentPane.WIDTH);
        primaryStage.setHeight(addingStudentPane.HEIGHT);
        primaryStage.centerOnScreen();
    }

    // a function that starts the program
    public static void startApplication() {
        loadBinaryData();
        primaryStage.setScene(CommonClass.mainScene);
        primaryStage.setResizable(false);
        // Saves the data when the program is closed
        primaryStage.setOnCloseRequest(e -> {CommonClass.saveData();});
    }
}
