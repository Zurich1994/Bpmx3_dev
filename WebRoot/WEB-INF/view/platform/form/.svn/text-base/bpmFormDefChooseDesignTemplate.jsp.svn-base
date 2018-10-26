
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择模板</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
<script type="text/javascript">
	var dialog=frameElement.dialog;
	var temps=${temps};
	$(function(){
		if(!temps)return;
		var temp,html;
		while(temp=temps.pop()){
			html='<option value="'+temp.alias+'" desc="'+temp.templateDesc+'">'+temp.name+'</option>';
			$("#tempname").append(html);
		}
	});
	
	$(function(){
		$("#dataFormSave").click(function(){
			openEdit();
		});		
	});

	function openEdit(){
		var tempalias=$("#tempname").val();		
		$("#tempalias").val(tempalias);
		var params=$("#frmDefInfo").serialize();
		var url='designEdit.ht?' + params;
		jQuery.openFullWindow(url);
		frameElement.dialog.get('pwin').closeWin();
	};
	
	function backPre(){
		var url=__ctx + '/platform/form/bpmFormDef/gatherInfo.ht?categoryId=${categoryId}&subject=${subject}&formDesc=${formDesc}&designType=1';
		window.location.href =url;
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">选择模版</span>
			</div>	
			<c:if test="${isSimple==0}">		
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="javascript:;"><span></span>下一步</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" onclick="backPre();"><span></span>返回</a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-detail">
			<form id="frmDefInfo">
				
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="30%">模板:</th>
						<td>
							<select id="tempname">							
							</select>
						</td>
					</tr>
				</table>
				
				<input type="hidden" name="tempalias" id="tempalias" />
				<input type="hidden" name="categoryId" value="${categoryId}"/>
				<input type="hidden" name="subject" value="${subject}"/>
				<input type="hidden" name="formKey" value="${formKey}"/>
				<input type="hidden" name="formDesc" value="${formDesc}"/>
			</form>
		</div>
	</div>
</body>
</html>


