package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;

import java.sql.*;

public class Static {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    private PieChart staticTicketTrains;
    @FXML
    private Button staticTicketTrainsButton;
    @FXML
    private LineChart <String, Number> staticCountMonth;
    @FXML
    private Button staticCountMonthButton;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
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
                String trainName = resultSet.getString("train_name");
                int ticketCount = resultSet.getInt("total_tickets_sold");

                PieChart.Data slice = new PieChart.Data(trainName + " (" + ticketCount + ")", ticketCount);
                staticTicketTrains.getData().add(slice);

                slice.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        Text dataText = new Text(String.valueOf(ticketCount));
                        dataText.setStyle("-fx-font-size: 14px;");
                        dataText.setTranslateX(newValue.getLayoutBounds().getWidth() / 2);
                        dataText.setTranslateY(newValue.getLayoutBounds().getHeight() / 2);
                        ((javafx.scene.Group) newValue).getChildren().add(dataText);
                    }
                });
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void staticCountMonthAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "  SELECT DATE_FORMAT(c.DeparDate, '%Y-%m') AS month," +
                    "SUM(t.CostTicket) AS total_revenue" +
                    "    FROM tickets t" +
                    "    JOIN cruise c ON t.CruiseID1 = c.ID" +
                    "    GROUP BY DATE_FORMAT(c.DeparDate, '%Y-%m')" +
                    "    ORDER BY DATE_FORMAT(c.DeparDate, '%Y-%m');";
            ResultSet resultSet = statement.executeQuery(sql);
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Місячний дохід");

            while (resultSet.next()) {
                String month = resultSet.getString("month");
                double totalRevenue = resultSet.getDouble("total_revenue");

                XYChart.Data<String, Number> data = new XYChart.Data<>(month, totalRevenue);
                series.getData().add(data);
            }
            staticCountMonth.getData().add(series);

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
