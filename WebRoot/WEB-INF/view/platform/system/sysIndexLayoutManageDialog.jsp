
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择模版</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript">
	var dialog = parent.frameElement.dialog; //调用页面的dialog对象(ligerui对象)、
	$(function(){
		//var height=$(".panel-top").height();
		$("#centerLayout").height($(window).height()-50);
		//$("#defLayout").ligerLayout({height:'100%', bottomHeight:40,allowTopResize:false,allowBottomResize:false});
		
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
		});
	})

	function selectTemplate(){
		var obj=$("#sysIndexLayoutManageItem tr.over");
	
		if(obj.length>0){
			var objInput=$("input",obj);
			parent.selectTemplate(objInput.val());
		}
	}
	 
</script>
<style type="text/css">
	div.bottom{text-align: center;padding-top: 10px;}
	body {overflow: hidden;}
</style>
</head>
<body>
	<div id="defLayout">
		<div id="centerLayout" position="center"  style="overflow: auto;">
			<div class="panel-top" usesclflw="true">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					</div>	
				</div>
				<div class="panel-search">
					<form id="searchForm" method="post" action="dialog.ht">
						<ul class="row">
								<span class="label">布局名称:</span><input type="text" name="Q_name_SL"  class="inputText" />
								<span class="label">布局描述:</span><input type="text" name="Q_memo_SL"  class="inputText" />	
						</ul>		
					</form>
				</div>
			</div>
		
	    	 <display:table name="sysIndexLayoutManageList" id="sysIndexLayoutManageItem" requestURI="dialog.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
			
				<display:column title="布局名称" sortable="true" sortName="NAME" maxLength="80">
						${sysIndexLayoutManageItem.name }
							<input  type="hidden" value="${fn:escapeXml(sysIndexLayoutManageItem.designHtml )}">
				</display:column>
				<display:column property="memo" title="布局描述" sortable="true" sortName="MEMO" maxLength="80"></display:column>
				<display:column title="是否是默认" sortable="true" sortName="IS_DEF">
						<c:choose>
							<c:when test="${sysIndexLayoutManageItem.isDef==1}">
								<span class="red">是</span>
							</c:when>
							<c:otherwise>
								<span class="green">否</span>
							</c:otherwise>
						</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysIndexLayoutManageItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTemplate()" ><span class="icon ok"></span><span>选择</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div> 
</body>
</html>


