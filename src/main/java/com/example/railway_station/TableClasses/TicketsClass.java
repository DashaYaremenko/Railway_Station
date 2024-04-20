package com.example.railway_station.TableClasses;

public class TicketsClass {
    private int IDTicket;
    private int NumTrain;
    private int StationID_1;
    private int StationID_2;
    private int Cruise_ID;
    private int Client_Id;
    private int NumCarrig;
    private double CostTicket;
    private String Service;
    public TicketsClass(int idTicket,int numTrain, int stationID_1, int stationID_2,int client_Id ,int cruise_ID, int numCarrig, double costTicket, String service ) {
        this.IDTicket = idTicket;
        this.NumTrain = numTrain;
        this.StationID_1 = stationID_1;
        this.StationID_2 = stationID_2;
        this.Cruise_ID = cruise_ID;
        this.NumCarrig = numCarrig;
        this.CostTicket = costTicket;
        this.Service = service;
        this.Client_Id=client_Id;
    }
    public int getIDTicket() {return IDTicket;}
    public void setIDTicket(int idTicket) {this.IDTicket = idTicket;}
    public int getNumTrain() {return NumTrain;}
    public void setNumTrain(int numTrain) {this.NumTrain = numTrain;}
    public int getStationID_1() {return StationID_1;}
    public void setStationID_1(int stationID_1) {this.StationID_1 = stationID_1;}
    public int getStationID_2() {return StationID_2;}
    public void setStationID_2(int stationID_2) {this.StationID_2 = stationID_2;}
    public int getCruise_ID() {return Cruise_ID;}
    public void setCruise_ID(int cruise_ID) {Cruise_ID = cruise_ID;}
    public int getClient_Id() {return Client_Id;}
    public void setClient_Id(int client_Id) {Client_Id = client_Id;}
    public int getNumCarrig() {return NumCarrig;}
    public void setNumCarrig(int numCarrig) {NumCarrig = numCarrig;}
    public double getCostTicket() {return CostTicket;}
    public void setCostTicket(double costTicket) {CostTicket = costTicket;}
    public String getService() {return Service;}
    public void setService(String service) {this.Service = service;}
}
