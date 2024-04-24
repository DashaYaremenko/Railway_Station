package com.example.railway_station;

import com.example.railway_station.TableClasses.TrainClass;
import javafx.scene.control.*;

public class RecTrain {
    private static final String URL = "jdbc:mysql://localhost:3306/railwaystat";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    public TextField IDTrain, NameTrain, CruiseID,DateAndTime;
    public MenuButton typeTrainMenuButton;
    public MenuItem FlashMenuItem, ElectroMenuItem, DiselMenuItem;
    public Button AddTrainButton,DeleteTrainButton,UpdateTrainButton,LookTrainButton;
    private TableView <TrainClass> TrainTable;
    private TableColumn <TrainClass, Integer> IDTrainCol,CruiseIDCol ;
    private TableColumn<TrainClass,String> NameTrainCol,  DateAndTimeCol, TypeTrain;






}
