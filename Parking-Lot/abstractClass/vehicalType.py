from abc import ABC, abstractmethod

class VehicleType(ABC) :
    @abstractmethod
    def get_vehicle_info(self):
        pass
    
    