package com.example.railway_station;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.sql.*;

public class Static {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
@FXML
private PieChart staticTicketTrains;

public void staticTicTrain() {
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
                String trainName = resultSet.getString("train_name");
                int ticketCount = resultSet.getInt("total_tickets_sold");

                // Додавання даних до PieChart
                PieChart.Data slice = new PieChart.Data(trainName, ticketCount);
                staticTicketTrains.getData().add(slice);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

//запит на загальну кількість білетів
// SELECT t.ID AS train_id,
//t.NameM AS train_name,
//COUNT(tc.TicketID) AS total_tickets_sold
//FROM trains t
//JOIN tickets tc ON t.ID = tc.TrainNum
//GROUP BY t.ID, t.NameM;

}
