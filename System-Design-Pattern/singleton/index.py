class Singleton:
    _instance = None
    
    @classmethod # here we use classmethod to create a singleton instance
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = Singleton()
        return cls._instance
     
    def do_something(self):
        print("Doing something")









def main():
    s1 = Singleton().get_instance()
    s2 = Singleton().get_instance()
    print(s1 is s2) # why this false ?
     
    
 

if __name__ == "__main__":
    main()