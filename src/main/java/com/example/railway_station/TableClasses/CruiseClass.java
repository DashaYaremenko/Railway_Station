package com.example.railway_station.TableClasses;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CruiseClass {
    private String ID;
    private Date DateDepar;
    private Date DateArriv;
    private Time TimeDepar;
    private Time TimeArriv;
    public CruiseClass(String idCruise,Date dateDepar,Date dateArriv,Time timeDepar,Time timeArriv) {
        this.ID = idCruise;
        this.DateDepar=dateDepar;
        this.DateArriv=dateArriv;
        this.TimeDepar=TimeDepar;
        this.TimeArriv=TimeArriv;
    }
    public String getID() {return ID;}
    public void setID(String id) {this.ID = id;}
    public Date getDateDepar() {return DateDepar;}
    public void setDateDepar(Date dateTimeDispatch) {this.DateDepar = dateTimeDispatch;}
    public Date getDateArriv() {return DateArriv;}
    public void setDateArriv(Date dateTimeArrival) {this.DateArriv = dateTimeArrival;}
    public Time getTimeDepar() {return TimeDepar;}
    public void setTimeDepar(Time timeDepar) {this.TimeDepar = timeDepar;}
    public Time getTimeArriv() {return TimeArriv;}
    public void setTimeArriv(Time timeArriv) {this.TimeArriv = timeArriv;}
}
