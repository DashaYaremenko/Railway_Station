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
    private TextField LastName, FirstName, IdTrain, IdCarriage;
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
        double baseCost = 100.0; // базова ціна квитка
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
    private void ByeTicketAction(ActionEvent event) {
        String lastName = LastName.getText();
        String firstName = FirstName.getText();
        String docType = TypeDoc.getText();
        String trainId = IdTrain.getText();
        String carriageId = IdCarriage.getText();
        String departureDate = datePicker.getValue().toString();
        boolean isLinens = Linens.isSelected();
        boolean isDrink = Drink.isSelected();
        boolean isSnacks = Snacks.isSelected();
        double cost = calculateCost(docType, isLinens, isDrink, isSnacks);
        Cost.setText(cost + " грн");
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO tickets (last_name, first_name, doc_type, train_id, carriage_id," +
                    " departure_date, linens, drink, snacks, cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql); {
                preparedStatement.setString(1, lastName);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, docType);
                preparedStatement.setString(4, trainId);
                preparedStatement.setString(5, carriageId);
                preparedStatement.setDate(6, Date.valueOf(departureDate));
                preparedStatement.setBoolean(7, isLinens);
                preparedStatement.setBoolean(8, isDrink);
                preparedStatement.setBoolean(9, isSnacks);
                preparedStatement.setDouble(10, cost);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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


}
