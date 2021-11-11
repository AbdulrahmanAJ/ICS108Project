package project;

// Just a test class to check whether the data is read or not.

import javafx.application.Application;
import javafx.stage.Stage;

public class TestClass extends Application {
	 
	public static void main(String[] args) {
		launch(args);
		CommonClass.loadBinaryData();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
	}
}
