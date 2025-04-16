from abc import ABC, abstractmethod
from vehicalType import Truck, Bike, Car

class Slot(ABC):
    @abstractmethod
    def create_slot(self,slot_type: str):
        pass
    @abstractmethod
    def assign_slot(self, vehicle: Truck | Bike | Car):
        pass
    
    @abstractmethod
    def unassign_slot(self):
        pass
    
    @abstractmethod
    def get_slot(self, slot_id: str):
        pass
    
    @abstractmethod
    def is_empty(self):
        pass
    
    
    
    
