const User  = require('../models/user')
class UserService{
  constructor(){
    this.users = [];
  }
  get _users(){
    return this.users;
  }
  addUser(name,email){
    if (this.checkUser(email)){
        throw new Error("User already exists")
    }
    const new_user = new User(name, email)
    this.users.push(new_user)
  }
  checkUser(email){
    // Check if user with given name and email already exists

    return this.users.some((user) => user.email == email)
  }
 
}

module.exports = UserService;

