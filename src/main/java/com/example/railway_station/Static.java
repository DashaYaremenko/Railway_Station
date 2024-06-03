package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.sql.*;

public class Static {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
@FXML
private PieChart staticTicketTrains;
@FXML
private Button staticTicketTrainsButton;

public void staticTicTrain(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT t.ID AS train_id, " +
                    "t.NameM AS train_name, " +
                    "COUNT(tc.TicketID) AS total_tickets_sold " +
                    "FROM trains t " +
                    "JOIN tickets tc ON t.ID = tc.TrainNum " +
                    "GROUP BY t.ID, t.NameM";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String trainName = resultSet.getString("train_id");
                int ticketCount = resultSet.getInt("total_tickets_sold");

                // Додавання даних до PieChart
                PieChart.Data slice = new PieChart.Data(trainName + " (" + ticketCount + ")", ticketCount);
                staticTicketTrains.getData().add(slice);
                slice.getNode().lookup(".chart-pie-label").setStyle("-fx-font-size: 14px;");

            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
