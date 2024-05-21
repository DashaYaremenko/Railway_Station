package com.example.railway_station;

import com.example.railway_station.TableClasses.StationClass;
import com.example.railway_station.TableClasses.TrainStatClass;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RecStation {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    public TextField IDStation, stationName;
    public TextField IDTrain, arrivalTime, departureTime;
    public Button AddStationButton, LookStationButton, AssignStationTrainButton, DeleteStationTrainButton, UpdateStationTrainButton, FindStationTrainButton1, FindTrainStationButton2;
    public TableView<StationClass> stationTable;
    public TableView<TrainStatClass> trainStatTable;
    public TableColumn<StationClass,Integer> IdStation;
    public TableColumn<StationClass,String> StationName;
    public TableColumn<TrainStatClass,String> ArrivalTime;
    public TableColumn<TrainStatClass,String> DepartureTime;
    public TableColumn<TrainStatClass,Integer> IdTrain;
    public RecStation() {

    }
}

