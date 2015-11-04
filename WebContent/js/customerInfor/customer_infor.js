function transferInDisplay(){
	var status = $("#status").val();
	if(status === 'turn'){
		Dialog.message("禁止重复移交");
		return;
	}
	var id = $("#id").val();
	Dialog.load();
	$.ajax({
		type:'GET',
		url:"transfer.json",
		data:'id='+id,
		success:function(msg){
			Dialog.closeLoad();
			if(msg.success == true){
				Dialog.message(msg.message);
			}else{
				Dialog.message(msg.message);
			}
			window.location.href=url;
		}
	});
}



function transferInTr(id){
	Dialog.load();
	$.ajax({
		type:'GET',
		url:"transfer.json",
		data:'id='+id,
		success:function(msg){
			Dialog.closeLoad();
			if(msg.success == true){
				Dialog.message(msg.message);
			}else{
				Dialog.message(msg.message);
			}
			window.location.href=url;
		}
	});
}

function transfer(){
	if ($(".checkbox :checked").length == 1) {
		var status = $(".checkbox :checked").next("input:hidden").val();
		if(status === 'turn'){
			Dialog.message("移交操作中...禁止重复移交");
			return;
		}
        var rowid = $($(".checkbox :checked")[0]).attr("value");
        Dialog.load();
    	$.ajax({
    		type:'GET',
    		url:"transfer.json",
    		data:'id='+rowid,
    		success:function(msg){
    			Dialog.closeLoad();
    			if(msg.success == true){
    				Dialog.message(msg.message);
    			}else{
    				Dialog.message(msg.message);
    			}
    			window.location.href=url;
    		}
    	});
	}else{
		Dialog.message("请选择一行");
	}
}

function displaycardinfomation(){
	if ($(".checkbox :checked").length == 1) {
        var rowid = $($(".checkbox :checked")[0]).attr("value");
        var id=rowid.split('_')[1];
        self.location.href= contextPath + "/customer/basiccustomerinfor/displayaccountinfolist.page?customerId=" + id;
	}else{
		
	}
}

//优质行业
function yzhy(){
	zzed();
}

//担保行业
function dbhy(){
	zzed();
}

//无房
function wf(){
	zzed();
}

//有房
function yf(){
	zzed();
}

//calc  额度
function zzed(){
	if($("#ApplyIntopiecesSpareType_1").val() =='2'){//有房
		hasHouse();
	}else if ($("#ApplyIntopiecesSpareType_1").val()=='1'){//无房
		noHouse();
	}else if ($("#ApplyIntopiecesSpareType_1").val()=='3'){//担保
		promise();
	}else {
		goodwork();
	}
}



