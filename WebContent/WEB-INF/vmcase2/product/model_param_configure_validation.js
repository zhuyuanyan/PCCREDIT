var validator = $($formName).validate({
	rules:
    { 
		dictType:{required:true},
		typeCode:{required:true},
		typeName:{required:true}
     },
messages:
    {
		dictType:{required:"字典类型不能为空"},
		typeCode:{required:"字典code不能为空"},
		typeName:{required:"名称不能为空"}
   },
	errorPlacement : function(error, element) {
		element.after(error);
		if(layout){
			layout.resizeLayout();
		}
	}
});