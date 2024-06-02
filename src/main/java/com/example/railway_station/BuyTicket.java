package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;


public class BuyTicket {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    private Button BuyTicketBtn;
    @FXML
    private TextField LastName, FirstName, IdTrain, IdCarriage, Station1, Station2;
    @FXML
    private MenuButton TypeDoc;
    @FXML
    private CheckBox Linens, Drink, Snacks;
    @FXML
    private Label Cost;
    @FXML
    private DatePicker datePicker;

    @FXML
    private void handleTypeDocSelection(ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        TypeDoc.setText(selectedItem.getText());
    }

    private double calculateCost(String docType, boolean isLinens, boolean isDrink, boolean isSnacks) {
        double baseCost = 100.0;
        switch (docType) {
            case "Повний":
                baseCost = 100.0;
                break;
            case "Дитячий":
                baseCost = 50.0;
                break;
            case "Пільговий":
                baseCost = 75.0;
                break;
        }
        if (isLinens) baseCost += 20.0;
        if (isDrink) baseCost += 10.0;
        if (isSnacks) baseCost += 15.0;
        return baseCost;
    }


    @FXML
    void handleLinens(ActionEvent event) {
        boolean linensSelected = Linens.isSelected();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE tickets SET linens = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, linensSelected);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDrink(ActionEvent event) {
        boolean DrinkSelected = Drink.isSelected();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE tickets SET Drink = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, DrinkSelected);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSnacks(ActionEvent event) {
        boolean SnacksSelected = Snacks.isSelected();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE tickets SET Snacks = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, SnacksSelected);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BuyTicketAction(ActionEvent event) {
        String lastName = LastName.getText();
        String firstName = FirstName.getText();
        String docType = TypeDoc.getText();
        String trainId = IdTrain.getText();
        String carriageId = IdCarriage.getText();
        String station1 = Station1.getText();
        String station2 = Station2.getText();
        Date departureDate = Date.valueOf(datePicker.getValue()); // Отримання дати з datePicker
        boolean isLinens = Linens.isSelected();
        boolean isDrink = Drink.isSelected();
        boolean isSnacks = Snacks.isSelected();
        double cost = calculateCost(docType, isLinens, isDrink, isSnacks);
        Cost.setText("Ціна: " + cost + " грн");
        String insertClientSql = "INSERT INTO clients (LastName, FirstName, TypeDoc) VALUES (?, ?, ?)";
        String ClientIdSql = "(SELECT ID FROM clients WHERE LastName = ? AND FirstName = ? AND TypeDoc = ?)";
        String getStationIdSql = "SELECT ts.StationID FROM trainstations ts JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ? AND ts.TrainID = ?";
        String getCruiseId1Sql = "SELECT c.ID FROM cruise c JOIN trainstations ts ON c.DeparStationTime = ts.ID JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ? AND c.DeparDate = ?";
        String getCruiseId2Sql = "SELECT c.ID FROM cruise c JOIN trainstations ts ON c.ArrivStationTime = ts.ID JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ?";
        String getStationIdSql2 = "SELECT ts.StationID FROM trainstations ts JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ? AND ts.TrainID = ?";
        String sql = "INSERT INTO tickets (ClientId, TrainNum, StationID1, CruiseID1, StationID2, CruiseID2, CarriageID, CostTicket, Linens, Drink, Snacks)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement insertClientStatement = connection.prepareStatement(insertClientSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientIdStatement = connection.prepareStatement(ClientIdSql);
             PreparedStatement getStationIdStatement = connection.prepareStatement(getStationIdSql);
             PreparedStatement getStationIdStatement2= connection.prepareStatement(getStationIdSql2);
             PreparedStatement getCruiseId1Statement = connection.prepareStatement(getCruiseId1Sql);
             PreparedStatement getCruiseId2Statement = connection.prepareStatement(getCruiseId2Sql);
             PreparedStatement insertTicketStatement = connection.prepareStatement(sql)) {


            insertClientStatement.setString(1, lastName);
            insertClientStatement.setString(2, firstName);
            insertClientStatement.setString(3, docType);
            insertClientStatement.executeUpdate();
            clientIdStatement.setString(1, lastName);
            clientIdStatement.setString(2, firstName);
            clientIdStatement.setString(3, docType);
            ResultSet clientIdRS = clientIdStatement.executeQuery();
            int clientId;
            if (clientIdRS.next()) {
                clientId = clientIdRS.getInt(1);
            } else {
                throw new SQLException();
            }
            getStationIdStatement.setString(1,station1);
            getStationIdStatement.setString(2,trainId);
            ResultSet stationId1RS = getStationIdStatement.executeQuery();
            int stationId1;
            if (stationId1RS.next()) {
                stationId1 = stationId1RS.getInt(1);
            } else {
                throw new SQLException();
            }
            getStationIdStatement2.setString(1,station2);
            getStationIdStatement2.setString(2,trainId);
            ResultSet stationId2RS = getStationIdStatement2.executeQuery();
            int stationId2;
            if (stationId2RS.next()) {
                stationId2 = stationId2RS.getInt(1);
            } else {
                throw new SQLException();
            }
            getCruiseId1Statement.setString(1,station1);
            getCruiseId1Statement.setString(2, String.valueOf(departureDate));
            ResultSet cruiseId1RS = getCruiseId1Statement.executeQuery();
            int cruiseId1;
            if (cruiseId1RS.next()) {
                cruiseId1 = cruiseId1RS.getInt(1);
            } else {
                throw new SQLException();
            }
            getCruiseId2Statement.setString(1,station2);
            ResultSet cruiseId2RS = getCruiseId2Statement.executeQuery();
            int cruiseId2;
            if (cruiseId2RS.next()) {
                cruiseId2 = cruiseId2RS.getInt(1);
            } else {
                throw new SQLException();
            }
            insertTicketStatement.setInt(1, clientId);
            insertTicketStatement.setInt(2, Integer.parseInt(trainId));
            insertTicketStatement.setInt(3, stationId1);
            insertTicketStatement.setInt(4, cruiseId1);
            insertTicketStatement.setInt(5, stationId2);
            insertTicketStatement.setInt(6, cruiseId2);
            insertTicketStatement.setInt(7, Integer.parseInt(carriageId));
            insertTicketStatement.setDouble(8, cost);
            insertTicketStatement.setBoolean(9, isLinens);
            insertTicketStatement.setBoolean(10, isDrink);
            insertTicketStatement.setBoolean(11, isSnacks);
            insertTicketStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
