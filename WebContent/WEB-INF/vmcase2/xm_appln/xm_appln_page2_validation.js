var validator = $($formName).validate({
	rules : {
		app_source : {required : true},
		branch : {required : true},
		ab_branch : {required : true},
		cp_nbrmth : {required : true},
		debit_brn : {required : true},
		cycle_nbr : {required : true},
		crdlmt_req : {required : true},
		custr_nbr1 : {required : true},
		embname_d1 : {required : true},
		cdesploc1 : {required : true},
		aval_nbr : {required : true},
		achk_nbr : {required : true},
		adec_nbr : {required : true}
	},
	messages : {
		app_source : {required : "申请来源不能为空"},
		branch : {required : "分行不能为空"},
		ab_branch : {required : "收件分行不能为空"},
		cp_nbrmth : {required : "自动分期期数不能为空"},
		debit_brn : {required : "借记卡开户分行号不能为空"},
		cycle_nbr : {required : "账单日不能为空"},
		crdlmt_req : {required : "申请信用额度不能为空"},
		custr_nbr1 : {required : "证件号码不能为空"},
		embname_d1 : {required : "凸字姓名不能为空"},
		cdesploc1 : {required : "递送分行不能为空"},
		aval_nbr : {required : "调查人工号不能为空"},
		achk_nbr : {required : "审核人工号不能为空"},
		adec_nbr : {required : "审批人工号不能为空"}
	},
	errorPlacement : function(error, element) {
		element.after(error);
		if (layout) {
			layout.resizeLayout();
		}
	}
});