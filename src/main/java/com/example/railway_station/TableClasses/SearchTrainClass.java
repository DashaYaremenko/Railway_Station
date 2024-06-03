package com.example.railway_station.TableClasses;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class SearchTrainClass {
    private String trainID;
    private String nameTrain;
    private String departureStation;
    private String arrivalStation;
    private Date departureDate;
    private Time departureTime;
    private Date arrivalDate;
    private Time arrivalTime;
    private int carriageCount;
    private String carriageTypes;

    public SearchTrainClass(String trainID, String nameTrain, String departureStation, String arrivalStation, Date departureDate,  Time departureTime,Date arrivalDate,Time arrivalTime, int carriageCount, String carriageTypes) {
        this.trainID = trainID;
        this.nameTrain = nameTrain;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate=departureDate;
        this.arrivalDate=arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.carriageCount = carriageCount;
        this.carriageTypes = carriageTypes;
    }
    public String getTrainID() {return trainID;}
    public void setTrainID(String trainID) {this.trainID = trainID;}
    public String getNameTrain() {return nameTrain;}
    public void setNameTrain(String nameTrain) {this.nameTrain = nameTrain;}
    public String getDepartureStation() {return departureStation;}
    public void setDepartureStation(String departureStation) {this.departureStation = departureStation;}
    public String getArrivalStation() {return arrivalStation;}
    public void setArrivalStation(String arrivalStation) {this.arrivalStation = arrivalStation;}
    public Date getDepartureDate() {return departureDate;}
    public void setDepartureDate(Date departureDate) {this.departureDate = departureDate;}
    public Date getArrivalDate() {return arrivalDate;}
    public void setArrivalDate(Date arrivalDate) {this.arrivalDate = arrivalDate;}
    public Time getDepartureTime() {return departureTime;}
    public void setDepartureTime(Time departureTime) {this.departureTime = departureTime;}
    public Time getArrivalTime() {return arrivalTime;}
    public void setArrivalTime(Time arrivalTime) {this.arrivalTime = arrivalTime;}
    public int getCarriageCount() {return carriageCount;}
    public void setCarriageCount(int carriageCount) {this.carriageCount = carriageCount;}
    public String getCarriageTypes() {return carriageTypes;}
    public void setCarriageTypes(String carriageTypes) {this.carriageTypes = carriageTypes;}
}
