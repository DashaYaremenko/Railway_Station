module com.example.railway_station {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.railway_station to javafx.fxml;
    exports com.example.railway_station;
}