package com.example.railway_station;

public class TrainClass {
    private String ID;
    private String NameM;
    private String TypeTrain;
    private int ColCruise;
    private String TypeCruise;

    public TrainClass(String id, String nameM, String typeTrain, String typeCruise) {
        this.ID = id;
        this.NameM = nameM;
        this.TypeTrain = typeTrain;
        this.TypeCruise = typeCruise;

    }
    public String getId() {return ID;}
    public void setId(String id) {
        this.ID = id;
    }
    public String getNameM() {
        return NameM;
    }
    public void setId(String id) {
        this.ID = id;
    }
    public void setTypeTrain(String typeTrain) {
        this.TypeTrain = typeTrain;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }
    public String getTypeDoc() {
        return TypeDoc;
    }
    public void setTypeDoc(String typeDoc) {
        this.TypeDoc = typeDoc;
    }
}
