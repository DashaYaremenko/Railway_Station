package com.example.railway_station;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SearchTrain {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextField destination1,destination2;
    @FXML
    public Button showButton;
    @FXML
    private TableView<TrainClass> TableShow;
    @FXML
    private TableColumn<TrainClass, String> IdColumn;
    @FXML
    private TableColumn<TrainClass, String> NameM_Column;
    @FXML
    private TableColumn<TrainClass, String> TypeTrainColumn;
    @FXML
    private TableColumn<TrainClass, String> TypeCruColumn;
    private TableColumn<TrainClass, Integer> ColCruColumn;
    private TableColumn<TrainClass,Double> TimeTrainColumn;



}
