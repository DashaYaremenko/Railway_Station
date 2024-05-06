package com.example.railway_station;

import com.example.railway_station.TableClasses.ClientClass;
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
public class RecClients {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDClients, LastName, FirstName, TypeDoc;
    @FXML
    public Button UpdateClientsButton, LookClientsButton, StaticClientsButton1, StaticClientsButton2;
    @FXML
    private TableView<ClientClass> ClientsTable;
    @FXML
    private TableColumn<ClientClass, Integer> IDClientCol;
    @FXML
    private TableColumn<ClientClass, String> LastNameCol, FirstNameCol, TypeDocCol;

    @FXML
    private void ShowButtonAction(ActionEvent event) {
        ObservableList<ClientClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM clients";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("Id");
                    String nameL = resultSet.getString("LastName");
                    String nameF = resultSet.getString("FirstName");
                    String typD = resultSet.getString("TypeDoc");
                    dataList.add(new ClientClass(id, nameL, nameF, typD));
                }
            }
            IDClientCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            TypeDocCol.setCellValueFactory(new PropertyValueFactory<>("TypeDoc"));
            ClientsTable.setItems(dataList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void UpdateButtonAction(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String Id = IDClients.getText();
            String fieldToUpdate = deterFieldToUpdate();
            String newValue = getFieldText(fieldToUpdate);

            String sql = "UPDATE clients SET " + fieldToUpdate + " = ? WHERE ID = ?";

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
        if (!LastName.getText().isEmpty()) {
            return "LastName";
        } else if (!FirstName.getText().isEmpty()) {
            return "FirstName";
        } else if (!TypeDoc.getText().isEmpty()) {
            return "TypeDoc";
        } else {
            throw new IllegalArgumentException("Invalid field to update");
        }
    }

    private String getFieldText(String fieldToUpdate) {
        switch (fieldToUpdate) {
            case "LastName":
                return LastName.getText();
            case "FirstName":
                return FirstName.getText();
            case "TypeDoc":
                return TypeDoc.getText();
            default:
                throw new IllegalArgumentException("Invalid field to update: " + fieldToUpdate);
        }
    }

    @FXML
    private void StaticClientsButtonAction1(ActionEvent event) {
        ObservableList<ClientClass> dataList = FXCollections.observableArrayList();
        String typeDocuments = TypeDoc.getText();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM clients WHERE TypeDoc = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, typeDocuments);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("Id");
                        String lastName = resultSet.getString("LastName");
                        String firstName = resultSet.getString("FirstName");
                        String docType = resultSet.getString("TypeDoc");
                        dataList.add(new ClientClass(id, lastName, firstName, docType));
                    }
                }
            } catch (SQLException e) {throw new RuntimeException(e);}
        } catch (SQLException e) {throw new RuntimeException(e);}
        ClientsTable.setItems(dataList);
        IDClientCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        TypeDocCol.setCellValueFactory(new PropertyValueFactory<>("TypeDoc"));
    }
    private void StaticClientsButtonAction2(ActionEvent event) {
        ObservableList<ClientClass> dataList = FXCollections.observableArrayList();

    }
}