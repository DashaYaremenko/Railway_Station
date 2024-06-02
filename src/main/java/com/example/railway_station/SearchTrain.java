package com.example.railway_station;

import com.example.railway_station.TableClasses.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private TableView<SearchTrainClass> TableShow;
    @FXML
    private TableColumn<TrainClass, String> IdColumn;
    @FXML
    private TableColumn<TrainClass, String> NameTrainColumn;
    @FXML
    private TableColumn<StationClass, String> NameM1_Column;
    @FXML
    private TableColumn<StationClass, String> NameM2_Column;
    @FXML
    private TableColumn<CarriageClass, String> TypeCruColumn;
    @FXML
    private TableColumn<CarriageClass, Integer> ColCruColumn;
    @FXML
    private TableColumn<CruiseClass,Timestamp> TimeTrainColumn1;
    @FXML
    private TableColumn<CruiseClass,Timestamp> TimeTrainColumn2;


    @FXML
    private void STButtonAction(ActionEvent event) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            return;
        }

        ObservableList<SearchTrainClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT t.ID AS trainID, t.NameM AS NameTrain, s1.NameStation AS departureStation, " +
                    "s2.NameStation AS arrivalStation, ts.DeparTime AS departureTime, ts.ArrivTime AS arrivalTime, " +
                    "COUNT(c.ID) AS carriageCount, GROUP_CONCAT(c.TypeCarrig) AS carriageTypes " +
                    "FROM trains t " +
                    "JOIN trainstations ts ON t.ID = ts.TrainId " +
                    "JOIN stations s1 ON ts.ID = s1.ID " +
                    "JOIN stations s2 ON ts.ID = s2.ID " +
                    "JOIN carriage c ON t.ID = c.TrainID " +
                    "WHERE DATE(ts.DeparTime) = ? " +
                    "GROUP BY t.ID, t.NameM, s1.NameStation, s2.NameStation, ts.DeparTime, ts.ArrivTime ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, java.sql.Date.valueOf(selectedDate));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("trainID");
                        String nameTrain = resultSet.getString("NameTrain");
                        String departureStation = resultSet.getString("departureStation");
                        String arrivalStation = resultSet.getString("arrivalStation");
                        LocalDateTime departureTime = resultSet.getTimestamp("departureTime").toLocalDateTime();
                        LocalDateTime arrivalTime = resultSet.getTimestamp("arrivalTime").toLocalDateTime();
                        int carriageCount = resultSet.getInt("carriageCount");
                        String carriageTypes = resultSet.getString("carriageTypes");
                        SearchTrainClass searchTrainClass = new SearchTrainClass(id, nameTrain, departureStation, arrivalStation, departureTime, arrivalTime, carriageCount, carriageTypes);
                        dataList.add(searchTrainClass);
                    }
                }
            }

            IdColumn.setCellValueFactory(new PropertyValueFactory<>("trainID"));
            NameTrainColumn.setCellValueFactory(new PropertyValueFactory<>("nameTrain"));
            NameM1_Column.setCellValueFactory(new PropertyValueFactory<>("departureStation"));
            NameM2_Column.setCellValueFactory(new PropertyValueFactory<>("arrivalStation"));
            TimeTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
            TimeTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("carriageCount"));
            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("carriageTypes"));

            TableShow.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}




