
from abstractClass.parkingLot import ParkingLot
from slot import Slot
from vehicalType import Truck, Bike, Car

class ParkingLot(ParkingLot, Slot):
    def __init__(self):
        self.parking_lot = []
        self.vehical_instance = {"TRUCK": Truck, "BIKE": Bike, "CAR": Car}
        
    def create_parking(self,total_floor: int):
        for i in range(total_floor):
            self.parking_lot.append([])
            
    def create_slots(self, floor_number: int, total_slots: int):
        for i in range(total_slots):
            slot = Slot()
            self.parking_lot[floor_number].append(slot)
        
    def pre_assign_slots(self, floor_number: int):
        total_slots = len(self.parking_lot[floor_number])
        for i in range(total_slots):
                if i == 0:
                    self.parking_lot[floor_number][i].create_slot("TRUCK")
                elif i == 1 or i == 2:
                    self.parking_lot[floor_number][i].create_slot("BIKE")
                else:
                    self.parking_lot[floor_number][i].create_slot("CAR")
                    
                    
    def park_vehicle(self, vehicle_type: str, reg_no: str, color: str):
        
        for floor in range(len(self.parking_lot)):
            for idx in range(len(self.parking_lot[floor])):
                slot = self.parking_lot[floor][idx]
                if slot.is_empty() and vehicle_type == slot.slots["slot_type"]:
                    vehical = self.vehical_instance[vehicle_type](reg_no, color) # create vehical instance
                    slot.assign_slot(vehical) # assign vehical to slot
                    return floor, idx
        return None, None
    
    def unpark_vehicle(self, floor_number: int, slot_number: int):
        if floor_number >= len(self.parking_lot) or slot_number >= len(self.parking_lot[floor_number]):
            print("Invalid Ticket")
            return
        slot = self.parking_lot[floor_number][slot_number]
        if slot.is_empty():
            print("Invalid Ticket")
            return
        vehicle_info = slot.get_vehicle_info()
        slot.unassign_slot()
        print(f"Unparked vehicle with Registration Number: {vehicle_info['reg_no']} and Color: {vehicle_info['color']}")
        
    def get_free_count(self, vehicle_type: str):
        count = 0
        for floor in range(len(self.parking_lot)):
            per_floor_count = 0
            for slot in range(len(self.parking_lot[floor])):
                
                if self.parking_lot[floor][slot].is_empty():
                    if  vehicle_type and vehicle_type == self.parking_lot[floor][slot].slots["slot_type"]:
                        per_floor_count += 1   
                    if not vehicle_type :
                        per_floor_count += 1
                        
            count += per_floor_count # add per floor count to total count
            print(f"No. of free slots for {vehicle_type} on Floor {floor+1}: {per_floor_count}")
        return count
    
    def get_free_slots(self, vehicle_type: str):
        slots = []
        for floor in range(len(self.parking_lot)):
            per_floor_slots = []
            for slot in range(len(self.parking_lot[floor])):
                
                if self.parking_lot[floor][slot].is_empty():    
                    if  vehicle_type and vehicle_type == self.parking_lot[floor][slot].slots["slot_type"] :
                        per_floor_slots.append(slot+1)
                    if not vehicle_type :
                        per_floor_slots.append(slot+1)
                        
            slots += per_floor_slots # add per floor slots to total slots
            print(f"Free slots for {vehicle_type} on Floor {floor+1}: {per_floor_slots}")
        return slots
    
    def get_occupied_slots(self, vehicle_type: str):
        slots = []
        for floor in range(len(self.parking_lot)):
            per_floor_slots = []
            for slot in range(len(self.parking_lot[floor])):
                
                if not self.parking_lot[floor][slot].is_empty():
                    if vehicle_type and vehicle_type == self.parking_lot[floor][slot].slots["slot_type"] :
                        per_floor_slots.append(slot+1)
                    if not vehicle_type :
                        per_floor_slots.append(slot+1)
                        
            slots += per_floor_slots # add per floor slots to total slots
            print(f"Occupied slots for {vehicle_type} on Floor {floor+1}: {per_floor_slots}")
        return slots
    
    def get_parking_lot(self):
        return self.parking_lot
    
    def get_slots(self, floor_number: int):
        return self.parking_lot[floor_number]
    
    def get_floor(self, floor_number: int):
        return self.parking_lot[floor_number]
            
    
        
        
        