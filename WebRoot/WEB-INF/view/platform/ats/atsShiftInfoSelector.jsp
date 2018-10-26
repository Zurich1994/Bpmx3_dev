
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择班次定义</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var isSingle="${isSingle}";
	$(function(){
		 $("#atsShiftInfoItem>tbody").find("tr").bind('click', function() {
			 if(isSingle!='true'){
				var ch=$(this).find(":checkbox");
				window.parent.selectMulti(ch);
			}
		});	
		 $("#chkall").bind("click",function(){
		        var checkAll=false;
				if($(this).attr("checked")){
					checkAll=true;	
				}
				var checkboxs=$(":checkbox",$("#atsShiftInfoItem>tbody"));
				checkboxs.each(function(){
					if(checkAll){
						window.parent.selectMulti(this);
					}
				});
		 });
	
	});
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>

	<div  class="panel-top">
		<div class="panel-search">
			<form id="searchForm" method="post" action="${ctx}/platform/ats/atsShiftInfo/selector.ht" >
				<ul class="row">
					<input type="hidden" name="isSingle" value="${isSingle }">
					<li><span class="label" >名称:</span><input size="14" type="text" name="Q_name_SL"  class="inputText" style="width:60%;" value="${param['Q_fullname_SL']}"/>
					&nbsp;<a href="javascript:;" onclick="$('#searchForm').submit()" class='button'><span>查询</span></a></li>
				</ul>
			</form>
		</div>
	</div>
		<c:choose>
			<c:when test="${isSingle==false}">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" name="chkall" />
				</c:set>
			</c:when>
			<c:otherwise>
				<c:set var="checkAll" value="选择"/>
			</c:otherwise>
		</c:choose>
	    <display:table name="atsShiftInfoList" id="atsShiftInfoItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
			<display:column title="${checkAll}" media="html" style="width:30px;">
				<c:choose>
					<c:when test="${isSingle==false}">
						<input type="checkbox" class="pk" name="data" value="${atsShiftInfoItem.id}#${atsShiftInfoItem.name}#${atsShiftInfoItem.code}#${atsShiftInfoItem.shiftTime}">
					</c:when>
					<c:otherwise>
						<input type="radio" class="pk" name="data" value="${atsShiftInfoItem.id}#${atsShiftInfoItem.name}#${atsShiftInfoItem.code}#${atsShiftInfoItem.shiftTime}">
			
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="code" title="编码" sortable="true" sortName="CODE" maxLength="80"></display:column>
			<display:column property="name" title="名称" sortable="true" sortName="NAME" maxLength="80"></display:column>
		</display:table>
		<hotent:paging tableId="atsShiftInfoItem" showExplain="false"/>
</body>
</html>