//1.有房客户
function hasHouse (){
	if($("#house_type").val() == '1'){//i.房子在本人名下：
		var value_1 = $("#value_1").val()==''?0:$("#value_1").val();
		var value_2 = $("#value_2").val()==''?0:$("#value_2").val();
		var value_3 = $("#value_3").val()==''?0:$("#value_3").val();
		var value_4 = $("#value_4").val()==''?0:$("#value_4").val();
		var value_5 = $("#value_5").val()==''?0:$("#value_5").val();
		var value_6 = $("#value_6").val()==''?0:$("#value_6").val();
		var value_7 = $("#value_7").val()==''?0:$("#value_7").val();
		var value_7_1 = $("#value_7_1").val()==''?0:$("#value_7_1").val();
		var value_8 = $("#value_8").val()==''?0:$("#value_8").val();
		var value_9 = $("#value_9").val()==''?0:$("#value_9").val();
		var value_10 = $("#value_10").val()==''?0:$("#value_10").val();
		var value_11 = $("#value_11").val()==''?0:$("#value_11").val();
		var value_12 = $("#value_12").val()==''?0:$("#value_12").val();
		
		//净资产
		var asset_1 = Number(value_1) + Number(value_2) + Number(value_3) - 
				      Number(value_4) - Number(value_5) - Number(value_6) - 
				      Number(value_7) - Number(value_7_1) - Number(value_8) -
				      Number(value_9) - Number(value_10) - Number(value_11) - Number(value_12);
		
		var total ="";
		if(asset_1 < 400000){
			total = 100000;
		}else if(asset_1>=400000 && asset_1 <= 700000){
			total = 150000;
		}else if(asset_1>700000 && asset_1 < 1000000){
			total = 200000;
		}else if(asset_1>=1000000 && asset_1 <= 1300000){
			total = 250000;
		}else if(asset_1 >1300000){
			total = 300000;
		}
		document.getElementById("asset_1").value = total;
	}else{//ii.房子为家庭共有或者配偶独有：
		var value_1 = $("#value_1").val()==''?0:$("#value_1").val();
		var value_2 = $("#value_2").val()==''?0:$("#value_2").val();
		var value_3 = $("#value_3").val()==''?0:$("#value_3").val();
		var value_4 = $("#value_4").val()==''?0:$("#value_4").val();
		var value_5 = $("#value_5").val()==''?0:$("#value_5").val();
		var value_6 = $("#value_6").val()==''?0:$("#value_6").val();
		var value_7 = $("#value_7").val()==''?0:$("#value_7").val();
		var value_7_1 = $("#value_7_1").val()==''?0:$("#value_7_1").val();
		var value_8 = $("#value_8").val()==''?0:$("#value_8").val();
		var value_9 = $("#value_9").val()==''?0:$("#value_9").val();
		var value_10 = $("#value_10").val()==''?0:$("#value_10").val();
		var value_11 = $("#value_11").val()==''?0:$("#value_11").val();
		var value_12 = $("#value_12").val()==''?0:$("#value_12").val();

		//净资产
		var asset_1 = Number(value_1) + Number(value_2) + Number(value_3) - 
				      Number(value_4) - Number(value_5) - Number(value_6) - 
				      Number(value_7) - Number(value_7_1) - Number(value_8) -
				      Number(value_9) - Number(value_10) - Number(value_11) - Number(value_12);
		
		var total ="";
		if($("#house_type1").val()=="1"){//已提供配偶征信
			if(asset_1 < 400000){
				total = 100000;
			}else if(asset_1>=400000 && asset_1 <= 700000){
				total = 150000;
			}else if(asset_1>700000 && asset_1 < 1000000){
				total = 200000;
			}else if(asset_1>=1000000 && asset_1 <= 1300000){
				total = 250000;
			}else if(asset_1 >1300000){
				total = 300000;
			}
		}else{//未提供配偶征信：
			if(asset_1 < 600000){
				total = 100000;
			}else if(asset_1>=600000 && asset_1 <= 1000000){
				total = 150000;
			}else if(asset_1>1000000 && asset_1 < 1400000){
				total = 200000;
			}else if(asset_1>=1400000 && asset_1 <= 1800000){
				total = 250000;
			}else if(asset_1 >1800000){
				total = 300000;
			}
		}
		document.getElementById("asset_1").value = total;
	}
}

//2.无房客户
function noHouse (){
	var total ="";
	if($("#house_type2").val()=='1'){//i.散件：
		var value_13 =  $("#value_13").val() == '1'?2:0;
		var value_14 =	$("#value_14").val() == '1'?2:0;
		var value_15 =	$("#value_15").val() == '1'?2:0;
		var value_16 =	$("#value_16").val() == '1'?1:0;
		var value_17 =	$("#value_17").val() == '1'?2:0;
		var value_18 =	$("#value_18").val() == '1'?2:0;
		var value_19 =	$("#value_19").val() == '1'?1:0;
		var value_20 =	$("#value_20").val() == '1'?1:0;
		var value_21 =	$("#value_21").val() == '1'?1:0;
		var value_22 =	$("#value_22").val() == '1'?5:0;
		var value_23 =	$("#value_23").val() == '1'?2:0;
		var value_24 =	$("#value_24").val() == '1'?2:0;
		
		total = Number(value_13)+Number(value_14)+Number(value_15)+Number(value_16)+Number(value_17)+
				Number(value_18)+Number(value_19)+Number(value_20)+Number(value_21)+Number(value_22)+
				Number(value_23)+Number(value_24)+Number(2);
	
	}else if($("#house_type2").val()=='2'){//ii.在我行有存款
		 var value_25 =	$("#value_25").val();
		 total =  Math.round(value_25 * 0.5)>150000 ? 150000 : Math.round(value_25 * 0.5);
	}else{//iii.持我行股金
		 var value_26 =	$("#value_26").val();
		 var value_27 =	$("#value_27").val();
		 total = Number(value_26) - Number(value_27);
	}
	document.getElementById("asset_1").value = total;
}

