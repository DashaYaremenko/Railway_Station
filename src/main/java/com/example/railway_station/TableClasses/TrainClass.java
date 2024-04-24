package com.example.railway_station.TableClasses;

import java.util.List;

public class TrainClass {
    private int ID;
    private String NameM;
    private String TypeTrain;
    private int CruiseID;
    private int Carriage;

    public TrainClass(int id, String nameM, String typeTrain, int cruiseID, int carriageID) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.CruiseID = cruiseID;
        this.Carriage = carriageID;
    }
    public int getId() {return ID;}
    public void setId(int id) {
        this.ID = id;
    }
    public String getNameM() {
        return NameM;
    }
    public void setNameM(String nameM) {this.NameM = nameM;}
    public String getTypeTrain() {return TypeTrain;}
    public void setTypeTrain(String typeTrain) {
        this.TypeTrain = typeTrain;
    }
    public int getCruiseID() {return CruiseID;}
    public void setCruiseID(int cruiseID) {this.CruiseID = cruiseID;}
    public int getCarriage() {return Carriage;}
    public void setCarriage(int carriageID) {this.Carriage = carriageID;}
}
