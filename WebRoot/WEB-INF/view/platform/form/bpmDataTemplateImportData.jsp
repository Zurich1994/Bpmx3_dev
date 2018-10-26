<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>业务数据模板导入</title>
<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
	window.name="win";
			
	$(function(){
		valid(showResponse);
		
		$("#btnSave").click(function(){
			var path = $('#file').val();
			
			var index1=path.lastIndexOf("."); 
			var index2=path.length;
			var extName=path.substring(index1+1,index2);//后缀名
			if(extName=='xls' || extName == 'xlsx'){
				var importType = $('input[type="radio"][name="importType"]:checked').val();
				var str = "确认新增导入数据？";
				if(importType=="update"){
					str = "确认更新导入数据？";
				}
				$.ligerDialog.confirm(str,function(btn){
					if(!btn) return;
					$.ligerDialog.waitting('正在导入中,请稍候...');
					$("#importForm").submit();	
				});
			}else{
				$.ligerDialog.warn("请选择 *.xls或.xlsx文件进行导入!");
			}
		});
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			//
			$.ligerDialog.success(obj.getMessage(),"提示信息");
			window.returnValue="/";
			window.close();
	    }else{//失败
			$.ligerDialog.closeWaitting();
	    	$.ligerDialog.error(obj.getMessage(),"提示信息");
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
	function closeWindow(){
		var a = window.parent.$(".l-dialog.l-dialog-win");
		var b = window.parent.$(".l-window-mask");
		a.remove();
		b.remove();
	}
	</script>
</head>
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义表导入</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link import" id="btnSave"><span></span>导入</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:closeWindow()"><span></span>关闭</a></div>
				
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importSave.ht" enctype="multipart/form-data">
			<div class="row">
			 	<input type="hidden" id="__displayId__" name="__displayId__" value="${__displayId__}">
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="22%">选择文件：</th>
					<td width="78%"><input type="file" size="40" name="file" id="file"/></td>						
				</tr>
				<tr>
					<th width="22%">选择类型：</th>
					<td width="78%">
					             新增导入<input type="radio" name="importType"  value="add" checked="checked" />
					        
					             更新导入<input type="radio" name="importType"  value="update" />
					</td>						
				</tr>
			</table>				
			
			</div>
	    </form>
	</div><!-- end of panel-body -->				
</body>
</html>