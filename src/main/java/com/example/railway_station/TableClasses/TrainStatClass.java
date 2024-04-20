package com.example.railway_station.TableClasses;

import java.time.LocalDateTime;

public class TrainStatClass {
    private int TrainID;
    private int StationID;
    private LocalDateTime ArrivTime;
    private LocalDateTime DeparTime;
    public TrainStatClass(int trainID, int stationID, LocalDateTime arrivTime, LocalDateTime deparTime) {
        this.TrainID = trainID;
        this.StationID = stationID;
        this.ArrivTime = arrivTime;
        this.DeparTime = deparTime;
    }
    public int getTrainID() {return TrainID;}
    public void setTrainID(int trainID) {this.TrainID = trainID;}
    public int getStationID() {return StationID;}
    public void setStationID(int stationID) {this.StationID = stationID;}
    public LocalDateTime getArrivTime() {return ArrivTime;}
    public void setArrivTime(LocalDateTime arrivTime) {this.ArrivTime = arrivTime;}
    public LocalDateTime getDeparTime() {return DeparTime;}
    public void setDeparTime(LocalDateTime deparTime) {this.DeparTime = deparTime;}
}
