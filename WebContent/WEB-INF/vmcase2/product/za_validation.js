var validator = $($formName).validate({
	rules:
    { 
		sqName:{required:true},
		customerType:{required:true},
		customerLevel:{required:true},
		ed:{required:true},
     },
messages:
    {
	sqName:{required:"商圈名称不能为空"},
	customerType:{required:"客户类型不能为空"},
	customerLevel:{required:"客户经理级别不能为空"},
	ed:{required:"最高额度不能为空"},
   },
	errorPlacement : function(error, element) {
		element.after(error);
		if(layout){
			layout.resizeLayout();
		}
	}
});