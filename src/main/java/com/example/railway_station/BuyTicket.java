package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;

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
   private void ByeTicketAction(ActionEvent event) {
    // Отримання параметрів з форми
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
Cost.setText("Ціна: "+cost +" грн");
        String sql = "INSERT INTO tickets (ClientId, TrainNum, StationID1, CruiseID1, StationID2, CruiseID2, CarriageID, CostTicket, Linens, Drink, Snacks)\n" +
                "VALUES (\n" +
                "    (SELECT ID FROM clients WHERE LastName = ? AND FirstName = ? AND TypeDoc = ?),\n" +
                "    ?,  -- TrainNum\n" +
                "    (SELECT ts.StationID FROM trainstations ts JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ?),\n" +
                "    (SELECT c.ID FROM cruise c JOIN trainstations ts ON c.ID = ts.CruiseID1 JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ? AND c.DeparDate = ?),\n" +
                "    (SELECT ts.StationID FROM trainstations ts JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ?),\n" +
                "    (SELECT c.ID FROM cruise c JOIN trainstations ts ON c.ID = ts.CruiseID2 JOIN stations s ON ts.StationID = s.ID WHERE s.NameStation = ?),\n" +
                "    ?,  -- CarriageID\n" +
                "    ?,  -- CostTicket\n" +
                "    ?,  -- Linens\n" +
                "    ?,  -- Drink\n" +
                "    ?   -- Snacks\n" +
                ")";

try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    preparedStatement.setString(1, lastName);
    preparedStatement.setString(2, firstName);
    preparedStatement.setString(3, docType);
    preparedStatement.setInt(4, Integer.parseInt(trainId));
    preparedStatement.setString(5, station1);
    preparedStatement.setString(6, station1);
    preparedStatement.setDate(7, departureDate);
    preparedStatement.setString(8, station2);
    preparedStatement.setString(9, station2);
    preparedStatement.setInt(10, Integer.parseInt(carriageId));
    preparedStatement.setDouble(11, cost);
    preparedStatement.setBoolean(12, isLinens);
    preparedStatement.setBoolean(13, isDrink);
    preparedStatement.setBoolean(14, isSnacks);
    preparedStatement.executeUpdate();
    } catch(SQLException e) {
        throw new RuntimeException(e);
    }
}

}
