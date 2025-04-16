package com.example.model;

public class Location {
    public double latitude;
    public double longitude;
    public String address;

    public Location(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public Location getLocationData(){
        return this;
    }

    
}
