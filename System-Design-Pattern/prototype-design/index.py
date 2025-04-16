# Prototype Design Pattern its use when creating a new object is verycosty so we can clone the object using existing object 
# and then modify it as needed.
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def draw(self):
        pass

class Circle(Shape):
    __color = ""
    count = 0
    
    def __init__(self, color):
        self.__color = color
        self.count += 1

    def draw(self):
        self.count += 1
        print("Drawing a circle with color: ", self.__color, "count: ", self.count)
        
    def clone(self):
        return Circle(self.__color)
    

def main():
    circle = Circle("Red")
    circle.draw()
    clonned_circle = circle.clone()
    clonned_circle.draw()
    clonned_circle.draw()
    clonned_circle.draw()

if __name__ == "__main__":
    main()

