class Group{
    constructor(name, user_ids){
        this.id = Math.round((Math.random()*1000) +1); 
        this.name = name;
        this.user_ids = user_ids; 
    }

}

 module.exports = Group;