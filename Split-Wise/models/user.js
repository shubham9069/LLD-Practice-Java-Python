 class User{
    constructor(name, email){
        this.id = Math.round((Math.random()*1000)+1)
        this.name = name;
        this.email = email; // not duplicate 
    }
}
module.exports  = User