package com.example.railway_station;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

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
    @FXML
    private TableColumn<TrainClass, Integer> ColCruColumn;
    @FXML
    private TableColumn<TrainClass,Double> TimeTrainColumn;

    private void ShowButtonAction(ActionEvent event) {
        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT*FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String nameM = resultSet.getString("NameM");
                    double timeTrain = resultSet.getDouble("TimeTrain");
                    int colCruise = resultSet.getInt("ColCruise");
                    dataList.add(new TrainClass(id, nameM, timeTrain, colCruise));
                }
            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameM_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
            TimeTrainColumn.setCellValueFactory(new PropertyValueFactory<>("timeTrain"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("colCruise"));
            TableShow.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
