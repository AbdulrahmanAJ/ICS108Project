module com.example.ics108project {
    requires javafx.controls;
    requires javafx.fxml;


//    opens com.example.ics108project to javafx.fxml;
//    exports com.example.ics108project;
    exports project;
    opens project to javafx.fxml;
}