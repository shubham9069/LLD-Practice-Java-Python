package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.constant.RideStatus;
import com.example.controller.DriverManagement;
import com.example.controller.RideManagement;
import com.example.controller.UserManagement;
import com.example.model.Location;
import com.example.model.Ride;

public class UberBookingTest {
    private RideManagement rideManagement;
    private DriverManagement driverManagement;
    private UserManagement userManagement;

    @BeforeEach
    void setUp() {
        rideManagement = new RideManagement();
        driverManagement = new DriverManagement(rideManagement);
        userManagement = new UserManagement(rideManagement, driverManagement);
    }

    @Test
    void testBasicBookingFlow() {
        // Setup
        String userId = userManagement.addUser("Test User", "test@email.com");
        String driverId = driverManagement.addDriver("Test Driver", "driver@email.com");
        Location driverLocation = new Location(1.0, 1.0, "Driver Location");
        driverManagement.driverOnline(driverId, driverLocation);

        // Test booking
        Location pickup = new Location(1.0, 1.0, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        String[] booking = userManagement.findRide(userId, pickup, dropoff);
        
        assertNotNull(booking, "Booking should be successful");
        assertEquals(2, booking.length, "Booking should return ride and driver IDs");
    }

    @Test
    void testConcurrentBookings() {
        // Setup
        String user1Id = userManagement.addUser("User1", "user1@email.com");
        String user2Id = userManagement.addUser("User2", "user2@email.com");
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        
        Location driverLocation = new Location(1.0, 1.0, "Driver Location");
        driverManagement.driverOnline(driverId, driverLocation);

        Location pickup = new Location(1.0, 1.0, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        
        String[] booking1 = userManagement.findRide(user1Id, pickup, dropoff);
        String[] booking2 = userManagement.findRide(user2Id, pickup, dropoff);
        
        assertTrue(booking1 == null || booking2 == null, 
                  "Only one booking should succeed for same driver");
    }

    @Test
    void testDriverSelection() {
        // Setup
        String userId = userManagement.addUser("User", "user@email.com");
        String driver1Id = driverManagement.addDriver("Driver1", "d1@email.com");
        String driver2Id = driverManagement.addDriver("Driver2", "d2@email.com");
        
        Location driver1Location = new Location(1.0, 1.0, "Location1");
        Location driver2Location = new Location(5.0, 5.0, "Location2");
        
        driverManagement.driverOnline(driver1Id, driver1Location);
        driverManagement.driverOnline(driver2Id, driver2Location);

        Location pickup = new Location(1.5, 1.5, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        
        String[] booking = userManagement.findRide(userId, pickup, dropoff);
        
        assertNotNull(booking, "Booking should be successful");
        assertEquals(driver1Id, booking[1], "Closest driver should be selected");
    }

    @Test
    void testRideStatusFlow() {
        // Setup
        String userId = userManagement.addUser("User", "user@email.com");
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        driverManagement.driverOnline(driverId, new Location(1.0, 1.0, "Driver"));
        
        // Book ride
        String[] booking = userManagement.findRide(userId,
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        
        assertNotNull(booking, "Booking should be successful");
        
        // Check initial status
        Ride ride = rideManagement.getRide(booking[0]);
        assertEquals(RideStatus.YET_TO_START, ride.status, "Initial ride status should be YET_TO_START");
        
        // Accept ride
        driverManagement.acceptRide(driverId, booking[0]);
        assertEquals(RideStatus.ON_THE_WAY, ride.status, "Ride status should be ON_THE_WAY after acceptance");
        
        // Complete ride
        Ride completedRide = userManagement.payRide(userId, booking[0]);
        assertEquals(RideStatus.COMPLETED, completedRide.status, "Ride status should be COMPLETED after payment");
    }
} 