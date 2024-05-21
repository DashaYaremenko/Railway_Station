package com.example.railway_station.TableClasses;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TrainStatClass {
    private int TrainID;
    private int StationID;
    private LocalTime ArrivTime;
    private LocalTime DeparTime;
    public TrainStatClass(int trainID, int stationID, LocalTime arrivTime, LocalTime deparTime) {
        this.TrainID = trainID;
        this.StationID = stationID;
        this.ArrivTime = arrivTime;
        this.DeparTime = deparTime;
    }
    public int getTrainID() {return TrainID;}
    public void setTrainID(int trainID) {this.TrainID = trainID;}
    public int getStationID() {return StationID;}
    public void setStationID(int stationID) {this.StationID = stationID;}
    public LocalTime getArrivTime() {return ArrivTime;}
    public void setArrivTime(LocalTime arrivTime) {this.ArrivTime = arrivTime;}
    public LocalTime getDeparTime() {return DeparTime;}
    public void setDeparTime(LocalTime deparTime) {this.DeparTime = deparTime;}
}
