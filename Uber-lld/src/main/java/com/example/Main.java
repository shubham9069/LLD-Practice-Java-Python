package com.example;
import com.example.constant.RideStatus;
import com.example.constant.Status;
import com.example.controller.DriverManagement;
import com.example.controller.RideManagement;
import com.example.controller.UserManagement;
import com.example.model.Driver;
import com.example.model.Location;
import com.example.model.Ride;

public class Main {
    private static UserManagement userManagement;
    private static DriverManagement driverManagement;
    private static RideManagement rideManagement;

    public static void main(String[] args) {
        System.out.println("starting the application");
         rideManagement = new RideManagement();
         driverManagement = new DriverManagement(rideManagement);
         userManagement = new UserManagement(rideManagement, driverManagement);
        //  testBasicBookingFlow();
        //  testConcurrentBookings();
        //  testDriverSelection();
        //  testEdgeCases();
         testRideStatusFlow();
        //  testPaymentFlow();
        //  testDriverStatusTransitions();
        //  testDriverStatusTransitions();

     


        

    }

    public static void testBasicBookingFlow() {
        // Setup
        String userId = userManagement.addUser("Test User", "test@email.com");
        String driverId = driverManagement.addDriver("Test Driver", "driver@email.com");
        Location driverLocation = new Location(1.0, 1.0, "Driver Location");
        driverManagement.driverOnline(driverId, driverLocation);

        // Test normal booking
        Location pickup = new Location(1.0, 1.0, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        String[] booking = userManagement.findRide(userId, pickup, dropoff);
        
        // Verify booking success
        assert booking != null && booking.length == 2;
    }

    public static void testConcurrentBookings() {
        // Setup multiple users and one driver
        String user1Id = userManagement.addUser("User1", "user1@email.com");
        String user2Id = userManagement.addUser("User2", "user2@email.com");
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        
        Location driverLocation = new Location(1.0, 1.0, "Driver Location");
        driverManagement.driverOnline(driverId, driverLocation);

        // Both users try to book same driver
        Location pickup = new Location(1.0, 1.0, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        
        String[] booking1 = userManagement.findRide(user1Id, pickup, dropoff);
        String[] booking2 = userManagement.findRide(user2Id, pickup, dropoff);
        
        // Verify only one booking succeeds
        assert (booking1 == null || booking2 == null);
    }

    public static void testDriverSelection() {
        // Setup multiple drivers at different distances
        String userId = userManagement.addUser("User", "user@email.com");
        String driver1Id = driverManagement.addDriver("Driver1", "d1@email.com");
        String driver2Id = driverManagement.addDriver("Driver2", "d2@email.com");
        
        Location driver1Location = new Location(1.0, 1.0, "Location1");
        Location driver2Location = new Location(5.0, 5.0, "Location2");
        
        driverManagement.driverOnline(driver1Id, driver1Location);
        driverManagement.driverOnline(driver2Id, driver2Location);

        // Request ride from location closer to driver1
        Location pickup = new Location(1.5, 1.5, "Pickup");
        Location dropoff = new Location(2.0, 2.0, "Dropoff");
        
        String[] booking = userManagement.findRide(userId, pickup, dropoff);
        
        // Verify closest driver (driver1) was selected
        assert booking[1].equals(driver1Id);
    }

    public static void testEdgeCases() {
        // Test 1: Invalid User ID
        String[] booking1 = userManagement.findRide("invalid_id", 
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        assert booking1 == null;

        // Test 2: No Available Drivers
        String userId = userManagement.addUser("User", "user@email.com");
        String[] booking2 = userManagement.findRide(userId,
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        assert booking2 == null;

        // Test 3: Driver too far
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        driverManagement.driverOnline(driverId, new Location(100.0, 100.0, "Far"));
        String[] booking3 = userManagement.findRide(userId,
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        assert booking3 == null;
    }

    public static void testRideStatusFlow() {
        // Setup basic booking
        String userId = userManagement.addUser("User", "user@email.com");
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        driverManagement.driverOnline(driverId, new Location(1.0, 1.0, "Driver"));
        
        // Book ride
        String[] booking = userManagement.findRide(userId,
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        
        // Verify initial status
        Ride ride = rideManagement.getRide(booking[0]);
        assert ride.status == RideStatus.YET_TO_START;
        
        // Accept ride
        driverManagement.acceptRide(driverId, booking[0]);
        assert ride.status == RideStatus.ON_THE_WAY;
        
        // Complete ride
        Ride completedRide = userManagement.payRide(userId, booking[0]);
        assert completedRide.status == RideStatus.COMPLETED;
    }

    public static void testDriverStatusTransitions() {
        // Setup
        String driverId = driverManagement.addDriver("Driver", "driver@email.com");
        
        // Initial status
        Driver driver = driverManagement.getDriver(driverId);
        assert driver.getStatus() == Status.NOT_AVAILABLE;
        
        // Go online
        driverManagement.driverOnline(driverId, new Location(1.0, 1.0, "Location"));
        assert driver.getStatus() == Status.AVAILABLE;
        
        // Book ride
        String userId = userManagement.addUser("User", "user@email.com");
        String[] booking = userManagement.findRide(userId,
            new Location(1.0, 1.0, "Pickup"),
            new Location(2.0, 2.0, "Dropoff"));
        
        // Verify status changes
        assert driver.getStatus() == Status.ON_THE_WAY;
        
        driverManagement.acceptRide(driverId, booking[0]);
        assert driver.getStatus() == Status.ON_TRIP;
        
        userManagement.payRide(userId, booking[0]);
        assert driver.getStatus() == Status.AVAILABLE;
    }
} 