<%--
	time:2012-01-05 12:01:21
	desc:edit the 脚本管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>选择视图</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/tableHeadFix.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSearch").click(searchTables);
			$("a.run").click(handNextClick);
			$("#tbTableList").chromatable({height:"350px",width:"100%"});
		});
		 
		function handNextClick(){
			var viewName=$("#selList").val();
			var ds=$("#dataSource").val();
		
			if(viewName==null || viewName==""){
				$.ligerDialog.warn("请先选择一个视图!");
				return;
			}
			
			$("#scriptForm").submit();
		}
		
	
		
		function searchTables(){
			var selList=$("#selList");
			var ds=$("#dataSource").val();
			var viewName=$("#viewName").val();
			var url=__ctx +"/platform/form/bpmFormTable/getViewList.ht";
			var tableObj=$("#tbTableList");
			$.ligerDialog.waitting('正在测试连接，请等待...');
			$.post(url, { ds:ds, table: viewName },function(data) {
				$.ligerDialog.closeWaitting();
				selList.empty();
				var success=data.success;
				if(success=='true'){
					var aryView=data.views;
					for(var i=0;i<aryView.length;i++){
						var v=aryView[i];
						selList.append("<option value='"+ v+"'>"+v+"</option>" );
					}
				}
				else{
					$.ligerDialog.error("出错了!");
				}
				
		    });
		}
		
		function handClick(){
			var obj=$(this);
			obj.addClass("over").siblings().removeClass("over");
			var tableName=$("td:first",obj).text();
			$("#tableName").val(tableName);
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">选择视图</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="javascript:;"><span></span>下一步</a>
					</div>
				
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="scriptForm" method="post" action="getModelByView.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">选择数据源: </th>
							<td>
								<select id="dataSource" name="dataSource">
									<option value="LOCAL">本地数据源</option>
									<c:forEach items="${dsList}" var="ds">
										<option value="${ds.alias}">${ ds.name} </option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%">查询视图: </th>
							<td>
								<input type="text" name="viewName" id="viewName">
								<a href="javascript:;" id="btnSearch" class="link search">查询</a>
								
							</td>
						</tr>
						<tr>
							<th width="20%">数据视图: </th>
							<td>
								<select id="selList" name="selList" size="10" style="width:150px;">
								</select>
							</td>
						</tr>
						
					</table>
					
				</form>
			</div>
			</div>
</div>
</body>
</html>
