
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>表单验证规则导入</title>
<%@include file="/commons/include/form.jsp" %>

	<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
	
	window.name="win";
			
	$(function(){
		valid(showResponse);
		
		$("#btnSave").click(function(){
			var path = $('#xmlFile').val();
			var extNaem = path.substring(path.length-3, path.length);
			if(extNaem!='xml'){
				$.ligerDialog.warn("请选择 *.xml文件进行导入!");
			}else{

				$("#importForm").submit();
			}
		});			
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success(obj.getMessage(),'提示信息',function(){
				//window.returnValue=obj.getMessage();
				dialog.get("sucCall")(obj.getMessage());
				dialog.close();
			});
	    }else{//失败
	    	$.ligerDialog.err('出错信息',"表单验证规则导入失败",obj.getMessage());
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
				<span class="tbar-label">表单验证规则导入</span>
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
		<div class="panel-search">
			<form id="importForm" name="importForm" method="post" target="win" action="importXml.ht" enctype="multipart/form-data">
				<div class="row">
				 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="22%">选择文件：</th>
						<td width="78%"><input type="file" size="40" name="xmlFile" id="xmlFile"/></td>						
					</tr>
				</table>				
				</div>
		    </form>
		</div>    		
	</div><!-- end of panel-body -->				
</div> 
</body>
</html>