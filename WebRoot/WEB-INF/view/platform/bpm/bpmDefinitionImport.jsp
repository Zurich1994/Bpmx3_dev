
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>流程定义导入</title>
<%@include file="/commons/include/form.jsp" %>
<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	window.name="win";
			
	$(function(){	
		valid(showResponse);
		$("#btnSave").click(function(){
			var path = $('#xmlFile').val();
			var extNaem = path.substring(path.length-3, path.length);
			if(extNaem!='zip'){
				$.ligerDialog.warn("请选择 *.zip文件进行导入!","提示信息");
			}else{
				$("#importForm").submit();
				$.ligerDialog.waitting("导入中，请稍后……");
			}
		});		
	});
	function showResponse(responseText){
		$.ligerDialog.closeWaitting();
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			var message = obj.getMessage();
			if(message){
				message =message.replaceAll("###","\'").replaceAll("!!!","<font ").replaceAll("%%%","</font>").replaceAll("&gt;",">").replaceAll("&lt;","<");   
			}
		  	 $.ligerDialog.tipDialog('提示信息',"导入结果如下:",message,null,function(){
					//window.returnValue="/";
		  			dialog.get('sucCall')("/");
					dialog.close();
				});
	    }else{//失败
			$.ligerDialog.closeWaitting();
	    	$.ligerDialog.err('提示信息',"导入失败!",obj.getMessage());
	    }
	}
	
	function valid(showResponse){
		var options={success:showResponse};
		__valid=$("#importForm").validate({
			rules: {},
			messages: {},
			submitHandler:function(form){
				$(form).ajaxSubmit(options);
		    },
		    success: function(label) {}
		});
	}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">流程定义导入</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
				
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importXml.ht" enctype="multipart/form-data">
				<div class="row">
					 <div class="panel-detail">
						 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="22%">选择文件：</th>
								<td width="78%"><input type="file" size="40" name="xmlFile" id="xmlFile"/></td>						
							</tr>
							<tr><td colspan="2">注意：如果当前系统的流程定义与导入的流程定义 ，存在相同的流程定义 key <br>将以新版本的方式覆盖。</td></tr>
						</table>				
					</div>
				</div>
		</form> 		
	</div><!-- end of panel-body -->				
</div> 
</body>
</html>