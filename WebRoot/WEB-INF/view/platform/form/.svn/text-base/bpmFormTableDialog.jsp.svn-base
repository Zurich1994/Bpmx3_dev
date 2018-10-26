
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>选择自定义表</title>
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

	function selectTable(){
		var obj=$("#bpmFormTableItem tr.over");
	
		if(obj.length>0){
			var objInput=$("input",obj);
			var aryTb=objInput.val().split(",");
			parent.getTable(aryTb[0],aryTb[1]);
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
								<li><span class="label">表名:</span><input type="text" name="Q_tableName_SL"  class="inputText" size="15" value="${param['Q_tableName_SL']}"/></li>
								<li><span class="label">描述:</span><input type="text" name="Q_tableDesc_SL"  class="inputText" size="15" value="${param['Q_tableDesc_SL']}"/></li>
						</ul>		
					</form>
				</div>
			</div>
		
	    	<display:table name="bpmFormTableList" id="bpmFormTableItem" requestURI="list.ht" sort="external"  export="false"  class="table-grid">
				<display:column  title="表名" style="text-align:left;width:200px;">
					<input  type="hidden" value="${bpmFormTableItem.tableId },${bpmFormTableItem.tableName }">
					${bpmFormTableItem.tableName }
				</display:column>
				<display:column property="tableDesc" title="描述" style="text-align:left"></display:column>
			</display:table>
			<hotent:paging tableId="bpmFormTableItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTable()" ><span class="icon ok"></span><span>选择</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="dialog.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div> 
</body>
</html>


