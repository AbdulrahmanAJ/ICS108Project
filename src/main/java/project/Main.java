package project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		// Link the primary stage in the common class
		CommonClass.primaryStage = primaryStage;
		// start the primary stage
		CommonClass.startApplication();
		primaryStage.show();
	}
}
