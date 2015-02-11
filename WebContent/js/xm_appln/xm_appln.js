$(document).ready(function(){			
	$(".mast").each(function(){//初始化必填项文本框样式
		if($(this).val()!=""){//必填项不为空时样式
			$(this).css("background","#ffffff")
		}
		else{//必填项为空时样式
			$(this).css("background","#ccebf5")
		}
	})
});

function changeBg(obj){//必填项改变时判断是否改变样式
	if($(obj).val()==""){
		$(obj).css("background","#ccebf5")
	}
	else{
		$(obj).css("background","#ffffff")
	}			
}

function Open(obj){//展开或隐藏box	
	var className=$(obj).parent().find("div[name=content]").attr("class");
	if(className=="display"){//隐藏时
		$(obj).parent().find("div[name=content]").attr("class","");
		$(obj).find("img").attr("src",contextPath+"/images/xm_appln/min.gif");
	}
	else{//展开时
		$(obj).parent().find("div[name=content]").attr("class","display");
		$(obj).find("img").attr("src",contextPath+"/images/xm_appln/max.gif");
	}
}
function Open2(obj){//展开或隐藏box	
	var className=$(obj).parent().find("div[name=content]").attr("class");
	if(className=="display information"){//隐藏时
		$(obj).parent().find("div[name=content]").attr("class","information");
		$(obj).find("img").attr("src",contextPath+"/images/xm_appln/min.gif");
	}
	else{//展开时
		$(obj).parent().find("div[name=content]").attr("class","display information");
		$(obj).find("img").attr("src",contextPath+"/images/xm_appln/max.gif");
	}
}
function selectTag(id,obj){//tab页
	$(".tags li").attr("class","");
	$(obj).attr("class","active");
	$(".Content div").hide();
	$("#"+id).show();
}
function addCard(){
	var i=$(".tags li").length;
	if(i<9){
		/*添加tab标签*/
		var text1="<li onclick='selectTag(\"tagContent"+i+"\",this);'>附卡"+i+"</li>"
		$("#tab1").html($("#tab1").html()+text1);
		/*添加tab内容*/
		var text2="<div class='tagContent' id='tagContent"+i+"'>"+
						"<table class='message'>"+
							"<tr>"+
								"<td class='label'>证件号码</td>"+
								"<td>"+
									"<input type='text' class='text' name='custr_nbr"+(i+1)+"' maxlength='19'/>"+
								"</td>"+
								"<td class='label'>凸字姓名</td>"+
								"<td>"+
									"<input type='text' class='text' name='embname_d"+(i+1)+"' maxlength='28'/>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>是否凸字?</td>"+
								"<td>"+
									"<input type='checkbox' value='1' name='emboss_cd"+(i+1)+"'/>"+
								"</td>"+
								"<td class='label'>需要PIN密信?</td>"+
								"<td>"+
									"<input type='checkbox' value='1' name='pin_reqd"+(i+1)+"'/>"+
								"</td>"+
							"</tr>"+
							
							"<tr>"+
								"<td class='label'>开通短信通知?</td>"+
								"<td>"+
									"<input type='checkbox' value='1' name='sms_yn"+(i+1)+"'/>"+
								"</td>"+
								"<td class='label'>消费需要密码?</td>"+
								"<td>"+
									"<input type='checkbox' value='1' name='pin_chk"+(i+1)+"'/>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>递送分行</td>"+
								"<td>"+
									"<input type='text' class='text small' name='cdesploc"+(i+1)+"' value='0' maxlength='4'/>"+
								"</td>"+
								"<td class='label'>递送方式</td>"+
								"<td>"+
									"<select name='cdespmtd"+(i+1)+"'>"+
										"<option value='到收件网点领取'>到收件网点领取</option>"+
										"<option value='快递给持卡人'>快递给持卡人</option>"+
										"<option value='重发卡暂不递送-信用原因'>重发卡暂不递送-信用原因</option>"+
										"<option value='暂不递送-持卡人再境外'>暂不递送-持卡人再境外</option>"+
										"<option value='寄往指定地址'>寄往指定地址</option>"+
									"</select>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>递送费用</td>"+
								"<td>"+
									"<select name='courierf"+(i+1)+"' class='small'>"+
										"<option value='无'>无</option>"+
										"<option value='同城'>同城</option>"+
										"<option value='外地'>外地</option>"+
										"<option value='境外'>境外</option>"+
									"</select>"+
								"</td>"+
								"<td class='label'>卡片版面</td>"+
								"<td>"+
									"<select name='cdfrm"+(i+1)+"'>"+
										"<option value='A'>标准卡版面</option>"+
										"<option value='B'>60周年卡表面</option>"+
										"<option value='C'>晋江金翼主题卡版面</option>"+
										"<option value='D'>福州藏天园卡</option>"+
										"<option value='E'>泉州渔贷卡</option>"+
										"<option value='F'>泉州农贷卡</option>"+
										"<option value='G'>泉州商贷卡</option>"+
									"</select>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>额度百分比</td>"+
								"<td>"+
									"<input type='text' class='text' name='cred_lmt"+(i+1)+"' maxlength='2'/>"+
								"</td>"+
								"<td class='label'>预留暗语</td>"+
								"<td>"+
									"<input type='text' class='text' name='spec_inst"+(i+1)+"' maxlength='12'/>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>开通ATM转账</td>"+
								"<td>"+
									"<label><input type='radio' value='MR' name='atm"+(i+1)+"' checked/>取产品新卡参数</label>"+
									"<label><input type='radio' value='BKT' name='atm"+(i+1)+"' />不开通</label>"+
									"<label><input type='radio' value='KT' name='atm"+(i+1)+"' />开通</label>"+
								"</td>"+
								"<td class='label'>开通电话转账</td>"+
								"<td>"+
									"<label><input type='radio' value='MR' name='tele"+(i+1)+"' checked/>取产品新卡参数</label>"+
									"<label><input type='radio' value='BKT' name='tele"+(i+1)+"' />不开通</label>"+
									"<label><input type='radio' value='KT' name='tele"+(i+1)+"' />开通</label>"+
								"</td>"+
							"</tr>"+
							"<tr>"+
								"<td class='label'>开通网银转账</td>"+
								"<td>"+
									"<label><input type='radio' value='MR' name='net"+(i+1)+"' checked/>取产品新卡参数</label>"+
									"<label><input type='radio' value='BKT' name='net"+(i+1)+"' />不开通</label>"+
									"<label><input type='radio' value='KT' name='net"+(i+1)+"' />开通</label>"+
								"</td>"+
								"<td class='label'>开通手机转账</td>"+
								"<td>"+
									"<label><input type='radio' value='MR' name='phone"+(i+1)+"' checked/>取产品新卡参数</label>"+
									"<label><input type='radio' value='BKT' name='phone"+(i+1)+"' />不开通</label>"+
									"<label><input type='radio' value='KT' name='phone"+(i+1)+"' />开通</label>"+
								"</td>"+
							"</tr>"+
						"</table>"+
					"</div>"
		$("#Content1").html($("#Content1").html()+text2);
	}
	
	$("#cardCount").val($(".tags li").length);
}

function disableForm() {
	$("input[type='text']").attr("disabled",true);
	$("input[type='radio']").attr("disabled",true);
	$("input[type='checkbox']").attr("disabled",true);
	$("select").attr("disabled",true);
}