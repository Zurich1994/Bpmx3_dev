<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>索引重建管理</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery-impromptu.4.0.min.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	$(function(){
		
	});

	function editRebuildJob() {
		var $indexesAry = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		if ($indexesAry.length == 0) {
			$.ligerDialog.warn("请选择索引！");
			return false;
		} else {
			
			$.ligerDialog.open({
				width : 280,
				type : 'error',
				title : '对话框弹出演示标题',
				content : '对话框弹出演示',
				buttons : [ {
					text : '确定',
					onclick : function(){
						
					}
				}, {
					text : '取消',
					onclick : function(){
						return false;
					}
				} ]
			});

			$.ligerDialog.prompt('请输入索引重建计划名', '', false, function(yes, value) {
				if (yes) {
					if ($.trim(value) != '') {
						$('#jobName').val(value);
						alert($('#jobName').val());
						saveRebuidJob();
					} else {
						$.ligerDialog.warn("索引重建计划名不能为空");
						return false;
					}
				}
			});
		}

		function saveRebuidJob() {
			var url = __ctx + "/platform/system/bpmIndexRebuild/saveIndexRebuildJob.ht";
			$.post(url, $('#frmIndexRebuid').serialize(), function(data) {
				var obj = new com.hotent.form.ResultMessage(data);
				if (obj.isSuccess()) {
					$.ligerDialog.success(obj.getMessage(),'提示信息');
					window.close();
				} else {
					$.ligerDialog.err('出错信息',"索引重建失败",obj.getMessage());
				}
			});
		}
	}
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">索引重建管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a id="editRebuildJob" class="link add" href="javascript:;" onclick="editRebuildJob()"><span></span>创建计划</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="indexSearch" class="link search" href="javascript:;"><span></span>查询</a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-search">
				<form id="searchForm" method="post" action="getIndexList.ht?indexName=${indexName}&tableName=${tableName}">
					<ul class="row">
			           <li> <span class="label">索引名称:</span><input type="text" name="Q_jobName_S"  class="inputText" value="${param['Q_jobName_S']}"/></li>
			           <li> <span class="label">表名称:</span><input type="text" name="Q_trigName_S"  class="inputText" value="${param['Q_trigName_S']}"/></li>
					</ul>
				</form>
			</div>
	
			<div class="panel-data">
				<form id="frmIndexRebuid">
			    	<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
				   	<table id="indexList" class="table-grid table-list" cellpadding="1" cellspacing="1">
				   		<thead>
				   			<tr>
				   				<th>选择</th>
				   				<th>索引名</th>
				   				<th>表名</th>
				   				<th>索引描述</th>
				   			</tr>
				   		</thead>
				   		<tbody>
				   			<c:forEach items="${indexList}" var="index">
				   				<tr>
				   					<td width="100px"><input class="pk" name="indexpks" type="checkbox" value="${index.indexName}|${index.indexTable}"/></td>
				   					<td width="30%">${index.indexName }</td>
				   					<td width="30%">${index.indexTable }</td>
				   					<td>${index.indexComment }</td>
				   				</tr>
				   			</c:forEach>
				   		</tbody>
				   	</table>
				   	
				   	<input name="jobName" id="jobName" type="hidden" value=""/>
			   	</form>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


