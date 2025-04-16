package com.example.controller;
import java.util.ArrayList;

import com.example.constant.RideStatus;
import com.example.constant.Status;
import com.example.model.Driver;
import com.example.model.Location;
import com.example.model.Ride;
import com.example.model.User;
public class UserManagement {
    public ArrayList<User> users;
    public RideManagement rideManagement;
    public DriverManagement driverManagement;

    public UserManagement(RideManagement rideManagement, DriverManagement driverManagement){
        this.users = new ArrayList<>();
        this.rideManagement = rideManagement;
        this.driverManagement = driverManagement;
    }

    public String addUser(String name, String email){
        User user = new User(name, email);
        this.users.add(user);
        System.out.println("User added successfully"+ user.getId());
        return user.getId();
    }

    public User getUser(String id){
        for(User user : this.users){
            if(user.getId().equals(id)){
                System.out.println("User found"+ user.getId());
                return user;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public String[] findRide(String userId, Location currentLocation, Location dropLocation){
        User user = this.getUser(userId);
        String[] driverRide = this.driverManagement.findDriver(user, currentLocation, dropLocation);
        
        if(driverRide == null){
            System.out.println("No ride found");
            return null;
        }
        System.out.println("Ride found"+ driverRide[0]);
        return driverRide;
    }

    public Ride payRide(String userId, String rideId){
        for(Ride ride : this.rideManagement.rides){
            if(ride.getId().equals(rideId) && ride.user.getId().equals(userId)){
                ride.setRideStatus(RideStatus.COMPLETED);
                User user = this.getUser(userId);
                Driver driver = this.driverManagement.getDriver(ride.driver.getId());
                driver.setStatus(Status.AVAILABLE);
                System.out.println("Ride completed"+ ride.getId());
            
                return ride;
            }
        }
        System.out.println("Ride not found");
        return null;
    }
    
}
