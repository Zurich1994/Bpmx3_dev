<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>设置脚本</title>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" >
<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/mode/sql/sql.js"></script>
<style type="text/css">
	.CodeMirror{
		font-size:medium;
		_position:relative;
	}
</style>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	var winArgs =null;
	var editor=null;
	
	$(function(){
		//winArgs = window.dialogArguments;
		winArgs =dialog.get('data');
		
		var width = $("#scritp").width();
		var height = $("#script").height();
		editor = CodeMirror.fromTextArea(document.getElementById("script"), {
			mode: "text/x-mariadb",
			lineWrapping:true,
			lineNumbers: true
		 });
		editor.setSize(width,height);
		
		var script =winArgs &&  winArgs.script;
		var tables =winArgs && winArgs.tables;
		
		if(script)
			editor.setValue(script);
		if(tables)
			initCheckTables(tables);
			
		
	});
	
	function initCheckTables(tables){
		for (var i = 0, c; c = tables[i++];) {
			var table = c.table;
			$('input[name="tables"]').each(function(){
				var me=$(this),
					val = me.val();
				if(table==val)
					 me.attr("checked",'true');
		    });
		}
	}
	/**
	*选择字段
	*/
	function flowVarChange (){
		var me = $(this),
			selected=me.find("option:selected"),
			source = selected.attr('source'),
			/* table  = selected.attr('table'), */
			val = me.val();
		if($.isEmpty(val))
			return;
		/* val = table +"." +(source ==1?("F_"+val):val); */
		 //val = (source ==1?("F_"+val):val);
		me.val('');
		editor.replaceSelection(val);
		editor.setCursor(1,'');
	}

	function commFieldChange(){
		var me = $(this),
			val = me.val();
		if($.isEmpty(val))
			return;
		me.val('');
		editor.replaceSelection(val);
		editor.setCursor(1,'');
	}
	/**
	* OK事件处理
	*/
	function onOk(obj){
		var _this = $(obj);
		//window.returnValue = getScriptContent();
		dialog.get('sucCall')(getScriptContent());
		dialog.close();
		
	};
	/**
	* 获取脚本内容
	*/
	function getScriptContent(){
		editor.save();
		var json ={},
			script = $("#script").val(),
			tables = [];
		
		$('input[name="tables"]:checked').each(function(){
			var me = $(this),
				obj = {};
				obj.table = me.val(),
				obj.source = me.attr("source"),
				obj.mainTable = me.attr("maintable"),
				obj.relation = me.attr("relation");
			tables.push(obj);
			
	    });
		
		json.script =script;
		json.tables =tables;
		return json;
	};
	
</script>
</head>
<body>
	<div class="panel" >
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">设置条件脚本</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link ok" onclick="onOk(this)"><span></span>确定</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" href="javascript:;" onclick="dialog.close()"><span></span>关闭</a>
					</div>
					<div class="tipbox"><a href="javascript:;" class="tipinfo"><span>该脚本为SQL 脚本 ，为where后的脚本,<br/>例：BUS_CREATOR_ID=:[CUR_USER]</span></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="scriptForm" method="post" action="save.ht">			
					<table class="table-detail" cellpadding="1" cellspacing="1" border="0"  >
						<tr>
							<td width="40%">字段：
								<select id="flowVarsSelect" class="left margin-set" name="flowVars" onchange="flowVarChange.apply(this)">
									<option value="">--请选择--</option>
									<%-- <optgroup class="main-table-item" label="${bpmFormTable.tableDesc}" ></optgroup> --%>
									<c:forEach items="${sysQueryFields}" var="field">
										<%-- <option class="field-item"  table="${bpmFormTable.tableName}"   maintable="${bpmFormTable.tableName}" relation="${bpmFormTable.relation}"  source="1"  value="${field.fieldName}" chosenopt="${field.jsonOptions}" ctltype="${field.controlType}" ftype="${field.fieldType}" datefmt='${field.datefmt}'>${field.fieldDesc}</option> --%>
										<option class="field-item" source="1"  value="${field.name}" ftype="${field.type}" table="" >${field.fieldDesc}</option>
									</c:forEach>
									<%-- <c:forEach items="${bpmFormTable.subTableList}" var="subTable">	
											<optgroup class="sub-table-item"   label="${subTable.tableDesc}" ></optgroup>
											<c:forEach items="${subTable.fieldList}" var="subField">
												<option class="sub-field-item" table="${subTable.tableName}" maintable="${bpmFormTable.tableName}"  relation="${subTable.relation}"  source="1" value="${subField.fieldName}" chosenopt="${subField.jsonOptions}" ctltype="${subField.controlType}" ftype="${subField.fieldType}" datefmt='${subField.datefmt}'>${subField.fieldDesc}</option>
											</c:forEach>
									</c:forEach>
									<c:forEach items="${bpmFormTable.otherTableList}" var="otherTable">	
											<optgroup class="other-table-item"  label="${otherTable.tableDesc}" ></optgroup>
											<c:forEach items="${otherTable.fieldList}" var="otherField">
												<option class="other-field-item" table="${otherTable.tableName}" maintable="${bpmFormTable.tableName}" relation="${otherTable.relation}"  source="2"   value="${otherField.fieldName}" chosenopt="${otherField.jsonOptions}" ctltype="${otherField.controlType}" ftype="${otherField.fieldType}" datefmt='${otherField.datefmt}'>${otherField.fieldDesc}</option>
											</c:forEach>
									</c:forEach> --%>
								</select>
							</td>
							<td>
								常用字段：
								<select id="commFieldSelect" class="left margin-set" name="commFields" onchange="commFieldChange.apply(this)">
									<option value="">--请选择--</option>
									<option value="[CUR_USER]">当前人</option>
									<option value="[CUR_ORG]">当前人组织</option>
								</select>
							</td>
						</tr>
						<%-- <tr>
							<td colspan="2">
								使用的表:<input type="checkbox" value="${bpmFormTable.tableName}" name="tables"  maintable="${bpmFormTable.tableName}" relation="${bpmFormTable.relation}"  source="1" >${bpmFormTable.tableDesc}
								<c:forEach items="${bpmFormTable.subTableList}" var="subTable">
									<input type="checkbox" value="${subTable.tableName}" name="tables"  maintable="${bpmFormTable.tableName}" relation="${subTable.relation}"  source="1"  >${subTable.tableDesc}
								</c:forEach>
								<c:forEach items="${bpmFormTable.otherTableList}" var="otherTable">
									<input type="checkbox" value="${otherTable.tableName}" name="tables" maintable="${bpmFormTable.tableName}" relation="${otherTable.relation}"  source="2"  >${otherTable.tableDesc}
								</c:forEach>
							</td>
						</tr> --%>
						<tr>
							<td colspan="2">
								<textarea  id="script" style="width: 100px;height: 300px" ></textarea>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>
