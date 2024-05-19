package com.example.railway_station;

import com.example.railway_station.TableClasses.CarriageClass;
import com.example.railway_station.TableClasses.TrainClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RecCarriage {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDCarriage, IDTrain, TypeCarriage, NumSeats;
    @FXML
    public Button AddCarriageButton, DeleteCarriageButton, UpdateCarriageButton, LookCarriageButton;
    @FXML
    private TableView<CarriageClass> CarriageTable;
    @FXML
    private TableColumn<CarriageClass, Integer> IDTrainCol, CarriageIDCol, NumSeatsCol;
    @FXML
    private TableColumn<CarriageClass, String> TypeCarriageCol;


}
