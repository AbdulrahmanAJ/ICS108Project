package project;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class CommonClass {
    // set the common variables
	public static ArrayList<Course> courseList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static MainPain mainPain = new MainPain();
    public static StudentPain studentsPain;
    public static CoursePane coursesPain;
    public static Scene mainScene = new Scene(mainPain);
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
        studentsPain = new StudentPain();
        coursesPain = new CoursePane();
    }

    // a function that save the data
    public static void saveData(){
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE) )) {
            // write the students and the course to file
            output.writeObject(CommonClass.courseList);
            output.writeObject(CommonClass.studentList);
            System.out.println("Data Saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // a functions for setting the panes
    public static void setMainPain() {
        mainScene.setRoot(mainPain);
        primaryStage.setWidth(mainPain.WIDTH);
        primaryStage.setHeight(mainPain.HEIGHT);
        primaryStage.centerOnScreen();
    }
    public static void setStudentsPain() {
        mainScene.setRoot(studentsPain);
        primaryStage.setWidth(studentsPain.WIDTH);
        primaryStage.setHeight(studentsPain.HEIGHT);
        primaryStage.centerOnScreen();
    }
    public static void setCoursesPain() {
        mainScene.setRoot(coursesPain);
        primaryStage.setWidth(coursesPain.WIDTH);
        primaryStage.setHeight(coursesPain.HEIGHT);
        primaryStage.centerOnScreen();
    }
}
