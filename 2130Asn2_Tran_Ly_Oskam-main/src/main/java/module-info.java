module com.example.hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.flightSystem to javafx.fxml;
    exports com.flightSystem;
}