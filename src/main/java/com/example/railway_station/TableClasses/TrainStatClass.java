package com.example.railway_station.TableClasses;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TrainStatClass {
    private int TrainID;
    private int StationID;
    private Time ArrivTime;
    private Time DeparTime;
    public TrainStatClass(int trainID, int stationID, Time arrivTime, Time deparTime) {
        this.TrainID = trainID;
        this.StationID = stationID;
        this.ArrivTime = arrivTime;
        this.DeparTime = deparTime;
    }
    public int getTrainID() {return TrainID;}
    public void setTrainID(int trainID) {this.TrainID = trainID;}
    public int getStationID() {return StationID;}
    public void setStationID(int stationID) {this.StationID = stationID;}
    public Time getArrivTime() {return ArrivTime;}
    public void setArrivTime(Time arrivTime) {this.ArrivTime = arrivTime;}
    public Time getDeparTime() {return DeparTime;}
    public void setDeparTime(Time deparTime) {this.DeparTime = deparTime;}
}
