package com.example.model;
import java.util.UUID;

import com.example.constant.RideStatus;
public class Ride {
    public final String id;
    public final User user;
    public final Driver driver;
    public final Location pickupLocation;
    public final Location dropLocation;
    public final double price;
    public RideStatus status;


    public Ride(User user, Driver driver, Location pickupLocation, Location dropLocation, double price) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.price = price;
        this.status = RideStatus.YET_TO_START;
    }

    public String rideDetails(){
        return "Ride id is "+ this.id + " and the status is "+ this.status + " and the price is "+ this.price+ " and the pickup location is "+ this.pickupLocation.address + " and the drop location is "+ this.dropLocation.address;
    }
    public String getId(){
        return this.id;
    }
    public void setRideStatus(RideStatus status){
        this.status = status;
    }
}
