package com.example.aula03_12_24;

public class RocketModel {
    int rocketImage;
    String rocketName;
    String launchDate;
    boolean launchSuccess;
    String payload;

    public RocketModel(int rocketImage, String rocketName, String launchDate, boolean launchSuccess, String payload){
        this.rocketImage = rocketImage;
        this.rocketName = rocketName;
        this.launchDate = launchDate;
        this.launchSuccess = launchSuccess;
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

    public String getLaunchDate(){
        return launchDate;
    }

    public void setLaunchDate(String launchDate){
        this.launchDate = launchDate;
    }

    public boolean isLaunchSuccess(){
        return launchSuccess;
    }

    public void setLaunchSuccess(boolean launchSuccess){
        this.launchSuccess = launchSuccess;
    }

    public String getPayload(){
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }


}
