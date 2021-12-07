package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class MainPane extends BorderPane {
    // initialize the main variables
    public final double WIDTH = 600;
    public final double HEIGHT = 255;

    // the constructor for the main pain
    public MainPane() {
        // create the buttons and the texts
        HBox buttonsHBox = createButtonsHBox();
        VBox textsVBox = createTextVbox();

        // insert the nodes to the pane
        this.setPadding(new Insets(15, 30,10,30));
        this.setCenter(textsVBox);
        this.setBottom(buttonsHBox);
    }

    // a function that create the buttons HBox
    private HBox createButtonsHBox() {
        // create the buttons and set the actions for the buttons
        Button coursesButton = new Button("View courses");
        coursesButton.setOnAction(e -> {
            CommonClass.setCoursesPane();
        });

        Button studentsButton = new Button("View students Details");
        studentsButton.setOnAction(e -> {
            CommonClass.setStudentsPane();
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            CommonClass.saveData();
        });

        // insert the buttons in HBox
        HBox buttonsHBox = new HBox(10, coursesButton, studentsButton, saveButton);
        buttonsHBox.setPadding(new Insets(20));
        buttonsHBox.setAlignment(Pos.CENTER);
        return buttonsHBox;
    }

    // a function that create the text VBox
    private VBox createTextVbox(){
        // make the main text and the set the font
        Text mainText = new Text("Registration System");
        mainText.setFont(new Font("A GOOGLE", 35));

        // make the description of the system
        Text descriptionText = new Text("A program that enables the user to add students to a list,\n" +
                "view their courses, register these courses and drop them.");
        descriptionText.setFont(new Font("A GOOGLE", 15));

        // make a vBox and insert the texts init
        VBox textsVBox = new VBox(15, mainText, descriptionText);
        textsVBox.setPadding(new Insets(15));
        textsVBox.setAlignment(Pos.CENTER);

        return textsVBox;
    }
}
