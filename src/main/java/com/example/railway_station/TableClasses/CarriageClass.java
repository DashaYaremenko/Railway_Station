package com.example.railway_station.TableClasses;

public class CarriageClass {
    private String ID;
    private String TypeCarrig;
    private int TrainID;
    private CarriageClass(String id, String typeCarrig, int trainID) {
        this.ID = id;
        this.TypeCarrig = typeCarrig;
        this.TrainID = trainID;
    }
    public String getID() {return ID;}
    public void setID(String id) {this.ID = id;}
    public String getTypeCarrig() {return TypeCarrig;}
    public void setTypeCarrig(String typeCarrig) {this.TypeCarrig = typeCarrig;}
    public int getTrainID() {return TrainID;}
    public void setTrainID(int trainID) {this.TrainID = trainID;}
}
