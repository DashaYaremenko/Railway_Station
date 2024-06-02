package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReturnTicket {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    private Button returnTicketButton;
    @FXML
    private TextField idClient;
    @FXML
    private TextField idTicket;

    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String idClient1 = idClient.getText();
            String idTicket1 = idTicket.getText();
            String sql = "START TRANSACTION;" +
                    "DELETE FROM tickets WHERE TicketID = ?;" +
                    "DELETE FROM clients WHERE ClientID = ?;" +
                    "COMMIT;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, idTicket1);
                preparedStatement.setString(2, idClient1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
