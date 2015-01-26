var validator = $($formName).validate({
	rules : {
		surname : {required : true},
		mo_phone : {required : true},
		mar_status : {required : true}
	},
	messages : {
		surname : {required : "姓名不能为空"},
		mo_phone : {required : "手机不能为空"},
		mar_status : {required : "婚姻状况不能为空"}
	},
	errorPlacement : function(error, element) {
		element.after(error);
		if (layout) {
			layout.resizeLayout();
		}
	}
});