package com.example.controller;
import java.util.ArrayList;

import com.example.constant.RideStatus;
import com.example.constant.Status;
import com.example.model.Driver;
import com.example.model.Location;
import com.example.model.Ride;
import com.example.model.User;

public class DriverManagement {
    public ArrayList<Driver> drivers;
    public RideManagement rideManagement;

    public DriverManagement(RideManagement rideManagement){
        this.drivers = new ArrayList<>();
        this.rideManagement = rideManagement;
    }

    public String addDriver(String name, String email){
        Driver driver = new Driver(name, email);
        this.drivers.add(driver);
        System.out.println("Driver added successfully"+ driver.getId() + " and status is " + driver.getStatus());
        return driver.getId();
    }
    public void driverOnline(String driverId, Location location){
        for(Driver driver : this.drivers){
            if(driver.getId().equals(driverId)){
                driver.ready(Status.AVAILABLE, location);
                System.out.println("Driver is online"+ driver.getId() + " and status is " + driver.getStatus() + " and location is " + driver.getLocation());
                return ;
            }
        }
        System.out.println("Driver not found");
    }

    public Driver getDriver(String id){
        for(Driver driver : this.drivers){
            if(driver.getId().equals(id)){
                System.out.println("Driver found"+ driver.getId());
                return driver;
            }
        }
        System.out.println("Driver not found");
        return null;
    }

    public String[] findDriver(User user, Location currentLocation, Location dropLocation) {
        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;
        for (Driver driver : this.drivers) {
            if (driver.getStatus().equals(Status.AVAILABLE)) {
                // Immediately mark driver as NOT_AVAILABLE while checking
                driver.setStatus(Status.NOT_AVAILABLE);
                Location driverLocation = driver.getLocation();
                
                double distance = Math.abs(driverLocation.latitude - currentLocation.latitude) 
                              + Math.abs(driverLocation.longitude - currentLocation.longitude);
                
                if (distance < 10 && distance < minDistance) {
                    minDistance = distance;
                    nearestDriver = driver;
                    driver.setStatus(Status.NOT_AVAILABLE);
                } else {
                    // Reset status if not chosen
                    driver.setStatus(Status.AVAILABLE);
                }
            }
        }
        // Create ride only if nearest driver found
        if(nearestDriver != null) {
            nearestDriver.setStatus(Status.ON_THE_WAY);
            Ride ride = this.rideManagement.addRide(user, nearestDriver, currentLocation, dropLocation, minDistance * 10);
            return new String[]{ride.getId(), nearestDriver.getId()};
        }
        return null;
    }

    public void acceptRide(String driverId, String rideId){
    for(Ride ride : this.rideManagement.rides){
        if(ride.getId().equals(rideId) && ride.driver.getId().equals(driverId)){
            ride.setRideStatus(RideStatus.ON_THE_WAY);
            Driver driver = this.getDriver(driverId);
            driver.setStatus(Status.ON_TRIP);
            System.out.println("Driver accepted the ride"+ driver.getId());
            return;
        }
    }
    System.out.println("Ride not found");
    }

}
