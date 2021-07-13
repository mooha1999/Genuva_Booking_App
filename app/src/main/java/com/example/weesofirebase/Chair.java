package com.example.weesofirebase;

public class Chair {
    private int chairImage,chairState,chairPrice,chairPosition;
    private String chairKey;

    public Chair(String chairKey, int chairPosition) {
        chairImage = R.drawable.ic_chair;
        this.chairKey = chairKey;
        chairState = 0;
        this.chairPosition = chairPosition;
        if (chairPosition>=20){
            chairPrice=60;
        }
        else if (chairPosition>=10){
            chairPrice=80;
        }
        else chairPrice = 100;
    }
    public Chair() {}

    public int getChairState() {
        return chairState;
    }

    public void setChairState(int chairState) {
        this.chairState = chairState;
    }

    public String getChairKey() {
        return chairKey;
    }

    public void setChairKey(String chairKey) {
        this.chairKey = chairKey;
    }

    public int getChairPrice() { return chairPrice; }

    public void setChairPrice(int chairPrice) { this.chairPrice = chairPrice; }

    public int getChairImage() {
        return chairImage;
    }

    public void setChairImage(int chairImage) {
        this.chairImage = chairImage;
    }

    public int getChairPosition() {
        return chairPosition;
    }

    public void setChairPosition(int chairPosition) {
        this.chairPosition = chairPosition;
    }
}
