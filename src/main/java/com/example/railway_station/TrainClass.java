package com.example.railway_station;

public class TrainClass {
    private String ID;
    private String NameM;
    private String TypeTrain;
    private int ColCruise;
    private String TypeCruise;
    private double TimeTrain;

    public TrainClass(String id, String nameM, String typeTrain, String typeCruise, int colCruise, double timeTrain) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.TypeCruise = typeCruise;
        this.ColCruise = colCruise;
        this.TimeTrain = timeTrain;
    }


    public String getId() {return ID;}
    public void setId(String id) {
        this.ID = id;
    }
    public String getNameM() {
        return NameM;
    }
    public void setNameM(String nameM) {this.NameM = nameM;}
    public String getTypeTrain() {return TypeTrain;}
    public void setTypeTrain(String typeTrain) {
        this.TypeTrain = typeTrain;
    }
    public int getColCruise() {
        return ColCruise;
    }
    public void setColCruise(int colCruise) {
        this.ColCruise = colCruise;
    }
    public String getTypeCruise() {
        return TypeCruise;
    }
    public void setTypeCruise(String typeCruise) {this.TypeCruise = typeCruise;}
    public double getTimeTrain() {return TimeTrain;}
    public void setTimeTrain(double timeTrain) {this.TimeTrain = timeTrain;}
}
