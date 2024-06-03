package com.example.railway_station;

import com.example.railway_station.TableClasses.TicketsClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private TextField nameClient, lastNameClient;
    @FXML
    private TableView ticketTable;
    @FXML
    private TableColumn<TicketsClass,Integer> ticketID;
    @FXML
    private TableColumn<TicketsClass,Integer> trainID;
    @FXML
    private TableColumn<TicketsClass,Integer> cruiseId1;
    @FXML
    private TableColumn<TicketsClass,Integer> cruiseId2;
    @FXML
    private TableColumn<TicketsClass,Integer> station1;
    @FXML
    private TableColumn<TicketsClass,Integer> station2;
    @FXML
    private TableColumn<TicketsClass,Integer> clientId;
    @FXML
    private TableColumn<TicketsClass,Integer> numCarrige;
    @FXML
    private TableColumn<TicketsClass,Double> costTicket ;
    @FXML
    private TableColumn<TicketsClass,Boolean> linens;
    @FXML
    private TableColumn<TicketsClass,Boolean> drink;
    @FXML
    private TableColumn<TicketsClass,Boolean> snakes;

    private void lookTicket() {
        String name = nameClient.getText();
        String lastName = lastNameClient.getText();
        ObservableList<TicketsClass> ticketList = FXCollections.observableArrayList();
        String sql = "SELECT " +
                "    t.TicketID, " +
                "    CONCAT(cl.LastName, ' ', cl.FirstName) AS ClientName, " +
                "    t.TrainNum, " +
                "    s1.NameStation AS Station1, " +
                "    CONCAT(c1.DeparDate, ' ', ts1.DeparTime) AS Cruise1, " +
                "    s2.NameStation AS Station2, " +
                "    CONCAT(c2.ArrivDate, ' ', ts2.ArrivTime) AS Cruise2, " +
                "    CONCAT(cr.ID, ' ', cr.TypeCarrig) AS CarriageInfo, " +
                "    t.CostTicket, " +
                "    t.Linens, " +
                "    t.Drink, " +
                "    t.Snacks, " +
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
                "    AND cl.FirstName = ?";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                 int idTicket=resultSet.getInt("TicketID");
                 int idTrain=resultSet.getInt("TrainNum");
                 String cruise1=resultSet.getString("Cruise1");
                 String cruise2=resultSet.getString("Cruise2");
                 String station1=resultSet.getString("Station1");
                 String station2=resultSet.getString("Station2");
                 String clientName=resultSet.getString("ClientName");
                 String carriageInfo=resultSet.getString("CarriageInfo");
                 Double costTicket=resultSet.getDouble("CostTicket");
                 Boolean linens=resultSet.getBoolean("Linens");
                 Boolean drink1=resultSet.getBoolean("Drink");
                 Boolean snacks=resultSet.getBoolean("Snacks");

                    ticketList.add(new TicketsClass(idTicket,clientName,idTrain,station1,cruise1,station2,cruise2,carriageInfo,costTicket,linens,drink1,snacks));
                }

            ticketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
            trainID.setCellValueFactory(new PropertyValueFactory<>("trainNum"));
            cruiseId1.setCellValueFactory(new PropertyValueFactory<>("cruiseId1"));
            cruiseId2.setCellValueFactory(new PropertyValueFactory<>("cruiseId2"));
            station1.setCellValueFactory(new PropertyValueFactory<>("station1"));
            station2.setCellValueFactory(new PropertyValueFactory<>("station2"));
            clientId.setCellValueFactory(new PropertyValueFactory<>("clientId"));
            numCarrige.setCellValueFactory(new PropertyValueFactory<>("carriageID"));
            costTicket.setCellValueFactory(new PropertyValueFactory<>("costTicket"));
            linens.setCellValueFactory(new PropertyValueFactory<>("linens"));
            drink.setCellValueFactory(new PropertyValueFactory<>("drink"));
            snakes.setCellValueFactory(new PropertyValueFactory<>("snacks"));
            ticketTable.setItems(ticketList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}
