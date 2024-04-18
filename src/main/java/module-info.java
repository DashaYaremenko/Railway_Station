module com.example.railway_station {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.railway_station to javafx.fxml;
    exports com.example.railway_station;
}