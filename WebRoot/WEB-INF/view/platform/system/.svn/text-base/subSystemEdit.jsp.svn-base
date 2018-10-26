<%--
	time:2011-11-21 12:22:07
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>修改子系统管理</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=subSystem"></script>
<script type="text/javascript"
	src="${ctx }/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript"
	src="${ctx }/js/hotent/platform/system/IconDialog.js"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${subSystem.systemId==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#subSystemForm').submit(); 
				
			});
		});

		function selectIcon(){
			IconDialog({callback:function(src){
				$("#logo").val(src);
				$("#logoImg").attr("src",src);
				$("#logoImg").show();
			},params:"iconType=1"})
		};
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"> <c:choose>
						<c:when test="${subSystem.systemId==0}">
					添加子系统
					</c:when>
						<c:otherwise>
					修改子系统
					</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="../subSystem/list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="subSystemForm" method="post" action="save.ht">

				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">名称: <span class="required">*</span></th>
						<td><input type="text" id="sysName" name="sysName"
							value="${subSystem.sysName}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%">别名:</th>
						<td><input type="text" id="alias" name="alias"
							value="${subSystem.alias}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%">系统图标:</th>
						<td><input type="hidden" id="logo" name="logo"
							value="${subSystem.logo}" class="inputText" /> <img id="logoImg"
							alt="" src="${ctx}${subSystem.logo}"> <a
							class="link detail" href="javascript:selectIcon();">选择</a></td>
					</tr>

					<tr>
						<th width="20%">访问地址:</th>
						<td><input type="text" id="defaultUrl" name="defaultUrl"
							value="${subSystem.defaultUrl}" size="40" class="url_bpmForm inputText" /></td>
					</tr>
					<tr>
						<th width="20%">首页地址:</th>
						<td><input type="text" id="homePage" name="homePage"
							value="${subSystem.homePage}" size="40" class="url_bpmForm inputText" /></td>
					</tr>
					<tr>
						<th width="20%">允许删除:</th>
						<td><input type="radio" value="1" name="allowDel"
							<c:if test="${subSystem.allowDel eq 1}">checked="checked"</c:if>>允许
							<input type="radio" value="0" name="allowDel"
							<c:if test="${subSystem.allowDel eq 0}">checked="checked"</c:if>>不允许

						</td>
					</tr>
					<tr>
						<th width="20%">是否激活:</th>
						<td><input type="radio" value="1" name="isActive"
							<c:if test="${subSystem.isActive eq 1}">checked="checked"</c:if>>是
							<input type="radio" value="0" name="isActive"
							<c:if test="${subSystem.isActive eq 0}">checked="checked"</c:if>>否
						</td>
					</tr>
					<tr>
						<th width="20%">本地系统:</th>
						<td><input type="radio" value="1" name="isLocal"
							<c:if test="${subSystem.isLocal eq 1}">checked="checked"</c:if>>是
							<input type="radio" value="0" name="isLocal"
							<c:if test="${subSystem.isLocal eq 0}">checked="checked"</c:if>>否

						</td>
					</tr>
					<tr>
						<th width="20%">备注:</th>
						<td><textarea id="memo" name="memo" rows="5" cols="40"
								class="textarea">${subSystem.memo}</textarea></td>
					</tr>
				</table>

				<input type="hidden" name="systemId" value="${subSystem.systemId}" />
			</form>
		</div>
	</div>
</body>
</html>

