# its decide the behavior of the object at the runtime
from abc import ABC, abstractmethod

class Strategy(ABC):
    @abstractmethod
    def calculate(self):
        pass
    
class StrategyContext:
    def __init__(self):
        self.strategy = None
        self.number1 = 0
        self.number2 = 0
        
    def set_strategy(self, strategy):
        self.strategy = strategy

    def calculate(self):
        return self.strategy.calculate(self.number1, self.number2)

class Add(Strategy):
    def calculate(self, a, b):
        return a + b

class Subtract(Strategy):
    def calculate(self, a, b):
        return a - b
    
    
def main():
    context = StrategyContext()
    context.number1 = 1
    context.number2 = 2
    context.set_strategy(Add())
    print(context.calculate())
    
    context.set_strategy(Subtract())
    print(context.calculate())

if __name__ == "__main__":
    main()