package com.example.railway_station.TableClasses;

import java.time.LocalDateTime;

public class CruiseClass {
    private String ID;
    private LocalDateTime DateTimeDispatch;
    private LocalDateTime DateTimeArrival;
    private CruiseClass (String id, LocalDateTime dateTimeDispatch, LocalDateTime dateTimeArrival) {
        this.ID = id;
        this.DateTimeDispatch = dateTimeDispatch;
        this.DateTimeArrival = dateTimeArrival;
    }
    public String getID() {return ID;}
    public void setID(String id) {this.ID = id;}
    public LocalDateTime getDateTimeDispatch() {return DateTimeDispatch;}
    public void setDateTimeDispatch(LocalDateTime dateTimeDispatch) {this.DateTimeDispatch = dateTimeDispatch;}
    public LocalDateTime getDateTimeArrival() {return DateTimeArrival;}
    public void setDateTimeArrival(LocalDateTime dateTimeArrival) {this.DateTimeArrival = dateTimeArrival;}

}
