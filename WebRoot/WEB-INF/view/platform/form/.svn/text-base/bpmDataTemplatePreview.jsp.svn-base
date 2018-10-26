<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>业务数据模板预览</title>
	<%@include file="/commons/include/get.jsp" %>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/ajaxgrid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/Export.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/util/DialogUtil.js" ></script>
	<script type="text/javascript"><!--
    var taskId=${taskId}; 
     
	$(function(){
		handleAjaxSearchKeyPress();
		Export.initExportMenu();   	
	});
    

    function templateAdd(url){
   
     var openurl= __ctx +"/platform/bpm/task/doNext.ht";
     var newurl=url+"&taskId="+taskId;
     alert("得到了新的url:"+newurl);
 
     
			$.ligerDialog.confirm("您确定执行此操作吗？","提示",function(rtn){		
			   if(rtn){
				  $.post(newurl,function(responseText){
				  var obj=new com.hotent.form.ResultMessage(responseText);
				  var Message=obj.getMessage();
				  var taskIds=Message.split("!");
				  alert("taskId:::"+taskIds[1]);
				  alert("instarnceId:::"+taskIds[2]);					
				  alert("isTableOrTemplete:::"+taskIds[3]);					
					if(obj.isSuccess()){//成功
						$.ligerDialog.success(taskIds[0],'提示信息');
						taskId = taskIds[1];
						jQuery.openFullWindow(openurl+"?taskId="+taskId+"&instanceId="+taskIds[2]+"&isTableOrTemplete="+taskIds[3]);								
					}
					else{
						$.ligerDialog.error(obj.getMessage(),'出错了');
					}
				});					
				}
} );
}	
    function templateEdit(url,pk){
   
     var openurl= __ctx +"/platform/bpm/task/doNext.ht";
     var newurl=url+"&taskId="+taskId;
     alert("得到了新的url:"+newurl);
     alert("取到得PK:"+pk);
     
			$.ligerDialog.confirm("您确定执行此操作吗？","提示",function(rtn){		
			   if(rtn){
				  $.post(newurl,function(responseText){
				  var obj=new com.hotent.form.ResultMessage(responseText);
				  var Message=obj.getMessage();
				  var taskIds=Message.split("!");
				  alert("taskId:::"+taskIds[1]);
				  alert("instarnceId:::"+taskIds[2]);					
				  alert("isTableOrTemplete:::"+taskIds[3]);					
					if(obj.isSuccess()){//成功
						$.ligerDialog.success(taskIds[0],'提示信息');
						taskId = taskIds[1];
						jQuery.openFullWindow(openurl+"?taskId="+taskId+"&instanceId="+taskIds[2]+"&isTableOrTemplete="+taskIds[3]+"&pk="+pk);								
					}
					else{
						$.ligerDialog.error(obj.getMessage(),'出错了');
					}
				});					
				}
} );
}	

	</script>
</head>
<body>
	<div id="content">
		${html}
	</div>
</body>
</html>
