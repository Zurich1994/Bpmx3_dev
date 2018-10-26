<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerLayout.js" ></script>
<title>流水号列表</title>
<script type="text/javascript">
	$(function(){
		//$("#defLayout").ligerLayout({ topHeight: 65,bottomHeight:40,allowTopResize:false,allowBottomResize:false});
		
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
			$(this).find(':radio').attr('checked', 'checked');
		});
	})

	function selectTable(){
		var obj=$("#identityItem tr.over");
	
		if(obj.length>0){
			var objInput=$("input.pk",obj);
			var aryTb=objInput.val().split(",");
			parent.getTable(aryTb[0],aryTb[1]);
		}else{
			alert("请选择记录");
		}
	}
	 
</script>
</head>



<body>
	
	<div id="defLayout">
		<div position="top" title="选择流水号" >
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="group"><a class="link add" href="edit.ht?islist=1"><span></span>添加</a></div>
				</div>	
			</div>
			<div class="panel-search" moreHeight="50">
				<form id="searchForm" method="post" action="showlist.ht">
					<ul class="row">
							<li><span class="label">名称:</span><input type="text" name="Q_Name_SL"  class="inputText" size="15" value="${param['Q_Name_SL']}"/></li>
					</ul>		
				</form>
			</div>
		</div>
		<div position="center"  style="overflow: auto;">
	    	  	<display:table name="identityList" id="identityItem" requestURI="showlist.ht" sort="external"  export="false"  class="table-grid">
				<display:column  media="html" style="width:30px;">
					  	<input type="radio" name="tableId" value="${identityItem.id}">
						<input  type="hidden" class="pk"  value="${identityItem.alias },${identityItem.name}">
				</display:column>
				<display:column property="name" title="名称" sortable="true" sortName="name"></display:column>
				<display:column property="alias" title="别名" sortable="true" sortName="alias"></display:column>
				<display:column property="rule" title="规则" sortable="true" sortName="rule"></display:column>
			</display:table>
			<hotent:paging tableId="identityItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTable()" ><span class="icon ok"></span><span>选择</span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="parent.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div>
	</div> 
</body>

</html>