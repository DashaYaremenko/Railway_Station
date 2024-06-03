package com.example.railway_station;

import com.example.railway_station.TableClasses.StatusTicketClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class StatusTicket {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";

    @FXML
    private Button statusButton;
    @FXML
    private TextField nameClient, lastNameClient;
    @FXML
    private TableView<StatusTicketClass> ticketTable;
    @FXML
    private TableColumn<StatusTicketClass, Integer> ticketIDCol;
    @FXML
    private TableColumn<StatusTicketClass, String> clientIdCol;
    @FXML
    private TableColumn<StatusTicketClass, Integer> trainIDCol;
    @FXML
    private TableColumn<StatusTicketClass, String> station1Col;
    @FXML
    private TableColumn<StatusTicketClass, String> cruiseId1Col;
    @FXML
    private TableColumn<StatusTicketClass, String> station2Col;
    @FXML
    private TableColumn<StatusTicketClass, String> cruiseId2Col;
    @FXML
    private TableColumn<StatusTicketClass, String> numCarrigeCol;
    @FXML
    private TableColumn<StatusTicketClass, Double> costTicketCol;
    @FXML
    private TableColumn<StatusTicketClass, Boolean> linensCol;
    @FXML
    private TableColumn<StatusTicketClass, Boolean> drinkCol;
    @FXML
    private TableColumn<StatusTicketClass, Boolean> snakesCol;

    @FXML
    private void lookTicket() {
        String name = nameClient.getText();
        String lastName = lastNameClient.getText();
        ObservableList<StatusTicketClass> ticketList = FXCollections.observableArrayList();

        String sql = "SELECT t.TicketID, " +
                "    CONCAT(cl.LastName, ' ', cl.FirstName,' ', cl.TypeDoc) AS ClientName, " +
                "    t.TrainNum, " +
                "    s1.NameStation AS Station1, " +
                "    CONCAT(c1.DeparDate, ' ', ts1.DeparTime) AS Cruise1, " +
                "    s2.NameStation AS Station2, " +
                "    CONCAT(c2.ArrivDate, ' ', ts2.ArrivTime) AS Cruise2, " +
                "    CONCAT(cr.ID, ', ', cr.TypeCarrig) AS CarriageInfo, " +
                "    t.CostTicket, " +
                "    t.Linens, " +
                "    t.Drink, " +
                "    t.Snacks " +
                "FROM " +
                "    tickets t " +
                "JOIN " +
                "    clients cl ON t.ClientId = cl.ID " +
                "JOIN " +
                "    trainstations ts1 ON t.StationID1 = ts1.StationID " +
                "JOIN " +
                "    stations s1 ON ts1.StationID = s1.ID " +
                "JOIN " +
                "    trainstations ts2 ON t.StationID2 = ts2.StationID " +
                "JOIN " +
                "    stations s2 ON ts2.StationID = s2.ID " +
                "JOIN " +
                "    cruise c1 ON t.CruiseID1 = c1.ID " +
                "JOIN " +
                "    cruise c2 ON t.CruiseID2 = c2.ID " +
                "JOIN " +
                "    carriage cr ON t.CarriageID = cr.ID " +
                "WHERE " +
                "    cl.LastName = ? " +
                "    AND cl.FirstName = ?" +
                "LIMIT 1";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idTicket = resultSet.getInt("TicketID");
                    int idTrain = resultSet.getInt("TrainNum");
                    String cruise1 = resultSet.getString("Cruise1");
                    String cruise2 = resultSet.getString("Cruise2");
                    String station1 = resultSet.getString("Station1");
                    String station2 = resultSet.getString("Station2");
                    String clientName = resultSet.getString("ClientName");
                    String carriageInfo = resultSet.getString("CarriageInfo");
                    double costTicket = resultSet.getDouble("CostTicket");
                    boolean linens = resultSet.getBoolean("Linens");
                    boolean drink1 = resultSet.getBoolean("Drink");
                    boolean snacks = resultSet.getBoolean("Snacks");

                    ticketList.add(new StatusTicketClass(idTicket, clientName, idTrain, station1, cruise1, station2, cruise2, carriageInfo, costTicket, linens, drink1, snacks));
                }

                ticketIDCol.setCellValueFactory(new PropertyValueFactory<>("IDTicket"));
                clientIdCol.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
                trainIDCol.setCellValueFactory(new PropertyValueFactory<>("IDTrain"));
                station1Col.setCellValueFactory(new PropertyValueFactory<>("Station1"));
                cruiseId1Col.setCellValueFactory(new PropertyValueFactory<>("Cruise1"));
                station2Col.setCellValueFactory(new PropertyValueFactory<>("Station2"));
                cruiseId2Col.setCellValueFactory(new PropertyValueFactory<>("Cruise2"));
                numCarrigeCol.setCellValueFactory(new PropertyValueFactory<>("CarriageInfo"));
                costTicketCol.setCellValueFactory(new PropertyValueFactory<>("CostTicket"));
                linensCol.setCellValueFactory(new PropertyValueFactory<>("Linens"));
                drinkCol.setCellValueFactory(new PropertyValueFactory<>("Drink"));
                snakesCol.setCellValueFactory(new PropertyValueFactory<>("Snacks"));
                ticketTable.setItems(ticketList);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

