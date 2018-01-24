'use strict';

function onCancel(){
	var selectTwo = document.getElementById("sel2");
	var option;
	
	if(selectTwo.options.length === 0){
		option = document.createElement("option");
		option.text = option.value = "someValue";
		option.selected = true;
		selectTwo.add(option);
	}
}