

from parkingLot import ParkingLot

class ParkingManager():
    def __init__(self):
        self.__parking_lot = {}
        
    def create_parking_lot(self, parking_lot_id: str, total_floor: int, total_slots: int):
        if parking_lot_id in self.__parking_lot:
            raise Exception("Parking lot already exists")
        
        parking_floor = ParkingLot()
        parking_floor.create_parking(total_floor) # create parking floor
        
        for i in range(total_floor):
            parking_floor.create_slots(i, total_slots) # create slots for each floor
            parking_floor.pre_assign_slots(i) # pre-assign slots type
        

        self.__parking_lot[parking_lot_id] = parking_floor
        print(f"Created parking lot with {total_floor} floors and {total_slots} slots per floor")
        
    def park_vehicle(self, parking_lot_id: str, vehicle_type: str, reg_no: str, color: str):
        parking_lot = self.__parking_lot[parking_lot_id]
        floor, slot = parking_lot.park_vehicle(vehicle_type, reg_no, color)
        
        if floor is None or slot is None:
            print("Parking Lot Full")
            return None
        
        print(f"Parked vehicle. Ticket ID: {parking_lot_id}_{floor+1}_{slot+1}")
        return parking_lot_id+"_"+str(floor+1)+"_"+str(slot+1)
    
    def unpark_vehicle(self, ticket: str):
        parking_lot_id, floor, slot = ticket.split("_")
        parking_lot = self.__parking_lot[parking_lot_id]
        parking_lot.unpark_vehicle(int(floor)-1, int(slot)-1)
        
    def get_slots (self,parking_lot_id: str, display_type: str, vehicle_type: str):
        parking_lot = self.__parking_lot[parking_lot_id]
        if display_type == "free_count":
            return parking_lot.get_free_count(vehicle_type)
        elif display_type == "free_slots":
            return parking_lot.get_free_slots(vehicle_type)
        elif display_type == "occupied_slots":
            return parking_lot.get_occupied_slots(vehicle_type)
    
    def get_parking_lot(self):
        return self.__parking_lot
        
    
        
    
    
    