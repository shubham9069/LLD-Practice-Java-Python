const Transaction = require('../models/transaction')

class TransactionService{

    constructor(){
        this.transaction = []

    }
    get _transaction(){
        return this.transaction
    }

    createTransactionUser(user_id,  expense_id, unpaid_amount,total_amount){
        const new_transaction = new Transaction(user_id, expense_id, unpaid_amount, total_amount)
        this.transaction.push(new_transaction)
    }
    getAllTransactionByUser(user_id){  
        const all_transaction = this.transaction.filter(elem=>elem?.user_id == user_id)
        if (all_transaction.length== 0){
            return "no transaction found "
        }

    }
    getAllTransactionOfExpense(expense_id){
        const all_transaction = this.transaction?.filter(elem=>elem?.expense_id == expense_id)
        if (all_transaction?.length == 0) return "no transaction found " 
        return all_transaction
    }
    getOwnAmountbyUser(expense_id,user_id){
        const user_transaction = this.transaction?.find((elem)=>elem.expense_id== expense_id && elem.user_id!=user_id)
        if (!user_transaction){
            return null
        }
        return user_transaction
    }
}
module.exports = TransactionService