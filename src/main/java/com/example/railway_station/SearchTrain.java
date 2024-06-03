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
    private TableColumn<TrainStatClass,Time> TimeTrainColumn1;
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
                    "    t.Name AS TrainName, " +
                    "    s1.NameStation AS DepartureStation, " +
                    "    s2.NameStation AS ArrivalStation, " +
                    "    c.DeparDate AS DepartureDate, " +
                    "    ts1.DeparStationTime AS DepartureTime, " +
                    "    c.ArrivDate AS ArrivalDate, " +
                    "    ts2.ArrivStationTime AS ArrivalTime, " +
                    "    cr.SeatCount AS SeatCount, " +
                    "    cr.Type AS CarriageType " +
                    "FROM " +
                    "    cruise c " +
                    "JOIN " +
                    "    trains t ON c.TrainID = t.ID " +
                    "JOIN " +
                    "    trainstations ts1 ON t.ID = ts1.TrainID " +
                    "JOIN " +
                    "    stations s1 ON ts1.StationID = s1.ID " +
                    "JOIN " +
                    "    trainstations ts2 ON t.ID = ts2.TrainID " +
                    "JOIN " +
                    "    stations s2 ON ts2.StationID = s2.ID " +
                    "JOIN " +
                    "    carriages cr ON t.ID = cr.TrainID " +
                    "WHERE " +
                    "    ts1.DeparStationTime IS NOT NULL " +
                    "    AND ts2.ArrivStationTime IS NOT NULL " +
                    "    AND ts1.DeparStationTime < ts2.ArrivStationTime " +
                    "    AND c.DeparDate = ? " +
                    "    AND s1.NameStation = ? " +
                    "    AND s2.NameStation = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, java.sql.Date.valueOf(selectedDate));
                preparedStatement.setString(2, departureStation);
                preparedStatement.setString(3, arrivalStation);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String trainNumber = resultSet.getString("trainID");
                        String trainName = resultSet.getString("nameTrain");
                        String depStation = resultSet.getString("departureStation");
                        String arrStation = resultSet.getString("arrivalStation");
                        Date depDate = resultSet.getDate("departureDate");
                        Time depTime = resultSet.getTime("departureTime");
                        Date arrDate = resultSet.getDate("arrivalDate");
                        Time arrTime = resultSet.getTime("departureTime");
                        int seatCount = resultSet.getInt("carriageSeatCount");
                        String carriageType = resultSet.getString("carriageTypes");
                        dataList.add(new SearchTrainClass(trainNumber, trainName, depStation, arrStation, depDate, depTime, arrDate, arrTime, seatCount, carriageType));
                    }
                }
            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("trainNumber"));
            NameTrainColumn.setCellValueFactory(new PropertyValueFactory<>("trainName"));
            NameM1_Column.setCellValueFactory(new PropertyValueFactory<>("departureStation"));
            NameM2_Column.setCellValueFactory(new PropertyValueFactory<>("arrivalStation"));
            DateTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
            TimeTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
            DateTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
            TimeTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("seatCount"));
            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("carriageType"));

            TableShow.setItems(dataList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




