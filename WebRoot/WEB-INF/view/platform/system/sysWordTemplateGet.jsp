<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>WORD表单模板明细</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficePlugin.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/OfficeControl.js"></script>
<style type="text/css">
	.panel-body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	var controlObj;
	
	$(window).bind("load",function(){
		//Office控件初始化。
		OfficePlugin.init();
		controlObj = OfficePlugin.officeObjs[0].controlObj;
	});
	
	$(function() {
		//验证代码
		var winHeight = $(window).height()-120;
		$("#frmDefLayout").ligerLayout({leftWidth : 200,height:winHeight,onHeightChanged:function(layoutHeight, diffHeight, middleHeight){
		}});
		
		initSqlArea();
   	 	typeChange();
	});
	
	function initSqlArea(){
		var sqlJson = '${sysWordTemplate.sql}';
		if(!sqlJson) return;
		sqlJson = $.parseJSON(sqlJson);
		$('#main').append(sqlJson.main);
		var subTable = sqlJson.subTable;
		for(var key in subTable){
			var subSql = $('#subTableTemplate').find('tr').clone();
   	 		$('#sqlTable').append(subSql);
			subSql.find('.subTableName').append(key);
			subSql.find('.subTableSql').append(subTable[key]);
		}
	}
	
	function typeChange(obj){
		var value = $('#type').val();
 		if(!value || value=='0'){
 			$('.sqlOption').hide();
 			$('.formOption').show();
 		}else {
 			$('.sqlOption').show();
 			$('.formOption').hide();
 		}
	}
	
</script>
</head>
<body style="overflow:hidden">
	<div>
		<div class="tbar-title">
			<span class="tbar-label">在线表单编辑</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link  del" href="javascript:window.onbeforeunload = null;window.close()"><span></span>关闭</a>
				</div>
			</div>
		</div>
	</div>
	<div  class="panel-body">
		<form id="frmWorkFlow" method="post" action="save.ht">
			<input id="tableId" type="hidden" name="tableId" value="${sysWordTemplate.tableId}" />
			<input id="id" type="hidden" name="id" value="${sysWordTemplate.id}" />
			<div class="panel-nav">
				<table cellpadding="0" cellspacing="0" border="0"  class="table-detail">
					<tr>
						<th width="200px">名称:&nbsp;</th>
						<td>${sysWordTemplate.name}</td>
					</tr>
					<tr>
						<th>别名:&nbsp;</th>
						<td>${sysWordTemplate.alias}</td>
					</tr>
					<tr>
						<th>类型:&nbsp;</th>
						<td>
						<input type="hidden" id="type" value="${sysWordTemplate.type}">
						<c:choose>
							<c:when test="${sysWordTemplate.type == 0}">自定义表</c:when>
							<c:otherwise>SQL</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr class="formOption">
						<th>表:&nbsp;</th>
						<td>${sysWordTemplate.tableName}</td>
					</tr>
					<tr class="sqlOption">
						<th>数据源别名:&nbsp;</th>
						<td>
						<c:choose>
							<c:when test="${sysWordTemplate.dsAlias == 'LOCAL'}">本地数据源</c:when>
							<c:otherwise>
							<c:forEach items="${dsList}" var="ds">
								<c:if test="${sysWordTemplate.dsAlias eq ds.alias}">${ds.name}</c:if>
							</c:forEach>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr class="sqlOption">
						<th>SQL:&nbsp;</th>
						<td>
							<table cellpadding="0" cellspacing="0" border="0"  class="table-detail" id="sqlTable">
								<tr>
									<th style="text-align: left;padding-left: 5px;width:120px;">别名:</th>
									<th style="text-align: left;padding-left: 10px;">SQL语句</th>
								</tr>
								<tr style="line-height: 30px;">
									<td>main</td>
									<td id="main"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div id="tab" class="panel-nav">
				<div id="frmDefLayout">
					<div id="wordEditor" position="center"  style="overflow:hidden;height:100%;">
						<input type="hidden" name="fileId" controltype="office"  value="${sysWordTemplate.fileId}" style="height:100%;" right="w"/>
					</div>
				</div>
			</div>
		</form>
	</div>
	<table class="hidden" id="subTableTemplate">
	<tr class="subTableSqlTr" style="line-height: 30px;">
		<td class="subTableName"></td>
		<td class="subTableSql"></td>
	</tr>
	</table>
</body>
</html>

