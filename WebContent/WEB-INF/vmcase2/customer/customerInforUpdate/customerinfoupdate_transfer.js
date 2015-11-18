var validator = $($formName).validate({
	rules : {
		customerManagerId : {
			required : true
		},
		auditUserNames :{
			required : true
		}
	},
	messages : {
		customerManagerId : {
			required : "移交客户经理不能为空"
		},
		auditUserNames :{
			required : "营销人员不能为空"
		}
	},
	errorPlacement : function(error, element) {
		element.after(error);
		if (layout) {
			layout.resizeLayout();
		}
	}
});