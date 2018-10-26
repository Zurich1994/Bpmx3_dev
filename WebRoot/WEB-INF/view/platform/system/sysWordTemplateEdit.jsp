<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑WORD表单模板</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<script type="text/javascript">
	
	$(function() {
   	 	$('#frmWorkFlow').ajaxForm({success:showResponse }); 
   	 	$('#btnAdd').click(function(){
   	 		var subSql = $('#subTableTemplate').find('tr').clone();
   	 		$('#sqlTable').append(subSql);
   	 	});
   	 	$('#sqlTable').delegate('.delSubTableSql', 'click', function(){
   	 		$(this).closest('tr').remove();
   	 	});
		initSqlArea();
   	 	$('#type').change(function(){
   	 		typeChange();
   	 	});
   	 	typeChange();
	});
	
	function initSqlArea(){
		var sqlJson = '${sysWordTemplate.sql}';
		if(!sqlJson) return;
		sqlJson = $.parseJSON(sqlJson);
		$('#main').val(sqlJson.main);
		var subTable = sqlJson.subTable;
		for(var key in subTable){
			$('#btnAdd').trigger('click');
			var sub = $('#sqlTable').find('tr.subTableSqlTr:last');
			sub.find('.subTableName').val(key);
			sub.find('.subTableSql').val(subTable[key]);
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
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){
			var id = obj.getMessage();
			$('#id').val(id);
			if(isNext) {
				var url = 'editTemplate.ht?id='+id;
				window.location.href = url.getNewUrl();
			}else {
				$.ligerDialog.confirm("保存成功,是否继续操作", "提示信息", function(rtn) {
					if (!rtn) {
						window.opener.location.reload();
						window.close();
					}
				});
			}
		}else{
			$.ligerDialog.error(obj.getMessage(), '出错信息');
		}
	}
	
	var isNext = false;// 是否是下一步，在showResponse中使用
	function submitData(isDoNext) {
		var type = $('#type').val();
		var valid = $("#frmWorkFlow").form().valid();
		if(!valid) return false;
		setSqlField(type);
		isNext = isDoNext;
		$('#frmWorkFlow').submit();
	}
	
	// 如果的SQL类型，则将SQL拼装成JSON放到表单的隐藏字段中
	function setSqlField(type){
		if(type!='1') return '';
		var sqlObj = {}, subTable = {};
		var main = $('#main').val();
		if($.isEmpty(main)) return '';
		sqlObj.main = main;
		$('#sqlTable .subTableSqlTr').each(function(){
			var subTableName = $(this).find('.subTableName').val();
			var subSql = $(this).find('.subTableSql').val();
			subTable[subTableName] = subSql ;
		});
		sqlObj.subTable = subTable;
		var subTableSql = JSON2.stringify(sqlObj);
		$('#sql').val(subTableSql);
		return subTableSql;
	}
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};
	function selectTable(){
		var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		FormTableDialog({callBack:callBack});
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
					<a class="link save" id="dataFormSave" href="javascript:;" onclick="submitData();"><span></span>保存</a>
				</div>
				<c:if test="${not empty sysWordTemplate.fileId}">
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link preview" id="btnPreView" href="javascript:;" onclick="preview('${sysWordTemplate.alias}');"><span></span>预览</a>
				</div>
				</c:if>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link  del" href="javascript:window.onbeforeunload = null;window.close()"><span></span>关闭</a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link run" href="javascript:;" onclick="submitData(true);"><span></span>下一步</a>
				</div>
				<a href="javascript:;" class="tipinfo"><span>编写SQL时，可以使用__PK__代替业务主键；<br>SQL中的别名，可以是字母下划线的组合，并且需要确保在当前记录中是唯一的。</span></a>
			</div>
		</div>
	</div>
	<div  class="panel-body">
		<form id="frmWorkFlow" method="post" action="save.ht">
			<input id="id" type="hidden" name="id" value="${sysWordTemplate.id}" />
			<input id="fileId" type="hidden" name="fileId" value="${sysWordTemplate.fileId}" />
			<div class="panel-nav">
				<table cellpadding="0" cellspacing="0" border="0"  class="table-detail">
					<tr>
						<th width="200px">名称:&nbsp;</th>
						<td><input id="name" type="text" name="name" validate="{required:true}" value="${sysWordTemplate.name}" class="inputText"/></td>
					</tr>
					<tr>
						<th>别名:&nbsp;</th>
						<td><input id="alias" type="text" name="alias" validate="{required:true}" value="${sysWordTemplate.alias}" class="inputText"/></td>
					</tr>
					<tr>
						<th>类型:&nbsp;</th>
						<td>
						<select name="type" id="type" class="inputText">
							<option value="0"  <c:if test="${sysWordTemplate.type == 0}">selected="selected"</c:if>>自定义表</option>
							<option value="1" <c:if test="${sysWordTemplate.type == 1}">selected="selected"</c:if>>SQL</option>
						</select>
						<input type="hidden" name="sql" id="sql" value="${sysWordTemplate.sql}">
						</td>
					</tr>
					<tr class="formOption">
						<th>表:&nbsp;</th>
						<td>
							<input type="text" id="tableName" class="inputText" name="tableName" value="${sysWordTemplate.tableName}" readonly="readonly">
							<input type="hidden" id="tableId" name="tableId" value="${sysWordTemplate.tableId}" validate="{required:true}">
							<a href='#' class='link search'  onclick="selectTable()" ></a>
							<a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选</span></a>
						</td>
					</tr>
					<tr class="sqlOption">
						<th>数据源别名:&nbsp;</th>
						<td>
						<select name="dsAlias" id="dsAlias" class="inputText">
							<option value="LOCAL">本地数据源</option>
							<c:forEach items="${dsList}" var="ds">
								<option value="${ds.alias}" <c:if test="${sysWordTemplate.dsAlias eq ds.alias}">selected="selected"</c:if>>${ds.name}</option>
							</c:forEach>
						</select>
						</td>
					</tr>
					<tr class="sqlOption">
						<th>SQL:&nbsp;</th>
						<td>
							<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group">
									<a id="btnAdd" class="link add"><span></span>添加</a>
								</div>
							</div>
							</div>
							
							<table cellpadding="0" cellspacing="0" border="0"  class="table-detail" id="sqlTable">
								<tr>
									<th style="text-align: left;padding-left: 5px;width:120px;">别名:</th>
									<th style="text-align: left;padding-left: 10px;">SQL语句</th>
									<th style="text-align: center;width:65px;">操作</th>
								</tr>
								<tr>
									<td>main</td>
									<td><textarea style="width:99%;height:100px;" id="main" validate="{required:true}"></textarea></td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<table class="hidden" id="subTableTemplate">
	<tr class="subTableSqlTr">
		<td><input type="text" class="inputText subTableName" validate="{required:true,variable:true}" value=""></td>
		<td><textarea style="width:99%;height:100px;" class="subTableSql" validate="{required:true}"></textarea></td>
		<td><a id="btnDel" class="link del delSubTableSql"><span></span>删除</a></td>
	</tr>
	</table>
	<jsp:include page="sysWordTemplateInc.jsp"></jsp:include>
</body>
</html>

