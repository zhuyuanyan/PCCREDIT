var validator = $($formName).validate({
	rules:
    { 
		productName:{required:true},
		ed:{required:true},
		lv:{required:true},
		nf:{required:true}
     },
messages:
    {
		productName:{required:"产品名称不能为空"},
		ed:{required:"额度不能为空"},
		lv:{required:"利率不能为空"},
		nf:{required:"年费不能为空"}
   },
	errorPlacement : function(error, element) {
		element.after(error);
		if(layout){
			layout.resizeLayout();
		}
	}
});