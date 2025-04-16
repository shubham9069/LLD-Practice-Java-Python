# in abstract design pattern, we create an object without specifying the exact class of object that will be created. its same as factory design pattern but it has more flexibility. we just added a extract layer for abstract class.

from abc import ABC, abstractmethod

class Vehicle(ABC):
    @abstractmethod
    def drive(self):
        pass

class Commercial(Vehicle):
    
    def get_vehicle_type(self):
        return "Commercial"
    

class Private(Vehicle):
    
    def get_vehicle_type(self):
        return "Private"
        
class TwoWheeler(Vehicle):
    def drive(self):
        print("Driving a two wheeler")
        
class FourWheeler(Vehicle):
    def drive(self):
        print("Driving a four wheeler")
        
class VehicleFactory:
    def get_vehicle(self, vehicle_type):
        if vehicle_type == "commercial":
            return Commercial()
        elif vehicle_type == "private":
            return Private()
        else:
            raise ValueError(f"Invalid vehicle type: {vehicle_type}")