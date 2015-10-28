function changeSjType(value){
	if(value=="3"){
		document.getElementById("type_1").style.display="none";
		document.getElementById("type_2").style.display="inline";
	}else if(value=="1"){
		document.getElementById("type_1").style.display="inline";
		document.getElementById("type_2").style.display="none";
	}else{
		document.getElementById("type_1").style.display="none";
		document.getElementById("type_2").style.display="none";
	}

}