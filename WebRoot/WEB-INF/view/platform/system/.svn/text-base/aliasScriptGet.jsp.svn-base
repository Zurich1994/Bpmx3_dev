
<%--
	time:2013-12-19 11:26:03
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/get.jsp"%>
<title>别名脚本明细</title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htDicCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	$(function(){
		var scriptType = '${aliasScript.scriptType}';
		//脚本类型（自定义：custom）
		if(scriptType=='custom'){
			$('.customTbody').css("display","block");
			$("tr[name='defaultTbody']").css("display","none");
			$("tr[name='customTbody']").removeAttr("style");;
		}else{
			$("tr[name='customTbody']").css("display","none");
			$("tr[name='defaultTbody']").removeAttr("style");;
		}
		
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">别名脚本明细</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">脚本别名:</th>
				<td>${aliasScript.aliasName}</td>
			</tr>
			<tr>
				<th width="20%">脚本描述:</th>
				<td>${aliasScript.aliasDesc}</td>
			</tr>	
			<tr>
				<th width="20%">脚本类型:</th>
				<td>
				    <c:if test="${aliasScript.scriptType eq 'default'}">系统默认</c:if>
					<c:if test="${aliasScript.scriptType eq 'custom'}">自定义</c:if>
				</td>
			</tr>
						
			<tr name='defaultTbody' >
				<th width="20%">脚本所在类的类名:</th>
				<td>${aliasScript.className}</td>
			</tr>
			<tr name='defaultTbody' >
				<th width="20%">调用类的对象名:</th>
				<td>${aliasScript.classInsName}</td>
			</tr>
			<tr name='defaultTbody' >
				<th width="20%">调用方法:</th>
				<td>${aliasScript.methodName}</td>
			</tr>
			<tr name='defaultTbody' >
				<th width="20%">调用的方法的描叙::</th>
				<td>${aliasScript.methodDesc}</td>
			</tr>
			<tr name='defaultTbody' >
				<th width="20%">方法返回类型:</th>
				<td>${aliasScript.returnType}</td>
			</tr>
			<tr name='defaultTbody' >
				<th width="20%">参数信息:</th>
				<td>${aliasScript.argument}</td>
			</tr>
		
			<tr name='customTbody' >
				<th width="20%">脚本内容:</th>
				<td>
				  <textarea type="text" id="scriptComten" codemirror="true"
										mirrorheight="400px" name="scriptComten" rows="30" cols="200">${aliasScript.scriptComten}</textarea>
				</td>
			</tr>

			<tr>
				<th width="20%">是否启用:</th>
				<td>
				    <c:if test="${aliasScript.enable eq 0}">是</c:if>
				    <c:if test="${aliasScript.enable eq 1}">否</c:if>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

