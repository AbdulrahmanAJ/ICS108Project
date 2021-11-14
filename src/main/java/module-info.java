module com.example.ics108project {
    requires javafx.controls;
    requires javafx.fxml;


    exports project;
    opens project to javafx.fxml, javafx.controls;
    exports project.panes;
    opens project.panes to javafx.fxml, javafx.controls;
}