module com.example.railway_station {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.railway_station to javafx.fxml;
    exports com.example.railway_station;
    exports com.example.railway_station.TableClasses;
    opens com.example.railway_station.TableClasses to javafx.fxml;
}