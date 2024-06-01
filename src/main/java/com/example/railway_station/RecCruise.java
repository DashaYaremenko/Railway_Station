package com.example.railway_station;

import com.example.railway_station.TableClasses.CruiseClass;
import javafx.scene.control.*;

import java.sql.*;

public class RecCruise {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    public TextField Time;
    public Button LookTime, FunDate,FunTime;
    public DatePicker Date;
    public TableView<CruiseClass> cruiseTableView;
    public TableColumn<CruiseClass, Time> TimeCol1;
    public TableColumn<CruiseClass, Date> DateCol1;
    public TableColumn<CruiseClass, Time> TimeCol2;
    public TableColumn<CruiseClass, Date> DateCol2;
}
