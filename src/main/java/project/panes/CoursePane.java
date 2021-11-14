package project.panes;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import project.CommonClass;

public class CoursePane extends BorderPane {
    public CoursePane() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            CommonClass.mainScene.setRoot(CommonClass.mainPain);
        });
        this.setBottom(backButton);

        // TODO: 14/11/2021 make the courses pain
        this.setCenter(new Text("Courses Pane"));
    }
}
