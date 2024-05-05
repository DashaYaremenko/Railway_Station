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
    private TableColumn<ClientClass, String>  LastNameCol, FirstNameCol, TypeDocCol;

    @FXML
    private void ShowButtonAction(ActionEvent event) {
        ObservableList<ClientClass> dataList = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("Id");
                    String nameL = resultSet.getString("LastName");
                    String nameF = resultSet.getString("FirstName");
                    String typD = resultSet.getString("TypeDoc");
                    dataList.add(new TrainClass(id, nameL, nameF, typD));
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


}
