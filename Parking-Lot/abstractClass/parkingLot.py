from abc import ABC, abstractmethod

class ParkingLot(ABC):
    @abstractmethod
    def create_parking(self,total_floor: int):
        pass
    @abstractmethod
    def park_vehicle(self, vehicle_type: str, reg_no: str, color: str):
        pass
    
    @abstractmethod
    def unpark_vehicle(self, floor_number: int, slot_number: int):
        pass

    
    


