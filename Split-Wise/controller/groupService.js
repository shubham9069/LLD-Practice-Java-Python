const Group = require('../models/group') 

class GroupService{
    
    constructor(){
        this.groups = [];
    }
    get _groups(){
        return this.groups;
    }

    createGroup(name, user_ids){
        if (!Array.isArray(user_ids)){
            throw new Error("User ids should be an array")
        }
        const users = new Set(user_ids)

        const new_group = new Group(name,users)
        this.groups.push(new_group)
    }
    addMemberIntoGroup(group_id, user_ids){

        for (let i = 0; i < this.groups.length; i++){
            if (this.groups[i].id === group_id){
                
                user_ids.forEach(user_id =>this.groups[i].user_ids.add(user_id))
                return 
                
            }
        }
         throw new Error("group not found")
    }
    deleteMemberIntoGroup(group_id, user_id){
        // i  there is amount the its will be settle  or transfer to each people 
        
        for (let i = 0; i < this.groups.length; i++){
            if (this.groups[i].id === group_id){
                this.groups[i].user_ids.delete(user_id)
                return 
                
            }
        }
         throw new Error("group not found")
        
    }
    
    
}

module.exports = GroupService;