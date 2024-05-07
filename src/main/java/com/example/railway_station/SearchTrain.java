package com.example.railway_station;

import com.example.railway_station.TableClasses.TrainClass;
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
    private TableColumn<TrainClass, String> TypeCruColumn;
    @FXML
    private TableColumn<TrainClass, Integer> ColCruColumn;
    @FXML
    private TableColumn<TrainClass,Double> TimeTrainColumn;

    @FXML
    private void STButtonAction(ActionEvent event) {
        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT*FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String nameM = resultSet.getString("NameM");
                    String typeCru = resultSet.getString("TypeCru");
                    double timeTrain = resultSet.getDouble("TimeTrain");
                    int colCruise = resultSet.getInt("ColCruise");
                    TrainClass train = new TrainClass(id, nameM, typeCru, colCruise, (int) timeTrain);
                    dataList.add(train);
                }
            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameM_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
            TimeTrainColumn.setCellValueFactory(new PropertyValueFactory<>("timeTrain"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("colCruise"));
            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("typeCru"));
            TableShow.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
