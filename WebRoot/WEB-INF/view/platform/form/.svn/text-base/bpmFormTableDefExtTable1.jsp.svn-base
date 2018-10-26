<%--
	time:2012-01-05 12:01:21
	desc:edit the 脚本管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>选择外部表</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/util/tableHeadFix.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSearch").click(searchTables);
			$("a.run").click(handNextClick);
			//$("#tbTableList").chromatable({height:"350px",width:"100%"});
		});
		 
		function handNextClick(){
			var tableName=$("#tableName").val();
			var ds=$("#dataSource").val();
			if(tableName==""){
				$.ligerDialog.warn("请先选择一个表!");
				return;
			}
			var parameter="tablename=" + tableName +"&dataSource=" + ds;
			$.get("isTableNameExternalExisted.ht",parameter, function(data) {
				if(data=="true"){
					$.ligerDialog.warn("此表定义已经添加!");
				}
				else{
					$("#scriptForm").submit();
				}
			});
		}
		
		var tdTemplate="<tr style='cursor:pointer'><td style='width:300px;'>#tbname</td><td>#comment</td></tr>";
		
		function searchTables(){
			var ds=$("#dataSource").val();
			var tbName=$("#tbName").val();
			var url=__ctx +"/platform/form/bpmFormTable/getTableList.ht";
			var tableObj=$("#tbTableList");
			$.ligerDialog.waitting('正在查询数据表，请等待...');
			$.post(url, { ds:ds, table: tbName },function(data) {
				$.ligerDialog.closeWaitting();
				$("tr:gt(0)",tableObj).remove();
				var success=data.success;
				if(success=='true'){
					var obj=data.tables;
					for(var key in obj){
						var comment=obj[key];
						var tr=tdTemplate.replace("#tbname",key).replace("#comment",comment);
						tableObj.append(tr);
					}
					$("#tbTableList tr:gt(0)").click(handClick);
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
		
		function sysDatasourceMgr(){
			var url=__ctx + "/platform/system/sysDataSource/list.ht";
			//{tabid:id,text:txt,url:url,icon:icon}
			addTabItem({tabid:"sysDatasource",text:"数据源管理",url:url})
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">选择外部表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="javascript:;"><span></span>下一步</a>
					</div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="scriptForm" method="post" action="defExtTable2.ht">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%">选择数据源: </th>
								<td width="30%">
									<select id="dataSource" name="dataSource">
										<option value="LOCAL">本地数据源</option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name} </option>
										</c:forEach>
									</select>
									&nbsp;
									<a href="javascript:;" onclick="sysDatasourceMgr();">数据源管理...</a>
								</td>
								<th width="20%">查询表: </th>
								<td width="30%">
									<input type="text" name="tbName" id="tbName" class="inputText"> &nbsp; <a href="javascript:;" id="btnSearch" class="link search">查询</a>
								</td>
							</tr>
						</table>
						<br/>
						<table id="tbTableList" class="table-grid table-list" width="80%" >
							<thead>
								<th style="text-align: center;width:300px;">表名</th><th style="text-align: center;">表注释</th>
							</thead>
							<tr>
								<td colspan="2">请根据数据源来查询外部表并且选择其中一张表</td>
							</tr>
						</table>
						<input type="hidden" value="" name="tableName" id="tableName"/>
				</form>
		</div>
</div>
</body>
</html>
