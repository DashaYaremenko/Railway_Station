package com.example.railway_station;

import com.example.railway_station.TableClasses.CarriageClass;
import com.example.railway_station.TableClasses.CruiseClass;
import com.example.railway_station.TableClasses.StationClass;
import com.example.railway_station.TableClasses.TrainClass;
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
    private TableView<TrainClass> TableShow;
    @FXML
    private TableColumn<TrainClass, String> IdColumn;
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

    private void handleSearch() {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Дата вибрана");
            alert.setHeaderText(null);
            alert.setContentText("Обрана дата: " + selectedDate.toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void STButtonAction(ActionEvent event) {
        handleSearch();
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            return;
        }
        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
        ObservableList<StationClass> dataList1 = FXCollections.observableArrayList();
        ObservableList<CruiseClass> dataList2 = FXCollections.observableArrayList();
        ObservableList<CarriageClass> dataList3 = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT t.ID, t.TypeCru, t.ColCruise, s.NameStation, c.TypeCarrig, c.NumSeats, ts.ArrivTime, ts.DeparTime " +
                    "FROM trains t " +
                    "JOIN stations s ON t.StationID = s.ID " +
                    "JOIN carriages c ON t.ID = c.TrainID " +
                    "JOIN trainstation ts ON t.ID = ts.TrainId";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String nameM1 = resultSet.getString("NameM1");
                    String nameM2 = resultSet.getString("NameM2");
                    String typeCru = resultSet.getString("TypeCru");
                    LocalDateTime timeTrain1 = resultSet.getTimestamp("TimeTrain1").toLocalDateTime();
                    LocalDateTime timeTrain2 = resultSet.getTimestamp("TimeTrain2").toLocalDateTime();
                    int colCruise = resultSet.getInt("ColCruise");
                    TrainClass train = new TrainClass(id);
                    dataList.add(train);
                    StationClass stationClass = new StationClass(nameM1, nameM2);
                    dataList1.add(stationClass);
                    CruiseClass cruiseClass = new CruiseClass(timeTrain1, timeTrain2);
                    dataList2.add(cruiseClass);
                    String typeCarriage = resultSet.getString("TypeCarriage");
                    int numSeats = resultSet.getInt("NumSeats");
                    CarriageClass carriage = new CarriageClass(id, typeCarriage, numSeats);
                    dataList3.add(carriage);
                }

            }
            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameM1_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
            NameM2_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
            TimeTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("arrivTime"));
            TimeTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("deparTime"));
            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("colCruise"));
            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("typeCru"));
            TableShow.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    SELECT t.ID AS trainID,
//    t.NameM as NameTrain,
//    s1.NameStation AS departureStation,
//    s2.NameStation AS arrivalStation,
//    ts.DeparTime AS departureTime,
//    ts.ArrivTime AS arrivalTime,
//    COUNT(c.ID) AS carriageCount,
//    GROUP_CONCAT(c.TypeCarrig) AS carriageTypes
//    FROM trains t
//    JOIN trainstations ts ON t.ID = ts.TrainId
//    JOIN stations s1 ON ts.StationID = s1.ID
//    JOIN stations s2 ON ts.StationID = s2.ID
//    JOIN carriage c ON t.ID = c.TrainID
//    GROUP BY t.ID, s1.NameStation, s2.NameStation, ts.DeparTime, ts.ArrivTime

//    @FXML
//    private void STButtonAction(ActionEvent event) {
//        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
//        ObservableList<StationClass> dataList1 = FXCollections.observableArrayList();
//        ObservableList<CruiseClass> dataList2 = FXCollections.observableArrayList();
//        ObservableList<CarriageClass> dataList3 = FXCollections.observableArrayList();
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            String sql = "SELECT t.ID, t.TypeCru, t.ColCruise, s.NameStation, c.TypeCarrig, c.NumSeats " +
//                    "FROM trains t " +
//                    "JOIN stations s ON t.StationID = s.ID " +
//                    "JOIN carriages c ON t.ID = c.TrainID";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
//                 ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    String id = resultSet.getString("ID");
//                    String nameM1 = resultSet.getString("NameM1");
//                    String nameM2 = resultSet.getString("NameM2");
//                    String typeCru = resultSet.getString("TypeCru");
//                    double timeTrain1 = resultSet.getDouble("TimeTrain1");
//                    double timeTrain2 = resultSet.getDouble("TimeTrain2");
//                    int colCruise = resultSet.getInt("ColCruise");
//                    TrainClass train = new TrainClass(id);
//                    dataList.add(train);
//                    StationClass stationClass = new StationClass(resultSet.getInt("StationID"), resultSet.getString("StationName"));
//                    CruiseClass cruiseClass = new CruiseClass(resultSet.getTimestamp("ArrivTime").toLocalDateTime(), resultSet.getTimestamp("DeparTime").toLocalDateTime());
//                    CarriageClass carriageClass=new CarriageClass();
//                    dataList1.add(stationClass);
//                    dataList2.add(cruiseClass);
//                }
//            }
//            IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//            NameM1_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
//            NameM2_Column.setCellValueFactory(new PropertyValueFactory<>("nameM"));
//            TimeTrainColumn1.setCellValueFactory(new PropertyValueFactory<>("timeTrain"));
//            TimeTrainColumn2.setCellValueFactory(new PropertyValueFactory<>("timeTrain"));
//            ColCruColumn.setCellValueFactory(new PropertyValueFactory<>("colCruise"));
//            TypeCruColumn.setCellValueFactory(new PropertyValueFactory<>("typeCru"));
//            TableShow.setItems(dataList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}




