const Group = require("./group");

class Expense{
    constructor(amount, description,group_id , paidBy){
        this.id = Math.round((Math.random()* 1000) +1)
        this.amount = amount;
        this.description = description;
        this.group_id = group_id,
        this.paidBy = paidBy
        
    }
}

module.exports = Expense 