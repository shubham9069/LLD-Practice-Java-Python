
from abstractClass.vehicalType import VehicleType

class Truck(VehicleType):
    def __init__(self, reg_no: str, color: str):
        self.vehicle_type = "Truck"
        self.reg_no = reg_no
        self.color = color
        self.vehicle_size = "Large"
        
        
    def get_vehicle_info(self):
        return {"reg_no": self.reg_no, "color": self.color, "vehicle_size": self.vehicle_size}
    
class Bike(VehicleType):
    def __init__(self, reg_no: str, color: str):
        self.vehicle_type = "Bike"
        self.reg_no = reg_no
        self.color = color
        self.vehicle_size = "Small"
    
    def get_vehicle_info(self):
        return {"reg_no": self.reg_no, "color": self.color, "vehicle_size": self.vehicle_size}
    
class Car(VehicleType):
    def __init__(self, reg_no: str, color: str):
        self.vehicle_type = "Car"
        self.reg_no = reg_no
        self.color = color
        self.vehicle_size = "Medium"
        
    def get_vehicle_info(self):
        return {"reg_no": self.reg_no, "color": self.color, "vehicle_size": self.vehicle_size}
    
