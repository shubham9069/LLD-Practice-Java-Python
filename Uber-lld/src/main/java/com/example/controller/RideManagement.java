package com.example.controller;
import java.util.ArrayList;

import com.example.model.Driver;
import com.example.model.Location;
import com.example.model.Ride;
import com.example.model.User;
public class RideManagement {
    public ArrayList<Ride> rides;

    public RideManagement(){
        this.rides = new ArrayList<>();
    }
    public Ride addRide( User user, Driver driver, Location pickupLocation, Location dropLocation, double price){
        Ride ride = new Ride(user, driver, pickupLocation, dropLocation, price);
        this.rides.add(ride);
        return ride;
    }
    public Ride getRide(String id){
        for(Ride ride : this.rides){
            if(ride.getId().equals(id)){
                System.out.println("Ride found"+ ride.dropLocation.address);
                return ride;
            }
        }
        System.out.println("Ride not found");
        return null;
    }

   
}
