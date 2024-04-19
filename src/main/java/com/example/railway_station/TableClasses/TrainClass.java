package com.example.railway_station.TableClasses;

public class TrainClass {
    private String ID;
    private String NameM;
    private String TypeTrain;
    private int CruiseID;

    public TrainClass(String id, String nameM, String typeTrain, int cruiseID) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.CruiseID = cruiseID;
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
}
