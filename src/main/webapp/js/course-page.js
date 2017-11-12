'use strict';

function validateCourseForm(){
	var courseCode = document.getElementById("courseCode").value;
	var courseName = document.getElementById("courseName").value;
	
	if(courseCode == "" || courseName == ""){
		alert("Enter in all required fields");
		return false;
	}else{
		return true;
	}
}

function confirmCourseDelete(){
	var doDelete = confirm("Are your sure you want to delete this course?");
	
	if(doDelete == true){
		return true;
	}else{
		return false;
	}
}