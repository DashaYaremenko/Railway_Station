package com.example.railway_station;

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
    public TextField IDTrS;
    public TextField IDStation, stationName;
    public TextField IDTrain, arrivalTime, departureTime, IDStation2;
    public Button AddStationButton, LookStationButton, LookStationTrainButton, AssignStationTrainButton, DeleteStationTrainButton,
            UpdateStationTrainButton, FindStationTrainButton1, FindTrainStationButton2;
    public TableView<StationClass> stationTable;
    public TableView<TrainStatClass> trainStatTable;
    public TableColumn<StationClass, Integer> IdStationCol;
    public TableColumn<StationClass, String> StationNameCol;
    public TableColumn<TrainStatClass, String> ArrivalTimeCol;
    public TableColumn<TrainStatClass, String> DepartureTimeCol;
    public TableColumn<TrainStatClass, Integer> IdTrainCol;
    public TableColumn<TrainStatClass, Integer> IdStationCol1;

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
                    int id = resultSet.getInt("StationID");
                    int idTrain = resultSet.getInt("TrainID");
                    Time arrivTime = resultSet.getTime("ArrivTime");
                    Time deparTime = resultSet.getTime("DeparTime");
                    dataList.add(new TrainStatClass(idTrain, id, arrivTime, deparTime));
                }
            }
            IdStationCol1.setCellValueFactory(new PropertyValueFactory<>("StationID"));
            IdTrainCol.setCellValueFactory(new PropertyValueFactory<>("TrainID"));
            ArrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("ArrivTime"));
            DepartureTimeCol.setCellValueFactory(new PropertyValueFactory<>("DeparTime"));
            trainStatTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void AssignStationTrainAction(ActionEvent event) {
        String ID = IDStation2.getText();
        String IdTrain = IDTrain.getText();
        String aTime = arrivalTime.getText();
        String dTime = departureTime.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO trainstations (TrainID,StationID,ArrivTime, DeparTime) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(2, ID);
                preparedStatement.setString(1, IdTrain);
                preparedStatement.setString(3, aTime);
                preparedStatement.setString(4, dTime);
                int rowsAdd = preparedStatement.executeUpdate();
                if (rowsAdd > 0) {
                    System.out.println("Запис добавлено успішно");
                    ShowButtonAction2(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void AddStationAction(ActionEvent event) {
        String Id = IDStation.getText();
        String NameS = stationName.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO stations (ID, NameStation) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Id);
                preparedStatement.setString(2, NameS);
                int rowsAdd = preparedStatement.executeUpdate();
                if (rowsAdd > 0) {
                    System.out.println("Запис добавлено успішно");
                    ShowButtonAction1(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String idTrain = IDTrain.getText();
            String idStation = IDStation2.getText();
            String aTime = arrivalTime.getText();
            String dTime = departureTime.getText();
            String sql = "DELETE FROM trainstations WHERE TrainID=? and StationID=? and ArrivTime=? and DeparTime=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idTrain);
                preparedStatement.setString(2, idStation);
                preparedStatement.setString(3, aTime);
                preparedStatement.setString(4, dTime);
                int rowsDeleteT = preparedStatement.executeUpdate();
                if (rowsDeleteT > 0) {
                    System.out.println("Запис видалено успішно");
                    trainStatTable.getItems().clear();
                    ShowButtonAction2(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String Id = IDTrS.getText();
            String fieldToUpdate = deterFieldToUpdate();
            String newValue = getFieldText(fieldToUpdate);
            String sql = "UPDATE trainstations SET " + fieldToUpdate + " = ? WHERE ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newValue);
                preparedStatement.setString(2, Id);
                int rowsUpdate = preparedStatement.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println("Запис оновлено успішно");
                    ShowButtonAction2(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String deterFieldToUpdate() {
        if (!IDTrain.getText().isEmpty()) {
            return "TrainID";
        } else if (!IDStation2.getText().isEmpty()) {
            return "StationID";
        } else if (!arrivalTime.getText().isEmpty()) {
            return "ArrivTime";
        } else if (!departureTime.getText().isEmpty()) {
            return "DeparTime";
        } else {
            throw new IllegalArgumentException("Invalid field to update");
        }
    }

    private String getFieldText(String fieldToUpdate) {
        switch (fieldToUpdate) {
            case "TrainID":
                return IDTrain.getText();
            case "StationID":
                return IDStation2.getText();
            case "ArrivTime":
                return arrivalTime.getText();
            case "DeparTime":
                return departureTime.getText();
            default:
                throw new IllegalArgumentException("Invalid field to update: " + fieldToUpdate);
        }
    }

    @FXML
    private void findStationTrain(ActionEvent event) {
        String trainId = IDTrain.getText();
        ObservableList<TrainStatClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT ts.* FROM trainstations ts INNER JOIN stations s ON ts.StationID = s.ID " +
                    "WHERE ts.TrainID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, trainId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("TrainID");
                       int idStation = resultSet.getInt("StationID");
                        Time arrivTime = resultSet.getTime("ArrivTime");
                        Time deparTime = resultSet.getTime("DeparTime");
                        dataList.add(new TrainStatClass(id, idStation,arrivTime,deparTime));
                    }
                }
                trainStatTable.setItems(dataList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@FXML
private void findTrainStation(ActionEvent event) {
    String stationId = IDStation2.getText();
    ObservableList<TrainStatClass> dataList = FXCollections.observableArrayList();
    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
        String sql = "SELECT ts.* FROM trainstations ts INNER JOIN stations s ON ts.StationID = s.ID WHERE ts.StationID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, stationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int trainId = resultSet.getInt("TrainID");
                    int idStation = resultSet.getInt("StationID");
                    Time arrivTime = resultSet.getTime("ArrivTime");
                    Time deparTime = resultSet.getTime("DeparTime");
                    dataList.add(new TrainStatClass(trainId, idStation, arrivTime, deparTime));
                }
            }
            trainStatTable.setItems(dataList);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
