
# in builder design pattern, we create a builder class to build the object of the main class


class User:
    
    def __init__(self, name, age, email):
        self.name = name
        self.age = age
        self.email = email
    
    @staticmethod
    def create_builder():
        return UserBuilder()

    def __str__(self):
        return f"User(name={self.name}, age={self.age}, email={self.email})"
    
    
class UserBuilder:
        
    def set_name(self, name):
        self.name = name
        return self

    def set_age(self, age):
        self.age = age
        return self

    def set_email(self, email):
        self.email = email
        return self

    def build(self):
        return User(self.name, self.age, self.email)
    
def main():
    user = User.create_builder().set_name("John").set_age(30).set_email("john@example.com").build()
    print(user)
    
if __name__ == "__main__":
    main()

   