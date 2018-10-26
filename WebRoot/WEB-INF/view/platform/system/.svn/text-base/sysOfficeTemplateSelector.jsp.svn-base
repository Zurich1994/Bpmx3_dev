<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统模版管理</title>
<%@include file="/commons/include/get.jsp" %>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}
</style>	
<script type="text/javascript">
$(function(){
	$("#defLayout").ligerLayout({ topHeight: 110,bottomHeight:40,allowTopResize:false,allowBottomResize:false});
	
	$("tr.odd,tr.even").unbind("hover");
	$("tr.odd,tr.even").click(function(){
		var obj=$(this);
		$("input[name='rtn']",obj).attr("checked","checked");
		$(this).siblings().removeClass("over").end().addClass("over");
	});

});

function selectTemplate(){
	var obj=$("input:radio[name='rtn']:checked");
	if(obj.length==0){
		$.ligerDialog.warn("请选择一个模版!");
		return;
	}
	var aryRtn=obj.val().split(",");
	parent.getTemplate(aryRtn[0],aryRtn[1],aryRtn[2]);
}
</script>
</head>
<body>
	<div id="defLayout">
		<div position="top" title="选择自定义表" >
		<div class="hide-panel">
			<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">系统模版管理列表</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
						
						</div>	
					</div>
					<div class="panel-search">
						<form id="searchForm" method="post" action="selector.ht?templatetype=${templatetype}">
							<ul class="row">
								<li><span class="label">主题:</span><input type="text" name="Q_subject_S"  class="inputText" value="${param['Q_subject_S']}"/></li>
								<!-- <li><span class="label">模版类型:</span>
								<select name="Q_templatetype_S">
									<option value="">请选择</option>
									<option value="1">普通模版</option>
									<option value="2">套红模版</option>
								</select> </li> -->
							</ul>
						</form>
					</div>
			</div>
		</div>
		</div>
		<div position="center"  style="overflow: auto;">
	    	 <display:table name="sysOfficeTemplateList" id="sysOfficeTemplateItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column  media="html" style="width:30px;">
					  <input type="radio" class="rtn" name="rtn" value="${sysOfficeTemplateItem.id},${sysOfficeTemplateItem.subject},${sysOfficeTemplateItem.path}">
				</display:column>
				<display:column property="subject" title="主题" sortable="true" sortName="subject"></display:column>
				<display:column title="模版类型" sortable="true" sortName="templatetype">
					<c:choose>
						<c:when test="${sysOfficeTemplateItem.templatetype==1 }">普通模版</c:when>
						<c:otherwise>
							套红模版
						</c:otherwise>
					</c:choose>
				</display:column>
				
			</display:table>
			<hotent:paging tableId="sysOfficeTemplateItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTemplate()" ><span class="icon ok"></span><span>选择</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="parent.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div> 

			
</body>
</html>


