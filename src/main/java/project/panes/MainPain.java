package project.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import project.CommonClass;

public class MainPain extends BorderPane {

    public MainPain() {
        // create the buttons
        Button coursesButton = new Button("View courses");
        Button studentsButton = new Button("View students Details");
        Button saveButton = new Button("Save");

        // set the actions for the buttons
        coursesButton.setOnAction(e -> {
            CommonClass.mainScene.setRoot(CommonClass.coursesPain);
        });
        studentsButton.setOnAction(e -> {
            CommonClass.mainScene.setRoot(CommonClass.studentsPain);
        });
        saveButton.setOnAction(e -> {
            CommonClass.saveData();
        });

        // insert the nodes to the pane
        // TODO: 14/11/2021 style the pane
        this.setCenter(new Text("Registration System"));
        this.setBottom(new HBox(10, coursesButton, studentsButton, saveButton));

    }

}
