'use strict';

function validateStudentForm(){
	var studentFirstName = document.getElementById("studentFirstName").value;
	var studentLastName = document.getElementById("studentLastName").value;
	var studentEmail = document.getElementById("studentEmail").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	if(studentFirstName == "" || studentLastName == "" || studentEmail == "" || username == "" || password == ""){
		alert("Enter in all required fields");
		return false;
	}else{
		return true;
	}
}

function confirmStudentDelete(){
	var doDelete = confirm("Are your sure you want to delete this student?");
	
	if(doDelete == true){
		return true;
	}else{
		return false;
	}
}