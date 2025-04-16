const UserService = require('./controller/userService')
const GroupService = require('./controller/groupService')
const ExpenseService = require('./controller/expenseService')

const userService = new UserService()

userService.addUser("John Doe", "john.doe@example.com")
userService.addUser("shubham PUBLIC", "shubham@example.com")
const users = userService._users
console.log(users)

const groupService = new GroupService()
const expenseService = new ExpenseService(groupService)

groupService.createGroup("john group1 ", [users[0]?.id])
groups = groupService._groups


groupService.createGroup("shubham group2", [users[0]?.id])
groupService.addMemberIntoGroup(groups[1]?.id, [users[0]?.id, users[1]?.id])

console.log(groups)

// pizza 100

expenseService.createExpenseInGroup(100, 'pizza', groups[0]?.id,users[0]?.id )
expenseService.createExpenseInGroup(100, 'burger', groups[1]?.id,users[1]?.id  )
expenseService.createExpenseInGroup(100, 'tea ', groups[1]?.id,users[1]?.id  )

console.log(expenseService._expense)

console.log(expenseService?._transaction)

console.log(expenseService.getExpenseofUserbyGroup(groups[1]?.id, users[1]?.id))




// groupService.deleteMemberIntoGroup(groups[0]?.id, users[1]?.id)





