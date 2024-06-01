package com.example.railway_station.TableClasses;

import java.util.List;

public class TrainClass {
    private String ID;
    private String NameM;
    private String TypeTrain;
    private int CruiseID;
    private int CarriageID;

    public TrainClass(String id, String nameM, String typeTrain, int carriageID) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.CarriageID = carriageID;
    }
    public String getId() {return ID;}
    public void setId(String id) {
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
    public int getCarriageID() {return CarriageID;}
    public void setCarriageID(int carriageID) {this.CarriageID = carriageID;}
}
