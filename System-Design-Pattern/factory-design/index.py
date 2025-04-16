# In factory design pattern, we create an object without specifying the exact class of object that will be created.
# its not pre-defined which object will be create its descide on runtime based on some condition.

from abc import ABC, abstractmethod

class Vehicle(ABC):
    @abstractmethod
    def drive(self):
        pass

class TwoWheeler(Vehicle):
    
    def drive(self):
        print("Driving a two wheeler")
        
class FourWheeler(Vehicle):
    def drive(self):
        print("Driving a four wheeler")
        
class VehicleFactory:
    def get_vehicle(self, vehicle_type):
        if vehicle_type == 1:
            return TwoWheeler()
        elif vehicle_type == 2:
            return FourWheeler()
        else:
            raise ValueError(f"Invalid vehicle type: {vehicle_type}")
        
        
        
def main():
    factory = VehicleFactory()
    vehicle = factory.get_vehicle(2)
    vehicle.drive()
    
if __name__ == "__main__":
    main()
        
        
        
        
        