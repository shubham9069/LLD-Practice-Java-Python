

# airline desk -- mediator -- flight   here mediator is the one who is responsible for sending the message to the flight

from abc import ABC, abstractmethod

class Commond(ABC):
    @abstractmethod
    def receive_message(self, message):
        pass
    @abstractmethod
    def send_message(self, message):
        pass

class Mediator():
    
    def __init__(self ):
        self.flight = None
        self.airline_desk = None
        
    def register_flight(self, flight):
        self.flight = flight
   
    def register_airline_desk(self, airline_desk):
        self.airline_desk = airline_desk
        
    def send_message(self, message,sender):
        if sender == "flight":
            self.airline_desk.receive_message(message)
        elif sender == "airline_desk":
            self.flight.receive_message(message)
    
class AirlineDesk(Commond):
    
    def __init__(self,mediator):
        self.mediator = mediator
        self.mediator.register_airline_desk(self)
    
    def send_message(self, message):
        self.mediator.send_message(message, "airline_desk")
        
    def receive_message(self, message):
        print(f"Airline Desk received message: {message}")
 
class Flight(Commond):
    def __init__(self,mediator):
        self.mediator = mediator
        self.mediator.register_flight(self)
        
    def send_message(self, message):
        self.mediator.send_message(message, "flight")
 
    def receive_message(self, message):
        print(f"Flight received message: {message}")
            
def main():
    mediator = Mediator()
    flight = Flight(mediator)
    airline_desk = AirlineDesk(mediator)
    flight.send_message("runway")
    airline_desk.send_message("landing")

main()