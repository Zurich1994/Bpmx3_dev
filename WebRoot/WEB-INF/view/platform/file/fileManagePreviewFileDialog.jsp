<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<% 
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>

<html>
<head>
<title>文件上传管理</title>
<%@include file="/commons/include/form.jsp"%>
	
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>

<script type="text/javascript">
	$().ready(function (){
		getOffice();
	});
	// 获取Office 控件
	function getOffice(){
		OfficePlugin.init();
		var path= "${ctx}/platform/file/fileManage/previewFile.ht?fileId=${fileId}";
		try{
			OfficePlugin.officeObjs[0].controlObj.OpenFromURL(path);
			OfficePlugin.officeObjs[0].setFileReadOnly(true);
		}
		catch(err){
			alert(err);
		}finally{
		}  
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">文件预览</span>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0"
				border="0">
				<tr>
					<td>
						<input type="hidden" class="hidden" name="${fileId}" lablename="filePreview" controltype="office" right="r" doctype="${ext }" />
					</td>
				</tr>
			</table> 
		</div>
	</div>
</body>
</html>
