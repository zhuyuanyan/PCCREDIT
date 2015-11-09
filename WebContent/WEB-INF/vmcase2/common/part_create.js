     //$("#id_save_button").click(function() {
      //  if ($($formName).valid()) {
      //    var formjson = $($formName).serialize();
       //   $.get($($formName).attr("action"), formjson, function(data, textStatus, jqXhr) {
        //    if (data.success) {
          //    #if ($request.getParameter('callBackUrl').indexOf('?') != -1)
          //      navigateTo("${request.getParameter('callBackUrl').replaceAll('\?.*','')}" + "?recordId=" + data.recordId);
           //   #else
         //      navigateTo("${request.getParameter('callBackUrl')}" + "?recordId=" + data.recordId);
         //     #end
         //   }
        //  });
      //  }
     // });
	$("#id_save_button").click(function() {
			Dialog.confirm("确定提交吗？", "提示",
			    function() {
					$('.dialog3').hide();
					//$("#appId1").val("${appId}");
					#set ($formName = "'#id_module_form'");
					var formjson = $($formName).serialize();
					 $.ajax({
							//url : "${contextPath}/customer/amountadjustment/apply.json",
						    url : "${contextPath}/system/role/insert.json",
							type : "get",
							data : formjson,
							success : function(data) {
								if (data.success) {
									window.top.Dialog.message(data.message);	
									#if ($request.getParameter('callBackUrl').indexOf('?') != -1)
								         navigateTo("${request.getParameter('callBackUrl').replaceAll('\?.*','')}" + "?recordId=" + data.recordId);
								     #else
								         navigateTo("${request.getParameter('callBackUrl')}" + "?recordId=" + data.recordId);
								     #end
								}
							}
					}); 
			});
		});