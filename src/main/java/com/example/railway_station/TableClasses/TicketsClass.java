package com.example.railway_station.TableClasses;

public class TicketsClass {
    private int IDTicket;
    private int NumTrain;
    private int StationID_1;
    private int StationID_2;
    private int CruiseID1;
    private int CruiseID2;
    private int ClientId;
    private int NumCarrig;
    private double CostTicket;
    private boolean Linens;
    private boolean Drink;
    private boolean Snacks;
    public TicketsClass(int idTicket,,int clientId, int numTrain, int stationID_1, int stationID_2 ,int cruiseID1,int cruiseID2, int numCarrig, double costTicket, boolean linens, boolean drink, boolean snacks ) {
        this.IDTicket = idTicket;
        this.NumTrain = numTrain;
        this.StationID_1 = stationID_1;
        this.StationID_2 = stationID_2;
        this.CruiseID1 = cruiseID1;
        this.CruiseID2 = cruiseID2;
        this.NumCarrig = numCarrig;
        this.CostTicket = costTicket;
        this.ClientId=clientId;
        this.Linens=linens;
        this.Drink=drink;
        this.Snacks=snacks;
    }
    public int getIDTicket() {return IDTicket;}
    public void setIDTicket(int idTicket) {this.IDTicket = idTicket;}
    public int getNumTrain() {return NumTrain;}
    public void setNumTrain(int numTrain) {this.NumTrain = numTrain;}
    public int getStationID_1() {return StationID_1;}
    public void setStationID_1(int stationID_1) {this.StationID_1 = stationID_1;}
    public int getStationID_2() {return StationID_2;}
    public void setStationID_2(int stationID_2) {this.StationID_2 = stationID_2;}
    public int getCruiseID1() {return CruiseID1;}
    public void setCruiseID0(int cruiseID1) {CruiseID1 = cruiseID1;}
    public int getCruiseID2() {return CruiseID2;}
    public void setCruiseID2(int cruiseID2) {CruiseID2 = cruiseID2;}
    public int getClientId() {return ClientId;}
    public void setClient_Id(int clientId) {ClientId = clientId;}
    public int getNumCarrig() {return NumCarrig;}
    public void setNumCarrig(int numCarrig) {NumCarrig = numCarrig;}
    public double getCostTicket() {return CostTicket;}
    public void setCostTicket(double costTicket) {CostTicket = costTicket;}
    public boolean getLinens() {return Linens;}
    public void setLinens(boolean linens) {Linens = linens;}
    public boolean getDrink() {return Drink;}
    public void setDrink(boolean drink) {Drink = drink;}
    public boolean getSnacks() {return Snacks;}
    public void setSnacks(boolean snacks) {Snacks = snacks;}

}
