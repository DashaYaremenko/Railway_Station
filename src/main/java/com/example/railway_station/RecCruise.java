package com.example.railway_station;

import com.example.railway_station.TableClasses.CruiseClass;
import com.example.railway_station.TableClasses.TrainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;

public class RecCruise {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField Time;
    @FXML
    public Button LookTime, FunDate,FunTime;
    @FXML
    public DatePicker DateP;
    @FXML
    public TableView<CruiseClass> cruiseTableView;
    @FXML
    public TableColumn<CruiseClass, String> TimeCol1;
    @FXML
    public TableColumn<CruiseClass, Date> DateCol1;
    @FXML
    public TableColumn<CruiseClass, String> TimeCol2;
    @FXML
    public TableColumn<CruiseClass, Date> DateCol2;

    @FXML
    private void ShowButtonAction(ActionEvent event) {
        ObservableList<CruiseClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM cruise";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    Date deparDate = resultSet.getDate("DeparDate");
                    String deparStationTime = resultSet.getString("DeparStationTime");
                    Date arrivDate = resultSet.getDate("ArrivDate");
                    String arrivStationTime = resultSet.getString("ArrivStationTime");
                    dataList.add(new CruiseClass( id, deparDate, deparStationTime, arrivDate,arrivStationTime));
                }
            }
            DateCol1.setCellValueFactory(new PropertyValueFactory<>("dateDepar"));
            TimeCol1.setCellValueFactory(new PropertyValueFactory<>("timeDepar"));
            DateCol2.setCellValueFactory(new PropertyValueFactory<>("dateArriv"));
            TimeCol2.setCellValueFactory(new PropertyValueFactory<>("timeArriv"));
            cruiseTableView.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void SearchOfDateAction(ActionEvent event) {
        ObservableList<CruiseClass> dataList = FXCollections.observableArrayList();
        LocalDate selectedDate = DateP.getValue();
        if (selectedDate == null) {
            return;
        }
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM cruise WHERE DeparDate = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, java.sql.Date.valueOf(selectedDate));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("ID");
                        Date deparDate = resultSet.getDate("DeparDate");
                        String deparStationTime = resultSet.getString("DeparStationTime");
                        Date arrivDate = resultSet.getDate("ArrivDate");
                        String arrivStationTime = resultSet.getString("ArrivStationTime");
                        dataList.add(new CruiseClass(id, deparDate, deparStationTime, arrivDate, arrivStationTime));
                    }
                }
            }
            ShowButtonAction(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}



