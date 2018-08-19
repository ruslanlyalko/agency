
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
//const moment = require('moment');
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();


exports.balanceRecalculate = functions.database.ref("/DATA/{userId}/ORDERS/{orderId}")
.onWrite((event, context) => {
    var orderObj = event.after.val();
    const userId = context.params.userId;  
	console.log("Recalculating for ", userId);	
	return recalculate(userId);
});

function recalculate(userId){
	const ordersPromise = admin.database().ref("/DATA").child(userId).child("ORDERS").once('value'); 
	
	return ordersPromise.then(ordersSS => {
		var income = 0;
		var expense = 0;			
		ordersSS.forEach(order => {
			orderObj = order.val();
			console.log("Income ", orderObj.income);	
			console.log("Expense ", orderObj.expense);	
			if(orderObj.income)
				income = income + orderObj.income;
			if(orderObj.expense)
				expense = expense + orderObj.expense;
		})
		var balance = income - expense;
		return storeUserData(userId, balance, income, expense);
	});
}

function storeUserData(userId, balance, income, expense){
	var userBalanceRef = admin.database().ref(`/USERS/${userId}/balance`);
	var userIncomeRef = admin.database().ref(`/USERS/${userId}/income`);
	var userExpenseRef = admin.database().ref(`/USERS/${userId}/expense`);
	
	userIncomeRef.set(income);
	userExpenseRef.set(expense);
	return userBalanceRef.set(balance).then(() => {
		return console.log('Balance updated: ', userId );	
	});
}

function sendNessagesViaFCM(tokens, payload){
	if(tokens.length > 0)
		return  admin.messaging().sendToDevice(tokens, payload)
			.then(response => {
				console.log("Push Sent: ", response);
				return 0;
			})
			.catch(error => {
				console.log("Push Error: ", error);
			});			
	else 
		return console.log("Push No Tokens");
}

