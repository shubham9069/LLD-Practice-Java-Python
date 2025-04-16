class Transaction{
    constructor(user_id,expense_id, unpaid_amount, total_amount){

        this.id = Math.round((Math.random()*1000) +1)
        this.user_id = user_id;
        this.expense_id = expense_id;
        this.unpaid_amount = unpaid_amount;
        this.total_amount = total_amount;

    }
}
module.exports = Transaction