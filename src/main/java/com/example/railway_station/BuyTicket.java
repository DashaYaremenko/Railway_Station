package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuyTicket {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    private Button BuyTicketBtn;
    @FXML
    private TextField LastName, FirstName,IdTrain, IdCarriage;
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
        MenuItem menuItem = (MenuItem) event.getSource();
        String selectedTypeDoc = menuItem.getText();
        addTypeDoc(selectedTypeDoc);
    }
    private void addTypeDoc(String selectedTypeDoc) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO clients (TypeDoc) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, selectedTypeDoc);
                statement.executeUpdate();
                System.out.println("Тип документа успішно додано до бази даних: " + selectedTypeDoc);
            } catch (SQLException e) {
                throw new RuntimeException("Помилка при вставці типу документа: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка з'єднання з базою даних: " + e.getMessage());
        }
    }

    private void addService(String service, double cost) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO tickets (Service, CostTicket) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, service);
                statement.setDouble(2, cost);
                statement.executeUpdate();
                System.out.println("Послуга успішно додана до бази даних: " + service);
            } catch (SQLException e) {
                throw new RuntimeException("Помилка при вставці послуги: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Помилка з'єднання з базою даних: " + e.getMessage());
        }
    }

    @FXML
    private void handleBuyTicket(ActionEvent event) {
        double totalPrice = 0.0;

        // Отримуємо прізвище та ім'я клієнта з текстових полів
        String lastName = LastName.getText();
        String firstName = FirstName.getText();

        // Перевіряємо, які послуги вибрані та додаємо їх до бази даних
        if (Linens.isSelected()) {
            addService("Linens", 10.0);
            totalPrice += 10.0;
        }
        if (Drink.isSelected()) {
            addService("Drink", 5.0);
            totalPrice += 5.0;
        }
        if (Snacks.isSelected()) {
            addService("Snacks", 7.0);
            totalPrice += 7.0;
        }

        // Оновлюємо відображення загальної ціни на мітці Price
        Cost.setText(String.valueOf(totalPrice));

        // Додаємо інформацію про клієнта до бази даних
        addClient(lastName, firstName);
    }



    @FXML
    private void ByeTicketAction(ActionEvent event) {
        String LastN = LastName.getText();
        String FirstN = FirstName.getText();
        String CarId = CarriageID.getText();
        String TypeT = TypeTrain.getText();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO trains (ID, NameM, TypeTrain,CruiseID,CarriageID) VALUES (?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Id);
                preparedStatement.setString(2, NameT);
                preparedStatement.setString(3, TypeT);
                preparedStatement.setString(4, CruID);
                preparedStatement.setString(5, CarId);
                int rowsAdd = preparedStatement.executeUpdate();
                if (rowsAdd > 0) {
                    System.out.println("Запис добавлено успішно");
                    ShowButtonAction(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
