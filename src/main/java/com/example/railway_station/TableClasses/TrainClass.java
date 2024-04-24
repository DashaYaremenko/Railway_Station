package com.example.railway_station.TableClasses;

import java.util.List;

public class TrainClass {
    private String ID;
    private String NameM;
    private String TypeTrain;
    private int CruiseID;
    List<CarriageClass> Carriage;

    public TrainClass(String id, String nameM, String typeTrain, int cruiseID, List<CarriageClass> carriage) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.CruiseID = cruiseID;
        this.Carriage = carriage;
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
    public int getCruiseID() {return CruiseID;}
    public void setCruiseID(int cruiseID) {this.CruiseID = cruiseID;}
    public List<CarriageClass> getCarriage() {return Carriage;}
    public void setCarriage(List<CarriageClass> carriage) {this.Carriage = carriage;}
}
