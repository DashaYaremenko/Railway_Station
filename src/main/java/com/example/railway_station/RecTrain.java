package com.example.railway_station;

import com.example.railway_station.TableClasses.TrainClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecTrain {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    public TextField IDTrain, NameTrain, CruiseID,CarriageID;
    @FXML
    public MenuButton typeTrainMenuButton;
    public MenuItem FlashMenuItem, ElectroMenuItem, DiselMenuItem;
    public Button AddTrainButton,DeleteTrainButton,UpdateTrainButton,LookTrainButton;
    private TableView <TrainClass> TrainTable;
    private TableColumn <TrainClass, Integer> IDTrainCol,CruiseIDCol,CarriageIDCol ;
    private TableColumn<TrainClass,String> NameTrainCol, TypeTrain;

    private void AddTrainButton(ActionEvent event){
        String Id = IDTrain.getText();
        String NameT = NameTrain.getText();
        String CruID = CruiseID.getText();
        String CarId = CarriageID.getText();
        String TypeT;
        if (FlashMenuItem.isSelected()) {TypeT = FlashMenuItem.getText();}
        else if (ElectroMenuItem.isSelected()) {TypeT = ElectroMenuItem.getText();}
        else if (DiselMenuItem.isSelected()) {TypeT = DiselMenuItem.getText();}
        else {TypeT = "Default";}
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO trains (ID, NameM, TypeTrain,CruiseID,CarriageID) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Id);
                preparedStatement.setString(2, NameT);
                preparedStatement.setString(3, TypeT);
                preparedStatement.setString(4, CruID);
                preparedStatement.setString(4, CarId);
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






}
