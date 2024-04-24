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
    public TextField IDTrain, NameTrain, CruiseID,CarriageID;
    @FXML
    public MenuButton typeTrainMenuButton;
    public MenuItem FlashMenuItem, ElectroMenuItem, DiselMenuItem;
    @FXML
    public Button AddTrainButton,DeleteTrainButton,UpdateTrainButton,LookTrainButton;
    @FXML
    private TableView <TrainClass> TrainTable;
    private TableColumn <TrainClass, Integer> IDTrainCol,CruiseIDCol,CarriageIDCol ;
    private TableColumn<TrainClass,String> NameTrainCol, TypeTrain;

    @FXML
    private void AddTrainAction(ActionEvent event){
        String Id = IDTrain.getText();
        String NameT = NameTrain.getText();
        String CruID = CruiseID.getText();
        String CarId = CarriageID.getText();
        String TypeT= typeTrainMenuButton.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO trains (ID, NameM, TypeTrain,CruiseID,CarriageID) VALUES (?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Id);
                preparedStatement.setString(2, NameT);
                preparedStatement.setString(3, TypeT);
                preparedStatement.setString(4, CruID);
                preparedStatement.setString(5, CarId);
                int rowsAdd=preparedStatement.executeUpdate();
                if (rowsAdd>0){
                    System.out.println("Запис добавлено успішно");
                    ShowButtonAction(event);
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ShowButtonAction(ActionEvent event) {
        ObservableList<TrainClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT*FROM trains";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nameT = resultSet.getString("NameM");
                    String typeT = resultSet.getString("TypeT");
                    int cruID = resultSet.getInt("CruID");
                    int carId = resultSet.getInt("CarId");
                    dataList.add(new TrainClass(id, nameT, typeT, cruID,carId));
                }
            }
            IDTrainCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            NameTrainCol.setCellValueFactory(new PropertyValueFactory<>("nameT"));
            TypeTrain.setCellValueFactory(new PropertyValueFactory<>("typeT"));
            CruiseIDCol.setCellValueFactory(new PropertyValueFactory<>("cruID"));
            CarriageIDCol.setCellValueFactory(new PropertyValueFactory<>("carId"));
            TrainTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void DeleteButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String id = ID.getText();
            String sql = "DELETE FROM clients WHERE ID=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, id);
                int rowsDeleteT=preparedStatement.executeUpdate();
                if (rowsDeleteT>0){
                    System.out.println("Запис видалено успішно");
                    TableShow.getItems().clear();
                    ShowButtonAction(event);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
