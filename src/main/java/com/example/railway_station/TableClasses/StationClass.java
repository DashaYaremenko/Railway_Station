package com.example.railway_station.TableClasses;

public class StationClass {
    private int ID;
    private String NameStation;
    private StationClass(int id, String nameStation) {
        this.ID = id;
        this.NameStation = nameStation;
    }
    public int getID() {return ID;}
    public void setID(int ID) {this.ID = ID;}
    public String getNameStation() {return NameStation;}
    public void setNameStation(String nameStation) {this.NameStation = nameStation;}
}
