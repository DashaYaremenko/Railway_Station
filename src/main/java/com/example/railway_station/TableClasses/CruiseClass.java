package com.example.railway_station.TableClasses;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CruiseClass {
    private String ID;
    private Date DateDepar;
    private Date DateArriv;
    private String TimeDepar;
    private String TimeArriv;
    public CruiseClass(String idCruise,Date dateDepar,String timeDepar,Date dateArriv,String timeArriv) {
        this.ID = idCruise;
        this.DateDepar=dateDepar;
        this.DateArriv=dateArriv;
        this.TimeDepar=timeDepar;
        this.TimeArriv=timeArriv;
    }
    public String getID() {return ID;}
    public void setID(String id) {this.ID = id;}
    public Date getDateDepar() {return DateDepar;}
    public void setDateDepar(Date dateTimeDispatch) {this.DateDepar = dateTimeDispatch;}
    public Date getDateArriv() {return DateArriv;}
    public void setDateArriv(Date dateTimeArrival) {this.DateArriv = dateTimeArrival;}
    public String getTimeDepar() {return TimeDepar;}
    public void setTimeDepar(String timeDepar) {this.TimeDepar = timeDepar;}
    public String getTimeArriv() {return TimeArriv;}
    public void setTimeArriv(String timeArriv) {this.TimeArriv = timeArriv;}
}
