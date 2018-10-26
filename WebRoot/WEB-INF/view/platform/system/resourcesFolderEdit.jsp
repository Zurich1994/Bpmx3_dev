<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<title>设置图标</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript">
		$(function(){
			$("#folderForm").ajaxForm({success:showResponse});
			$('a.add').click(function(){
				var name=$("#folderName").val();
				if(name==''){
					$.ligerDialog.warn('请填入文件名','提示信息');
				}else{
					$("#folderForm").submit();
				}
			});
		});
		
		function showResponse(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),"提示信息",function(){
					window.parent.location.reload();		
				});
		    }else{//失败
		    	$.ligerDialog.err('出错信息',"查看设置文件图标失败",obj.getMessage());
		    }
		};
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
			新建文件夹
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link add" id="dataFormSave" href="javascript:;"><span></span>创建</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="icons.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body" id="folderEdit">
		<form id="folderForm" action="createFolder.ht" method="post" >
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">父目录: </th>
				<td id="parentFoler" ></td>
			</tr>
			<tr>
				<th width="20%">文件名: </th>
				<td><input type="text" id="folderName" name="folderName" class="inputText"/></td>
			</tr>
		</table>
		<input type="hidden" id="path" name="path" value=""/>
		</form>
	</div>
</div>
</body>
</html>
