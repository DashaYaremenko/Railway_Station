package com.example.railway_station;

import com.example.railway_station.TableClasses.CarriageClass;
import com.example.railway_station.TableClasses.TrainClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class RecCarriage {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDCarriage, IDTrain, TypeCarriage, NumSeats;
    @FXML
    public Button AddCarriageButton, DeleteCarriageButton, UpdateCarriageButton, LookCarriageButton;
    @FXML
    private TableView<CarriageClass> CarriageTable;
    @FXML
    private TableColumn<CarriageClass, Integer> IDTrainCol, CarriageIDCol, NumSeatsCol;
    @FXML
    private TableColumn<CarriageClass, String> TypeCarriageCol;

    @FXML
    private void AddCarriageAction(ActionEvent event) {
        String ID = IDCarriage.getText();
        String IDtrain = IDTrain.getText();
        String NumSeat = NumSeats.getText();
        String TypeC = TypeCarriage.getText();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO carriage (ID, TypeCarrig, TrainID,NumSeats) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, ID);
                preparedStatement.setString(2, TypeC);
                preparedStatement.setString(3, IDtrain);
                preparedStatement.setString(4, NumSeat);
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
        ObservableList<CarriageClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM carriage";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("ID");
                    int IdTrain = resultSet.getInt("trainID");
                    String typeC = resultSet.getString("typeCarrig");
                    int NumS = resultSet.getInt("numSeats");
                    dataList.add(new CarriageClass(id, typeC,IdTrain, NumS));
                }
            }
            CarriageIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            TypeCarriageCol.setCellValueFactory(new PropertyValueFactory<>("typeCarrig"));
            IDTrainCol.setCellValueFactory(new PropertyValueFactory<>("trainID"));
            NumSeatsCol.setCellValueFactory(new PropertyValueFactory<>("numSeats"));
            CarriageTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String id = IDCarriage.getText();
            String sql = "DELETE FROM carriage WHERE ID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                int rowsDeleteT = preparedStatement.executeUpdate();
                if (rowsDeleteT > 0) {
                    System.out.println("Запис видалено успішно");
                    CarriageTable.getItems().clear();
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
            String Id = IDCarriage.getText();
            String fieldToUpdate = deterFieldToUpdate();
            String newValue = getFieldText(fieldToUpdate);

            String sql = "UPDATE carriage SET " + fieldToUpdate + " = ? WHERE ID = ?";

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
        if (!IDTrain.getText().isEmpty()) {
            return "TrainID";
        } else if (!NumSeats.getText().isEmpty()) {
            return "NumSeats";
        } else if (!TypeCarriage.getText().isEmpty()) {
            return "TypeCarrig";
        } else {
            throw new IllegalArgumentException("Invalid field to update");
        }
    }
    private String getFieldText(String fieldToUpdate) {
        switch (fieldToUpdate) {
            case "TrainID":
                return IDTrain.getText();
            case "NumSeats":
                return NumSeats.getText();
            case "TypeCarrig":
                return TypeCarriage.getText();
            default:
                throw new IllegalArgumentException("Invalid field to update: " + fieldToUpdate);
        }
    }

}
