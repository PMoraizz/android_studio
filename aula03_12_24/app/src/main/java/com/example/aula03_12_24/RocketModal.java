package com.example.aula03_12_24;

import android.widget.ImageView;

public class RocketModal {
    int rocketImage;
    String rocketName;
    String lauchDate;
    boolean lauchSuccess;
    String payload;

    public RocketModal (int rocketImage, String rocketName, String lauchDate, boolean lauchSuccess, String payload){
        this.rocketImage = rocketImage;
        this.rocketName = rocketName;
        this.lauchDate = lauchDate;
        this.lauchSuccess = lauchSuccess;
        this.payload = payload;
    }

    public int getRocketImage(){
        return rocketImage;
    }

    public void setRocketImage(int rocketImage){
        this.rocketImage = rocketImage;
    }

    public String getRocketName(){
        return rocketName;
    }

    public void setRocketName(String rocketName){
        this.rocketName = rocketName;
    }

    public String getLauchDate(){
        return lauchDate;
    }

    public void setLauchDate(String lauchDate){
        this.lauchDate = lauchDate;
    }

    public boolean isLauchSuccess(){
        return lauchSuccess;
    }

    public void setLauchSuccess(boolean lauchSuccess){
        this.lauchSuccess = lauchSuccess;
    }

    public String getPayload(){
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }


}
