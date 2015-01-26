var validator = $($formName).validate({
	rules : {
		intr_nbr : {required : true},
		intr_name : {required : true},
		brnchcrlmt : {required : true},
		addr1_l1 : {required : true},
		postcode1 : {required : true}
	},
	messages : {
		intr_nbr : {required : "推荐人工号不能为空"},
		intr_name : {required : "推荐人姓名不能为空"},
		brnchcrlmt : {required : "分行建议额度(万)不能为空"},
		addr1_l1 : {required : "地址-1不能为空"},
		postcode1 : {required : "邮政编码不能为空"}
	},
	errorPlacement : function(error, element) {
		element.after(error);
		if (layout) {
			layout.resizeLayout();
		}
	}
});