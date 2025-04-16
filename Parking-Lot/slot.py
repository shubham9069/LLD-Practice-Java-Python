
from abstractClass.slot import Slot
from vehicalType import Truck, Bike, Car
class Slot(Slot):
    def __init__(self):
        self.slots = {"slot_type": "", "vehicle": None}
        
    def create_slot(self, slot_type: str):
        self.slots["slot_type"] = slot_type
        
    def assign_slot(self, vehicle: Truck | Bike | Car):
        self.slots["vehicle"] = vehicle
        
    def get_slot(self):
        return self.slots
    
    def is_empty(self):
        return self.slots["vehicle"] is None
    
    def unassign_slot(self):
        self.slots["vehicle"] = None
    
    def get_vehicle_info(self):
        vehicle_info = self.slots["vehicle"].get_vehicle_info()
        return vehicle_info
        
        
        