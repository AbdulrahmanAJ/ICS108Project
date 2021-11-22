package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MainPain extends BorderPane {
    public static final double WIDTH = 500;
    public static final double HEIGHT = 300;

    public MainPain() {
        this.isResizable();
        // create the buttons
        Button coursesButton = new Button("View courses");
        Button studentsButton = new Button("View students Details");
        Button saveButton = new Button("Save");

        // set the actions for the buttons
        coursesButton.setOnAction(e -> {
            CommonClass.setCoursesPain();
        });
        studentsButton.setOnAction(e -> {
            CommonClass.setStudentsPain();

        });
        saveButton.setOnAction(e -> {
            CommonClass.saveData();
        });

        // make the text and the font
        Font font = new Font("A GOOGLE", 35);
        Text text = new Text("Registration System");
        text.setFont(font);
        HBox hBox = new HBox(10, coursesButton, studentsButton, saveButton);
        hBox.setPadding(new Insets(20));
        hBox.setAlignment(Pos.CENTER);

        // insert the nodes to the pane
        // TODO: 14/11/2021 style the pane
        this.setHeight(300);
        this.setWidth(500);
        this.setPadding(new Insets(50, 50,0,50));
        this.setCenter(text);
        this.setBottom(hBox);

    }
}
