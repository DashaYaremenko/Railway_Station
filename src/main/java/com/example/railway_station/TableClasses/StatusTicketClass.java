package com.example.railway_station.TableClasses;

public class StatusTicketClass {
    private int IDTicket;
    private int IDTrain;
    private double CostTicket;
    private String ClientName;
    private String Cruise1;
    private String Cruise2;
    private String Station1;
    private String Station2;
    private String CarriageInfo;
    private boolean Linens;
    private boolean Drink;
    private boolean Snacks;

    public StatusTicketClass(int idTicket, String clientName, int idTrain, String station1, String cruise1, String station2, String cruise2, String carriageInfo, double costTicket, boolean linens, boolean drink, boolean snacks) {
        this.IDTicket = idTicket;
        this.IDTrain = idTrain;
        this.Station1 = station1;
        this.Station2 = station2;
        this.Cruise1 = cruise1;
        this.Cruise2 = cruise2;
        this.CarriageInfo = carriageInfo;
        this.CostTicket = costTicket;
        this.ClientName = clientName;
        this.Linens = linens;
        this.Drink = drink;
        this.Snacks = snacks;
    }

    public int getIDTicket() {
        return IDTicket;
    }

    public void setIDTicket(int IDTicket) {
        this.IDTicket = IDTicket;
    }

    public int getIDTrain() {
        return IDTrain;
    }

    public void setIDTrain(int IDTrain) {
        this.IDTrain = IDTrain;
    }

    public double getCostTicket() {
        return CostTicket;
    }

    public void setCostTicket(double costTicket) {
        this.CostTicket = costTicket;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        this.ClientName = clientName;
    }

    public String getCruise1() {
        return Cruise1;
    }

    public void setCruise1(String cruise1) {
        this.Cruise1 = cruise1;
    }

    public String getCruise2() {
        return Cruise2;
    }

    public void setCruise2(String cruise2) {
        this.Cruise2 = cruise2;
    }

    public String getStation1() {
        return Station1;
    }

    public void setStation1(String station1) {
        this.Station1 = station1;
    }

    public String getStation2() {
        return Station2;
    }

    public void setStation2(String station2) {
        this.Station2 = station2;
    }

    public String getCarriageInfo() {
        return CarriageInfo;
    }

    public void setCarriageInfo(String carriageInfo) {
        this.CarriageInfo = carriageInfo;
    }

    public boolean isLinens() {
        return Linens;
    }

    public void setLinens(boolean linens) {
        this.Linens = linens;
    }

    public boolean isDrink() {
        return Drink;
    }

    public void setDrink(boolean drink) {
        this.Drink = drink;
    }

    public boolean isSnacks() {
        return Snacks;
    }

    public void setSnacks(boolean snacks) {
        this.Snacks = snacks;
    }
}

