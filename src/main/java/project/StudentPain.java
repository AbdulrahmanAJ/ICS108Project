package project;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class StudentPain extends BorderPane {
    public StudentPain() {
        // the back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            CommonClass.mainScene.setRoot(CommonClass.mainPain);
        });
        this.setBottom(backButton);

        // TODO: 14/11/2021 make the students pain
        this.setCenter(new Text("Students Pane"));
    }
}