//3.担保客户
function promise (){
	var total = "";
	if($("#house_type3").val() == '1'){//i.外地无房客户（担保人必须为厦门本地有房客户）：
		var value_28 = $("#value_28").val() ==''?0 : $("#value_28").val();
		var value_29 = $("#value_29").val() ==''?0 : $("#value_29").val();
		var value_30 = $("#value_30").val() ==''?0 : $("#value_30").val();
		var value_31 = $("#value_31").val() ==''?0 : $("#value_31").val();
		var value_32 = $("#value_32").val() ==''?0 : $("#value_32").val();
		var value_33 = $("#value_33").val() ==''?0 : $("#value_33").val();
		var value_34 = $("#value_34").val() ==''?0 : $("#value_34").val();
		var value_35 = $("#value_35").val() ==''?0 : $("#value_35").val();
		var value_36 = $("#value_36").val() ==''?0 : $("#value_36").val();
		var value_37 = $("#value_37").val() ==''?0 : $("#value_37").val();
		var value_38 = $("#value_38").val() ==''?0 : $("#value_38").val();
		var value_39 = $("#value_39").val() ==''?0 : $("#value_39").val();
		
		total = Number(value_28)+ Number(value_29)+ Number(value_30)+ Number(value_31)
				-Number(value_32)- Number(value_33)- Number(value_34)- Number(value_35)
				-Number(value_36)- Number(value_37)- Number(value_38)- Number(value_39);
		
		if(total < 800000){
			total = 80000;
		}else if(total>=800000 && total<=1400000){
			total = 100000;
		}else if(total>=14000000 && total<=2000000){
			total = 150000;
		}else{
			total = 200000;
		}
	}else if($("#house_type3").val() == '2'){//ii.本地客户，担保人有房：
		var value_28 = $("#value_28").val() ==''?0 : $("#value_28").val();
		var value_29 = $("#value_29").val() ==''?0 : $("#value_29").val();
		var value_30 = $("#value_30").val() ==''?0 : $("#value_30").val();
		var value_31 = $("#value_31").val() ==''?0 : $("#value_31").val();
		var value_32 = $("#value_32").val() ==''?0 : $("#value_32").val();
		var value_33 = $("#value_33").val() ==''?0 : $("#value_33").val();
		var value_34 = $("#value_34").val() ==''?0 : $("#value_34").val();
		var value_35 = $("#value_35").val() ==''?0 : $("#value_35").val();
		var value_36 = $("#value_36").val() ==''?0 : $("#value_36").val();
		var value_37 = $("#value_37").val() ==''?0 : $("#value_37").val();
		var value_38 = $("#value_38").val() ==''?0 : $("#value_38").val();
		var value_39 = $("#value_39").val() ==''?0 : $("#value_39").val();
		
		total = Number(value_28)+ Number(value_29)+ Number(value_30)+ Number(value_31)
				-Number(value_32)- Number(value_33)- Number(value_34)- Number(value_35)
				-Number(value_36)- Number(value_37)- Number(value_38)- Number(value_39);
		
		if(total < 600000){
			total = 80000;
		}else if(total>=600000 && total<=1000000){
			total = 100000;
		}else if(total>10000000 && total<1500000){
			total = 150000;
		}else{
			total = 200000;
		}
	}else{//iii.本地客户，担保人无房：
		total = Number($("#value_40").val()) * Number(5) >=200000?200000: Number($("#value_40").val()) * Number(5)
	}
	document.getElementById("asset_1").value = total;
}

//4.优质行业
function goodwork(){
	var total ="";
	if($("#value_41").val() == '1'){
		total = 100000;
	}else if ($("#value_41").val() =='2'){
		total = 200000;
	}else{
		total =300000;
	} 
	document.getElementById("asset_1").value = total;
}



