package com.example.weesofirebase;

public class UserModel {
    private String bandImage,
    bandName,date,location,room,id;
    private Chair[]chairs = new Chair[30];

    public UserModel(String bandImage, String bandName, String date, String location, String room, String id) {
        this.bandImage = bandImage;
        this.bandName = bandName;
        this.date = date;
        this.location = location;
        this.room = room;
        this.id = id;
    }

    public String getBandImage() {
        return bandImage;
    }

    public void setBandImage(String bandImage) {
        this.bandImage = bandImage;
    }

    public UserModel() {
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public void setChairs(String s,int i){
        chairs[i].setChairState(0);
        chairs[i].setChairKey(s);
    }*/

}
