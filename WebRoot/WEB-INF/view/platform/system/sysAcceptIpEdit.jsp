<%--
	time:2012-02-20 17:35:46
	desc:edit the 可访问地址
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 可访问地址</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysAcceptIp"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				var sIp=$("#startIp").val();
				var eIp=$("#endIp").val();
				if(!isIP(sIp)||!isIP(eIp)){
					$.ligerDialog.confirm('IP地址填写有误，不能保存！','提示信息');
					return false;
				}
				return true;
			} 
			if(${sysAcceptIp.acceptId ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#sysAcceptIpForm').submit(); 
			});
		});

		function isIP(strIP) {
			if (strIP==null||strIP=="") return false;
			var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式
			if(re.test(strIP))
			{
			if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;
			}
			return false;
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysAcceptIp.acceptId !=null }">
			            <span class="tbar-label">编辑可访问地址</span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label">添加可访问地址</span>
			        </c:otherwise>
			    </c:choose>					
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
				<form id="sysAcceptIpForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">标题:  <span class="required">*</span></th>
							<td><input type="text" id="title" name="title" value="${sysAcceptIp.title}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">开始地址: </th>
							<td><input type="text" id="startIp" name="startIp" value="${sysAcceptIp.startIp}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%">结束地址: </th>
							<td><input type="text" id="endIp" name="endIp" value="${sysAcceptIp.endIp}"  class="inputText"/></td>
						</tr>						
						<tr>
							<th width="20%">备注: </th>
							<td><input type="text" id="remark" name="remark" value="${sysAcceptIp.remark}"  class="inputText"/></td>
						</tr>
					</table>
					
					<input type="hidden" name="acceptId" value="${sysAcceptIp.acceptId}" />
				</form>
		</div>
</div>
</body>
</html>
