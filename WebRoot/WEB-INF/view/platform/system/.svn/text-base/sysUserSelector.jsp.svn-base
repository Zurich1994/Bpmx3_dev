<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>用户列表</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var isSingle='${isSingle}';
	forbidF5("Chrome");//禁止刷新页面
	$(function(){
		$("#sysUserItem").find("tr").bind('click', function() {
			if(isSingle=='true'){
				var rad=$(this).find('input[name=userData]:radio');
				rad.attr("checked","checked");
			}else{
				var ch=$(this).find(":checkbox[name='userData']");
				window.parent.selectMulti(ch);
			}
		});
	});
</script>
</head>
<body style="overflow-x: hidden;overflow-y: auto;">
<div  class="panel-top">
	<div class="panel-search">
		<form id="searchForm" method="post" action="${ctx}/platform/system/sysUser/selector.ht" >
			<ul class="row">
				<input type="hidden" name="isSingle" value="${isSingle }">
				<input type="hidden" name="type" value="${type }">
				<input type="hidden" name="typeVal" value="${typeVal }">
				<li><span class="label" >姓名:</span><input size="14" type="text" name="Q_fullname_SL"  class="inputText" style="width:60%;" value="${param['Q_fullname_SL']}"/>
				&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
			</ul>
		</form>
	</div>
</div>
   	<c:if test="${isSingle==false}">
    	<c:set var="checkAll">
			<input onclick="window.parent.selectAll(this);" type="checkbox" />
		</c:set>
	</c:if>
	<display:table  name="sysUserList" id="sysUserItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
		<display:column title="${checkAll}" media="html" style="width:30px;">
				<c:choose>
					<c:when test="${isSingle==false}">
						<input onchange="window.parent.selectMulti(this);" type="checkbox" class="pk" name="userData" value="${sysUserItem.userId}#${sysUserItem.account}#${sysUserItem.fullname}#${sysUserItem.email}#${sysUserItem.mobile}#${sysUserItem.orgName}">
					</c:when>
					<c:otherwise>
						<input type="radio" class="pk" name="userData" value="${sysUserItem.userId}#${sysUserItem.account}#${sysUserItem.fullname}#${sysUserItem.email}#${sysUserItem.mobile}#${sysUserItem.orgName}">
					</c:otherwise>
				</c:choose>
		</display:column>
		<display:column  style="width:60px;" property="fullname" title="姓名" sortable="true" sortName="fullname"></display:column>
		<display:column  title="所属组织" sortable="false" >
			<c:choose>
				<c:when test="${sysUserItem.orgName==''}"><span class="red">未设置</span></c:when>
				<c:otherwise>${sysUserItem.orgName}</c:otherwise>
			</c:choose>
		</display:column>
	</display:table>
	<hotent:paging tableId="sysUserItem" showExplain="false"/>
</body>
</html>


