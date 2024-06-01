package com.example.railway_station;

import com.example.railway_station.TableClasses.CruiseClass;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class RecCruise {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField Time;
    @FXML
    public Button LookTime, FunDate,FunTime;
    @FXML
    public DatePicker Date;
    @FXML
    public TableView<CruiseClass> cruiseTableView;
    @FXML
    public TableColumn<CruiseClass, Time> TimeCol1;
    @FXML
    public TableColumn<CruiseClass, Date> DateCol1;
    @FXML
    public TableColumn<CruiseClass, Time> TimeCol2;
    @FXML
    public TableColumn<CruiseClass, Date> DateCol2;


}
