package project;

// Just a test class to check whether the data is read or not.

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	 
	public static void main(String[] args) {
		CommonClass.loadBinaryData();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){

		primaryStage.setScene(CommonClass.mainScene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> {
			CommonClass.saveData();
		});

	}
}
