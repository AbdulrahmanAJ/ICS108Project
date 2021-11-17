package project;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.io.*;
import java.util.ArrayList;

public class CommonClass {
    // set the common variables
	public static ArrayList<Course> courseList = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static BorderPane mainPain = new MainPain();
    public static BorderPane studentsPain = new StudentPain();
    public static BorderPane coursesPain = new CoursePane();
    public static Scene mainScene = new Scene(mainPain);

    private static final  File FILE = new File("src/main/resources/project/Registration.dat");

    // the function that reads the file, It will fill the "courseList" and the "studentList"
	public static void loadBinaryData() {
        System.out.println("start loading");
        try (
            FileInputStream fos = new FileInputStream(FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fos)
        ) {
            CommonClass.courseList = (ArrayList<Course>) objectInputStream.readObject();
            CommonClass.studentList = (ArrayList<Student>) objectInputStream.readObject();
            for (int i = 0; i < CommonClass.courseList.size(); i++) {
                System.out.println(CommonClass.courseList.get(i));
            }
            System.out.print(CommonClass.studentList.size());
            for (int i = 0; i < CommonClass.studentList.size(); i++) {
                System.out.println(CommonClass.studentList.get(i));
            }
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
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


}
