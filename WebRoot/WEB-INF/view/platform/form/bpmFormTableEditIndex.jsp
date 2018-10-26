
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>索引管理</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	function editIndex(tableId, tableName, indexName) {
		var url = __ctx + "/platform/form/bpmFormTable/indexDialog.ht?isAdd=0&" + "tableId=" + tableId + "&tableName=" + encodeURIComponent(tableName) + "&indexName=" + encodeURIComponent(indexName);
		var winArgs = "dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=0;resizable=1;";
		url = url.getNewUrl();
		/* var rtn = window.showModalDialog(url, "", winArgs);
		window.location.reload(true); */
		
		/*KILLDIALOG*/
		DialogUtil.open({
			height:600,
			width: 800,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				window.location.reload(true);
			}
		});
	}
	function addIndex() {
		var tableId = '${tableId}';
		var tableName = '${table.tableName}';
		var url = __ctx + "/platform/form/bpmFormTable/indexDialog.ht?isAdd=1&" + "tableId=" + tableId + "&tableName=" + encodeURIComponent(tableName);
		var winArgs = "dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=0;resizable=1;";
		url = url.getNewUrl();
		/* var rtn = window.showModalDialog(url, "", winArgs);
		window.location.reload(true); */
		
		DialogUtil.open({
			height:600,
			width: 800,
			title : '',
			url: url, 
			isResize: true,
			//自定义参数
			sucCall:function(rtn){
				window.location.reload(true);
			}
		});
	}

</script>
</head>
<body>
	<div class="panel">

		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义表索引管理</span>
			</div>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a onclick="addIndex()" class="link add"><span></span>添加</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="list.ht"><span></span>返回</a>
				</div>
			</div>
		</div>
		<div class="panel-data">
			<form id="frmIndexes">
				<table cellpadding="0" cellspacing="0" border="0" style="" class="table-grid">
					<tr class="even">
						<td>表名：${table.tableName }&nbsp;&nbsp;&nbsp;描述：${table.tableDesc }</td>
					</tr>
				</table>
				<display:table name="tableIndexes" id="index" requestURI="editIndex.ht" sort="external" cellpadding="1" cellspacing="1" export="false" class="table-grid">
					<display:column property="indexName" title="索引名" style="text-align:left"></display:column>
					<display:column property="indexType" title="类型" style="text-align:left"></display:column>
					<display:column title="字段" style="text-align:left">
						<c:forEach items="${index.indexFields}" var="field">
							${field }<br />
						</c:forEach>
					</display:column>
					<display:column property="indexStatus" title="状态" style="text-align:left"></display:column>
					<display:column title="管理" media="html" style="text-align:left;">
						<c:if test="${index.pkIndex}">
						
						</c:if>
						<c:choose>
							<c:when test="${index.pkIndex}">
								<font color="blue">主键索引</font> 
							</c:when>
							<c:otherwise>
								<a href="javascript:;" class="link edit" onclick="editIndex('${tableId }','${table.tableName}','${index.indexName}')">编辑</a>
								<a href="delIndex.ht?tableName=${table.tableName}&indexName=${index.indexName}" class="link del">删除</a>
							</c:otherwise>
						</c:choose>
					</display:column>
				</display:table>
			</form>
		</div>
	</div>
</body>
</html>


