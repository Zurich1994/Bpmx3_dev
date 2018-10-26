<%--
	time:2012-03-10 10:18:37
	desc:edit the 系统分类键定义
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 系统分类键定义</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysTypeKey"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysTypeKey.typeId==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#sysTypeKeyForm').submit(); 
			});
			
			$("#typeName").blur(function(){
				var obj=$(this);
				autoPingin(obj);
			});
		});
		
		function autoPingin(obj){
			var value=obj.val();
			Share.getPingyin({
				input:value,
				postCallback:function(data){
					$("#typeKey").val(data.output);
				}
			});
		}
		
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
					<c:choose>
						<c:when test="${sysTypeKey.typeId==0}">
							添加系统分类键定义
						</c:when>
						<c:otherwise>
							编辑系统分类键定义   
						</c:otherwise>
					</c:choose>			
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysTypeKeyForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						
						<tr>
							<th width="20%">分类名称:  <span class="required">*</span></th>
							<td><input type="text" id="typeName" name="typeName" value="${sysTypeKey.typeName}"  class="inputText" /></td>
						</tr>
						<tr>
							<th width="20%">分类键值:  <span class="required">*</span></th>
							<td>
								<c:choose>
									<c:when test='${sysTypeKey.flag == null||sysTypeKey.flag ==0}'>
										<input type="text" id="typeKey" name="typeKey" value="${sysTypeKey.typeKey}"  class="inputText"/>
									</c:when>
									<c:otherwise>
										<input type="text" id="typeKey" name="typeKey" readonly="readonly" value="${sysTypeKey.typeKey}"  class="inputText"/>
									</c:otherwise>
								</c:choose>
                            </td>
						</tr>
						<tr>
							<th width="20%">结构: </th>
							<td>
							<c:choose>
								<c:when test='${sysTypeKey.typeId == null||sysTypeKey.typeId ==0}'>
									<input type="radio" name="type" value="0"  <c:if test="${sysTypeKey.type==0}"> checked="checked" </c:if> />平铺结构
									<input type="radio" name="type" value="1" <c:if test="${sysTypeKey.type==1}"> checked="checked" </c:if> />树形结构
								</c:when>
								<c:when test='${sysTypeKey.type==0}'>
									平铺结构
								</c:when>
								<c:otherwise>
									树形结构
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
					</table>
					<input type="hidden" id="sn" name="sn" value="${sysTypeKey.sn}"  />
					<input type="hidden" name="typeId" value="${sysTypeKey.typeId}" />
				</form>
			</div>
		</div>
</div>
</body>
</html>
