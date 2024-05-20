package com.example.railway_station;

import com.example.railway_station.TableClasses.TrainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.sql.*;

public class RecTrain {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDTrain, NameTrain, CruiseID, CarriageID;
    @FXML
    public TextField TypeTrain;
    @FXML
    public Button AddTrainButton, DeleteTrainButton, UpdateTrainButton, LookTrainButton;
    @FXML
    private TableView<TrainClass> TrainTable;
    @FXML
    private TableColumn<TrainClass, Integer> IDTrainCol, CruiseIDCol, CarriageIDCol;
    @FXML
    private TableColumn<TrainClass, String> NameTrainCol, TypeTrainCol;

    @FXML
    private void AddTrainAction(ActionEvent event) {
        String Id = IDTrain.getText();
        String NameT = NameTrain.getText();
        String CruID = CruiseID.getText();
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

    @FXML
    private void ShowButtonAction(ActionEvent event) {
        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM trains\n" +
                    "JOIN carriage ON trains.CarriageID = carriage.id;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    String nameT = resultSet.getString("NameM");
                    String typeT = resultSet.getString("TypeTrain");
                    int cruID = resultSet.getInt("CruiseID");
                    int carId = resultSet.getInt("CarriageId");
                    dataList.add(new TrainClass(id, nameT, typeT, cruID, carId));
                }
            }
            IDTrainCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameTrainCol.setCellValueFactory(new PropertyValueFactory<>("NameM"));
            TypeTrainCol.setCellValueFactory(new PropertyValueFactory<>("TypeTrain"));
            CruiseIDCol.setCellValueFactory(new PropertyValueFactory<>("CruiseID"));
            CarriageIDCol.setCellValueFactory(new PropertyValueFactory<>("CarriageID"));
            TrainTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String id = IDTrain.getText();
            String sql = "DELETE FROM trains WHERE ID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                int rowsDeleteT = preparedStatement.executeUpdate();
                if (rowsDeleteT > 0) {
                    System.out.println("Запис видалено успішно");
                    TrainTable.getItems().clear();
                    ShowButtonAction(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String Id = IDTrain.getText();
            String fieldToUpdate = deterFieldToUpdate();
            String newValue = getFieldText(fieldToUpdate);

            String sql = "UPDATE trains SET " + fieldToUpdate + " = ? WHERE ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newValue);
                preparedStatement.setString(2, Id);

                int rowsUpdate = preparedStatement.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println("Запис оновлено успішно");
                    ShowButtonAction(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String deterFieldToUpdate() {
        if (!NameTrain.getText().isEmpty()) {
            return "NameM";
        } else if (!TypeTrain.getText().isEmpty()) {
            return "TypeTrain";
        } else if (!CruiseID.getText().isEmpty()) {
            return "CruiseID";
        } else if (!CarriageID.getText().isEmpty()) {
            return "CarriageID";
        } else {
            throw new IllegalArgumentException("Invalid field to update");
        }
    }
    private String getFieldText(String fieldToUpdate) {
        switch (fieldToUpdate) {
            case "NameM":
                return NameTrain.getText();
            case "TypeTrain":
                return TypeTrain.getText();
            case "CruiseID":
                return CruiseID.getText();
            case "CarriageID":
                return CarriageID.getText();
            default:
                throw new IllegalArgumentException("Invalid field to update: " + fieldToUpdate);
        }
    }
}