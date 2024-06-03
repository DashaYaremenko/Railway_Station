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
    public TextField destination1, destination2;
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
    private TableColumn<CruiseClass, Date> DateTrainColumn1;
    @FXML
    private TableColumn<CruiseClass, Date> DateTrainColumn2;
    @FXML
    private TableColumn<TrainStatClass,Time> TimeTrainColumn1;
    @FXML
    private TableColumn<TrainStatClass,Time> TimeTrainColumn2;


    @FXML
    private void STButtonAction(ActionEvent event) {
        ObservableList<SearchTrainClass> dataList = FXCollections.observableArrayList();
        LocalDate selectedDate = datePicker.getValue();
        String departureStation = destination1.getText();
        String arrivalStation = destination2.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT " +
                    "    t.ID AS TrainNumber, " +
                    "    t.NameM AS TrainName, " +
                    "    s1.NameStation AS DepartureStation, " +
                    "    s2.NameStation AS ArrivalStation, " +
                    "    c.DeparDate AS DepartureDate, " +
                    "    ts1.DeparTime AS DepartureTime, " +
                    "    c.ArrivDate AS ArrivalDate, " +
                    "    ts2.ArrivTime AS ArrivalTime, " +
                    "    cr.NumSeats AS SeatCount, " +
                    "    cr.TypeCarrig AS CarriageType " +
                    "FROM " +
                    "    cruise c " +
                    "JOIN " +
                    "    trainstations ts1 ON c.DeparStationTime = ts1.ID " +
                    "JOIN " +
                    "    stations s1 ON ts1.StationID = s1.ID " +
                    "JOIN " +
                    "    trainstations ts2 ON c.ArrivStationTime = ts2.ID " +
                    "JOIN " +
                    "    stations s2 ON ts2.StationID = s2.ID " +
                    "JOIN " +
                    "    trains t ON ts1.TrainID = t.ID " +
                    "JOIN " +
                    "    carriage cr ON t.ID = cr.TrainID " +
                    "WHERE " +
                    "    ts1.DeparTime IS NOT NULL " +
                    "    AND ts2.ArrivTime IS NOT NULL " +
                    "    AND ts1.DeparTime < ts2.ArrivTime " +
                    "    AND c.DeparDate = ? " +
                    "    AND s1.NameStation = ? " +
                    "    AND s2.NameStation = ?;";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, java.sql.Date.valueOf(selectedDate));
                preparedStatement.setString(2, departureStation);
                preparedStatement.setString(3, arrivalStation);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String trainNumber = resultSet.getString("TrainNumber");
                        String trainName = resultSet.getString("TrainName");
                        String depStation = resultSet.getString("DepartureStation");
                        String arrStation = resultSet.getString("ArrivalStation");
                        Date depDate = resultSet.getDate("DepartureDate");
                        Time depTime = resultSet.getTime("DepartureTime");
                        Date arrDate = resultSet.getDate("ArrivalDate");
                        Time arrTime = resultSet.getTime("ArrivalTime");
                        int seatCount = resultSet.getInt("SeatCount");
                        String carriageType = resultSet.getString("CarriageType");
                        dataList.add(new SearchTrainClass(trainNumber, trainName, depStation, arrStation, depDate, depTime, arrDate, arrTime, seatCount, carriageType));
                    }
                }
            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("trainID"));
            NameTrainColumn.setCellValueFactory(new PropertyValueFactory<>("nameTrain"));
            NameM1_Column.setCellValueFactory(new PropertyValueFactory<>("departureStation"));
            NameM2_Column.setCellValueFactory(new PropertyValueFactory<>("arrivalStation"));
            DateTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
            TimeTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
            DateTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
            TimeTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("carriageCount"));
            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("carriageTypes"));

            TableShow.setItems(dataList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




