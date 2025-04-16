const Expense  = require('../models/expense')
const Transaction = require('../models/transaction')
const TransactionService = require('../controller/transactionService')

class ExpenseService{

    constructor(groupService){
        this.expense=[]
        this.groupService = groupService
        this.transactionService = new TransactionService()
    }
    get _expense(){
        return this.expense
    }
    get _transaction(){
        return this.transactionService?._transaction
    }
    get _groups(){
        return this.groupService?._groups
    }

    createExpenseInGroup(amount,description,group_id, paidBy_id){ 
        // create trabsaction of group 
        const new_expense = new Expense(amount, description, group_id, paidBy_id)
        this.expense.push(new_expense)

        const user_transaction  = []
        const group_found = this.groupService._groups?.find((group)=>{
            return group?.id == group_id
        })
        if (!group_found){
            throw new Error('group not found ')
        }
        const each_amount = amount / (group_found?.user_ids?.size)
        group_found?.user_ids.forEach(user_id => {
            this.transactionService.createTransactionUser(user_id,new_expense?.id, each_amount, amount )
        });
        
    }
    getAllExpenseByGroup(group_id){
        const all_expense = this.expense.filter((expe)=>expe?.group_id ==group_id )
        if (all_expense.length == 0){
            return "no expense found"
        }
        return all_expense

    }
    getExpenseInformation(expense_id){
        const all_expense = this.expense.filter((expe)=>expe?.id == expense_id )
        if (all_expense.length == 0){
            return "no expense found"
        }
        return all_expense

    }

    getExpenseofUserbyGroup(group_id, user_id){
        const all_expense = this.expense.filter(elem=>elem?.group_id == group_id)
        if (!all_expense?.length){
            return 'group not found '
        }
        let totalAmount = 0
        all_expense?.forEach((elem)=>{
            totalAmount += this.transactionService.getOwnAmountbyUser(elem?.id, user_id)?.unpaid_amount
        })
        if (!totalAmount){
            return ' no expense found'
        }
        return totalAmount
    }

}

module.exports = ExpenseService