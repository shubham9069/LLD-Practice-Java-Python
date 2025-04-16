from parkingManager import ParkingManager


parking_manager = ParkingManager()

# Create parking lot
parking_manager.create_parking_lot("PR1234", 2, 6)

# Display free count
parking_manager.get_slots("PR1234", "free_count", "CAR")
parking_manager.get_slots("PR1234", "free_count", "BIKE")
parking_manager.get_slots("PR1234", "free_count", "TRUCK")

# Display free slots
parking_manager.get_slots("PR1234", "free_slots", "CAR")
parking_manager.get_slots("PR1234", "free_slots", "BIKE")
parking_manager.get_slots("PR1234", "free_slots", "TRUCK")

# Display occupied slots
parking_manager.get_slots("PR1234", "occupied_slots", "CAR")
parking_manager.get_slots("PR1234", "occupied_slots", "BIKE")
parking_manager.get_slots("PR1234", "occupied_slots", "TRUCK")

# Park vehicles
parking_manager.park_vehicle("PR1234", "CAR", "KA-01-DB-1234", "black")
parking_manager.park_vehicle("PR1234", "CAR", "KA-02-CB-1334", "red")
parking_manager.park_vehicle("PR1234", "CAR", "KA-01-DB-1133", "black")
parking_manager.park_vehicle("PR1234", "CAR", "KA-05-HJ-8432", "white")
parking_manager.park_vehicle("PR1234", "CAR", "WB-45-HO-9032", "white")
parking_manager.park_vehicle("PR1234", "CAR", "KA-01-DF-8230", "black")
parking_manager.park_vehicle("PR1234", "CAR", "KA-21-HS-2347", "red")

# Display free count again
parking_manager.get_slots("PR1234", "free_count", "CAR")
parking_manager.get_slots("PR1234", "free_count", "BIKE")
parking_manager.get_slots("PR1234", "free_count", "TRUCK")

# Unpark vehicles
parking_manager.unpark_vehicle("PR1234_2_5")
parking_manager.unpark_vehicle("PR1234_2_5")
parking_manager.unpark_vehicle("PR1234_2_7")

# Display free count again
parking_manager.get_slots("PR1234", "free_count", "CAR")
parking_manager.get_slots("PR1234", "free_count", "BIKE")
parking_manager.get_slots("PR1234", "free_count", "TRUCK")

# Display free slots again
parking_manager.get_slots("PR1234", "free_slots", "CAR")
parking_manager.get_slots("PR1234", "free_slots", "BIKE")
parking_manager.get_slots("PR1234", "free_slots", "TRUCK")

# Display occupied slots again
parking_manager.get_slots("PR1234", "occupied_slots", "CAR")
parking_manager.get_slots("PR1234", "occupied_slots", "BIKE")
parking_manager.get_slots("PR1234", "occupied_slots", "TRUCK")

# Park more vehicles
parking_manager.park_vehicle("PR1234", "BIKE", "KA-01-DB-1541", "black")
parking_manager.park_vehicle("PR1234", "TRUCK", "KA-32-SJ-5389", "orange")
parking_manager.park_vehicle("PR1234", "TRUCK", "KL-54-DN-4582", "green")
parking_manager.park_vehicle("PR1234", "TRUCK", "KL-12-HF-4542", "green")

# Final display of free and occupied slots
parking_manager.get_slots("PR1234", "free_count", "CAR")
parking_manager.get_slots("PR1234", "free_count", "BIKE")
parking_manager.get_slots("PR1234", "free_count", "TRUCK")
parking_manager.get_slots("PR1234", "free_slots", "CAR")
parking_manager.get_slots("PR1234", "free_slots", "BIKE")
parking_manager.get_slots("PR1234", "free_slots", "TRUCK")
parking_manager.get_slots("PR1234", "occupied_slots", "CAR")
parking_manager.get_slots("PR1234", "occupied_slots", "BIKE")
parking_manager.get_slots("PR1234", "occupied_slots", "TRUCK")






