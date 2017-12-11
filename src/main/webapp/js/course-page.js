'use strict';

function validateCourseForm(){
	var courseCode = document.getElementById("courseCode").value;
	var courseName = document.getElementById("courseName").value;
	var selectedSemester = document.getElementById("sel2")
	
	if(courseCode == "" || courseName == "" || selectedSemester.options.length == 0){
		alert("Enter in all required fields");
		return false;
	}else{
		return true;
	}
}

function selectRightBox(){
	var rightBox = document.getElementById("sel1").value;
	
	var strUser = rightBox.options[rightBox.selectedIndex].text;
	
}

function confirmCourseDelete(){
	var doDelete = confirm("Are your sure you want to delete this course?");
	
	if(doDelete == true){
		return true;
	}else{
		return false;
	}
}

function rightBox() {

    var selectOne = document.getElementById("sel1");
    var selectTwo = document.getElementById("sel2");
    var option;

    for(var i = 0; i < selectOne.options.length; i++){
        if(selectOne.options[i].selected){
            option = document.createElement("option");
            option.text = option.value = selectOne.options[i].text;
            option.selected = true;
            selectTwo.add(option);
        }
    }

    var i = 0;

    while(i < selectOne.options.length){
        if(selectOne.options[i].selected){
            selectOne.remove(selectOne.selectedIndex);
            i = 0;
        }else{
            i++;
        }
    }
}

function leftBox(){
    var selectOne = document.getElementById("sel1");
    var selectTwo = document.getElementById("sel2");
    var option;

    for(var i = 0; i < selectTwo.options.length; i++){
	    if(selectTwo.options[i].selected){
	        option = document.createElement("option");
	        option.text = option.value = selectTwo.options[i].text
	        selectOne.add(option);
	    }
    }

    var i = 0;

    while(i < selectTwo.options.length){
        if(selectTwo.options[i].selected){
            selectTwo.remove(selectTwo.selectedIndex);
            i = 0;
        }else{
            i++;
        }
    }
}