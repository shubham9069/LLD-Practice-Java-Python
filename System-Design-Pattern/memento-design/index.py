# this pattern is used to store the state of an object at a particular point of time and restore it later 

class Memento:
    def __init__(self, state):
        self.state = state
        
    def get_state(self):
        return self.state
    

class bankBalance: # this is the originator class
    def __init__(self, ):
        self.balance = 0
        
    def deposit(self, amount):
        self.balance += amount
        return self.balance
        
        
    def withdraw(self, amount):
        self.balance -= amount
        return self.balance
        
    def get_balance(self):
        return self.balance
    
class bankBalanceMemento:
    def __init__(self):
        self.memento = []
        
    def add_memento_state(self, balance):
        self.memento.append(Memento(balance))
        
    def get_memento_state(self, index):
        return self.memento[index].get_state()
        
def main():
    bank_balance = bankBalance()
    memento = bankBalanceMemento()
    memento.add_memento_state(bank_balance.deposit(100))
    memento.add_memento_state(bank_balance.deposit(200))
    memento.add_memento_state(bank_balance.deposit(300))
    memento.add_memento_state(bank_balance.withdraw(100))
    memento.add_memento_state(bank_balance.deposit(200))
    
    print("current balance: ", bank_balance.get_balance())
    
    # its give you current balance of that state like bank statement we maintain current balance for each transaction
    print("state 1: ", memento.get_memento_state(1))
    print("state 2: ", memento.get_memento_state(2))
    print("state 3: ", memento.get_memento_state(3))
    print("state 4: ", memento.get_memento_state(4))
  
    
if __name__ == "__main__":
    main()