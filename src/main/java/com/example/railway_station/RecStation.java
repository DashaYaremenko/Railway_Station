package com.example.railway_station;

import com.example.railway_station.TableClasses.ClientClass;
import com.example.railway_station.TableClasses.StationClass;
import com.example.railway_station.TableClasses.TrainStatClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalTime;

public class RecStation {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDStation, stationName;
    public TextField IDTrain, arrivalTime, departureTime;
    public Button AddStationButton, LookStationButton,LookStationTrainButton, AssignStationTrainButton, DeleteStationTrainButton,
            UpdateStationTrainButton, FindStationTrainButton1, FindTrainStationButton2;
    public TableView<StationClass> stationTable;
    public TableView<TrainStatClass> trainStatTable;
    public TableColumn<StationClass,Integer> IdStationCol;
    public TableColumn<StationClass,String> StationNameCol;
    public TableColumn<TrainStatClass,String> ArrivalTimeCol;
    public TableColumn<TrainStatClass,String> DepartureTimeCol;
    public TableColumn<TrainStatClass,Integer> IdTrainCol;
    public TableColumn<TrainStatClass,Integer> IdStationCol1;

    @FXML
    private void ShowButtonAction1(ActionEvent event) {
        ObservableList<StationClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM stations";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String nameS = resultSet.getString("nameStation");
                    dataList.add(new StationClass(id, nameS));
                }
            }
            IdStationCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            StationNameCol.setCellValueFactory(new PropertyValueFactory<>("NameStation"));
            stationTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void ShowButtonAction2(ActionEvent event) {
        ObservableList<TrainStatClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM trainstations";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("stationID");
                    int idTrain = resultSet.getInt("trainID");
                    Time arrivTime =resultSet.getTime("arrivalTime");
                    Time deparTime=resultSet.getTime("deparTime");
                    dataList.add(new TrainStatClass(id, idTrain, arrivTime.toLocalTime(), deparTime.toLocalTime()));
                }
            }
            IdTrainCol.setCellValueFactory(new PropertyValueFactory<>("TrainID"));
            IdStationCol1.setCellValueFactory(new PropertyValueFactory<>("StationID"));
            ArrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("ArrivTime"));
            DepartureTimeCol.setCellValueFactory(new PropertyValueFactory<>("DeparTime"));
            trainStatTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

