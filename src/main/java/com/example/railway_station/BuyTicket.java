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
    private Button BuyTicketBtn;
    private TextField LastName, FirstName;
    @FXML
    private MenuButton TypeDoc;
    private CheckBox Linens, Drink, Snacks;

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



//    @FXML
//    private void ByeTicketAction(ActionEvent event) {
//        String LastN = LastName.getText();
//        String CruID = CruiseID.getText();
//        String CarId = CarriageID.getText();
//        String TypeT = TypeTrain.getText();
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            String sql = "INSERT INTO trains (ID, NameM, TypeTrain,CruiseID,CarriageID) VALUES (?,?,?,?,?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, Id);
//                preparedStatement.setString(2, NameT);
//                preparedStatement.setString(3, TypeT);
//                preparedStatement.setString(4, CruID);
//                preparedStatement.setString(5, CarId);
//                int rowsAdd = preparedStatement.executeUpdate();
//                if (rowsAdd > 0) {
//                    System.out.println("Запис добавлено успішно");
//                    ShowButtonAction(event);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
