package com.example.railway_station;

import com.example.railway_station.TableClasses.TicketsClass;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StatusTicket {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    @FXML
    private TextField idClient;
    private TableView ticketTable;
    private TableColumn<TicketsClass,Integer> ticketID;
    private TableColumn<TicketsClass,Integer> trainID;
    private TableColumn<TicketsClass,Integer> cruiseId;
    private TableColumn<TicketsClass,Integer> station1;
    private TableColumn<TicketsClass,Integer> station2;
    private TableColumn<TicketsClass,Integer> clientId;
    private TableColumn<TicketsClass,Integer> numCarrige;
    private TableColumn<TicketsClass,Double> costTicket ;
    private TableColumn<TicketsClass,String> service;

    private void lookTicket() {


    }



}
