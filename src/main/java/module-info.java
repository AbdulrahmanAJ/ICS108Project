module com.example.ics108project {
    requires javafx.controls;
    requires javafx.fxml;


    exports project;
    opens project to javafx.fxml, javafx.controls;
}