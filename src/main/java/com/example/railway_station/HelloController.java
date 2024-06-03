package com.example.railway_station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    //1 інтерфейс
    public void clickButtonClient(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("ImClientInter.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickButtonAdmin(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ImAdminInter.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //interface client
    public void clickButtonSearchTrain(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("SearchTrain.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clickButtonBuyTicket(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("BuyTicket.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clickButtonReturnTicket(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("ReturnTicket.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clickButtonStatusTicket(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("StatusTicket.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void clickButtonRecTrain(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("RecTrain.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void clickButtonRecClients(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("RecClients.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void clickButtonRecCarriage(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("RecCarriage.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // interface admin
    public void clickButtonStation(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("RecStation.fxml"));
            Parent parent=loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clickButtonRecTime(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("RecTimeTrain.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void clickButtonStatic(ActionEvent event){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("Static.fxml"));
            Parent parent= loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}