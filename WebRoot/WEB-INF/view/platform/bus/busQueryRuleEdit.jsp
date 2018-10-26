<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>高级查询设置</title>
<%@include file="/commons/include/form.jsp"%>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctx}/js/jquery/plugins/jquery.fix.clone.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript"
	src="${ctx}/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript"
	src="${ctx}/js/codemirror/lib/util/matchbrackets.js"></script>
<script type="text/javascript"
	src="${ctx}/js/codemirror/mode/groovy/groovy.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/plugins/jquery.qtip.js"></script>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	var tab = "";
	window.returnValue = "close";
	$(function() {
		//Tag Layout
		tab = $("#tab").ligerTab({
		//onBeforeSelectTabItem:onBeforeSelectTabItem
		});

		__BusQueryRule__.init();

		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}

		$("a.save").click(function() {
			var form = $('#busQueryRuleForm');
			if (!form.valid())
				return;
			if (saveChange(form))
				customFormSubmit(form, options);
		});
		// 批量授权
		$("select.rightselect").change(setPermision);
	});

	function manageFieldValid() {
		var name = new Array();
		$("#manageTbl").find("[var='name']").each(function() {
			name.push($(this).val());
		});
		return isRepeat(name);
	}
	//验证排序字段
	function sortFieldValid() {
		var i = 0;
		$("#sortTbl tbody tr").each(function() {
			i++;
		});
		if (i > 3)
			return true;
		return false;
	}

	function isRepeat(arr) {
		var hash = {};
		for ( var i in arr) {
			if (hash[arr[i]]) {
				return true;
			}
			// 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
			hash[arr[i]] = true;
		}
		return false;
	}
	//导出字段
	function getExportField() {
		var json = [];
		$("#exportFieldTbl tr[var='exportTableTr']").each(
				function() {
					var me = $(this), table = {}, tableName = $(
							"[var='tablename']", me).val(), fields = [];
					table.tableName = tableName;
					table.tableDesc = $("[var='tabledesc']", me).val();
					table.isMain = $("[var='ismain']", me).val();
					$("#exportFieldTbl tr[var='exportFieldTr']").each(
							function() {
								var self = $(this), obj = {}, name = $(
										"[var='tablename']", self).val();
								if (tableName == name) {

									obj.name = $("[var='name']", self).html();
									obj.desc = $("[var='desc']", self).val();
									obj.type = $("[var='type']", self).val();
									obj.style = $("[var='style']", self).val();
									obj.tableName = tableName;
									obj.isMain = $("[var='ismain']", self)
											.val();
									obj.right = getRightJson(self, 2);
									fields.push(obj)
								}
							});
					table.fields = fields;
					json.push(table);

				});
		return json;
	}

	//显示字段
	function getDisplayField() {
		var json = [];
		$("#displayFieldTbl tr[var='displayFieldTr']").each(function() {
			var me = $(this), obj = {};
			obj.name = $("[var='name']", me).html();
			obj.desc = $("[var='desc']", me).val();
			obj.variable = $("[var='variable']", me).val();
			obj.type = $("[var='type']", me).val();
			obj.style = $("[var='style']", me).val();

			obj.right = getRightJson(me, 1);
			json.push(obj);
		});
		return json;
	}
	function getRightJson(me, flag) {
		var rightJson = [];
		if (flag == 1) {
			var fieldRight = $("[var='fieldRight']", me);
			var printRight = $("[var='printRight']", me);
			rightJson.push(getRight(fieldRight, 0));
			rightJson.push(getRight(printRight, 1));
		} else {
			var exportRight = $("[var='exportRight']", me);
			rightJson.push(getRight(exportRight, 2));
		}
		return rightJson;
	}

	function getRight(rightTd, s) {
		var obj = {};
		obj.s = s;
		obj.type = $("[var='right']", rightTd).val();
		obj.id = $("[var='rightId']", rightTd).val();
		obj.name = $("[var='rightName']", rightTd).val();
		obj.script = $("[var='rightScript']", rightTd).val();
		return obj;
	}

	//排序字段
	function getSortField() {
		var fields = new Array();
		$("#sortTbl tbody tr").each(function() {
			var me = $(this), obj = {};
			obj.name = $("[var='name']", me).html();
			obj.desc = $("[var='desc']", me).html();
			obj.sort = $("[var='sort']", me).val();
			obj.source = $("[var='source']", me).val();
			fields.push(obj);
		});
		return fields;
	}

	//过滤字段
	function getFilterField() {
		var fields = new Array();
		$("#filterTbl tbody tr").each(function() {
			var me = $(this), obj = {}, rightJson = [];
			obj.name = $("[var='name']", me).html();
			obj.key = $("[var='key']", me).html();
			obj.type = $("[var='type']", me).val();
			obj.condition = $("[var='condition']", me).val();
			rightJson.push(getRight($("[var='filterRight']", me), 3));
			obj.right = rightJson;
			fields.push(obj);
		});
		return fields;
	}

	function saveChange(form) {
		//判断排序字段太多报错问题
		if (sortFieldValid()) {
			$.ligerDialog.error("排序字段不能设置超过3个，请检查！", "提示信息");
			tab.selectTabItem("sortSetting");
			return false;
		}

		var needPage = $("input[name='needPage']:checked").val();

		var templateAlias = $("#templateAlias").val();
		if (templateAlias == "" || needPage == "") {
			tab.selectTabItem("baseSetting");
			form.valid();
			return false;
		}
		//显示字段
		var displayField = JSON2.stringify(getDisplayField());
		//条件字段
		//var conditionField= JSON2.stringify(getConditionField());
		//排序字段
		var sortField = JSON2.stringify(getSortField());
		//过滤字段字段
		var filterField = JSON2.stringify(getFilterField());
		//导出字段
		//var exportField= JSON2.stringify(getExportField());

		$('#displayField').val(displayField);
		//$('#conditionField').val(conditionField);
		$('#sortField').val(sortField);
		$('#filterField').val(filterField);
		//$('#manageField').val(manageField);
		//$('#exportField').val(exportField);
		return true;
	}

	/**
	 * 自定义外部表单并提交
	 * @return void
	 */
	function customFormSubmit(form, options) {
		var id = $("#id").val();
		var tableName = $("#tableName").val();

		var isQuery = $("#isQuery").val();
		var isFilter = $("#isFilter").val();

		var needPage = $("input[name='needPage']:checked").val();
		var pageSize = $("#pageSize").val();

		//显示字段
		var displayField = $('#displayField').val();

		//条件字段
		//var conditionField= $('#conditionField').val();
		//排序字段
		var sortField = $('#sortField').val();
		//过滤字段字段
		var filterField = $('#filterField').val();
		//管理字段
		var exportField = $('#exportField').val();

		var json = {
			id : id,
			tableName : tableName,
			isQuery : isQuery,
			isFilter : isFilter,
			needPage : needPage,
			pageSize : pageSize,
			displayField : displayField,
			sortField : sortField,
			filterField : filterField,
			exportField : exportField
		};

		var form = $('<form method="post" action="save.ht"></form>');
		var input = $("<input type='hidden' name='json'/>");
		var jsonStr = JSON2.stringify(json);

		input.val(jsonStr);
		form.append(input);
		form.ajaxForm(options);
		form.submit();
	}

	function showResponse(responseText) {
		$.ligerDialog.closeWaitting();
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(obj.getMessage() + ",是否继续操作", "提示信息",
					function(rtn) {
						if (rtn) {
							window.location.href = location.href.getNewUrl();
						} else {
							closeWin();
						}
					});

		} else {
			$.ligerDialog.err(obj.getMessage(), "提示信息");
		}
	}

	function closeWin() {
		window.close();
	}

	//批量设置权限。
	function setPermision() {
		var me = $(this), val = me.val(), right = me.attr('right');
		if (val == "")
			return;
		$("span[name='r_span'],span[name='w_span'],span[name='b_span']", obj)
				.hide();
		$('td[var="' + right + '"]').each(function() {
			var self = $(this), rightSelect = $("[var='right']", self);
			rightSelect.next().hide();
			rightSelect.next().next().hide();
			if (val == 0) {
				rightSelect.val('none');
			} else if (val == 1) {
				rightSelect.val('everyone');
			}
		});
		me.val("");
	}
</script>
<script type="text/javascript"
	src="${ctx}/js/hotent/platform/bus/BusQueryRuleEdit.js"></script>
</head>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">查询规则设置</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link close" href="javascript:;" onclick="closeWin();"><span></span>关闭</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="busQueryRuleForm" method="post" action="save.ht">

				<div id="tab">
					<!-- 基本信息  start-->
					<div tabid="baseSetting" id="table" title="基本信息">
						<div>
							<div class="tbar-title">
								<span class="tbar-label">基本信息</span>
							</div>
							<table class="table-detail" cellpadding="0" cellspacing="0"
								border="0" type="main" style="border-width: 0 !important;">
								<tr>
									<th width="10%">表名:</th>
									<td>${tableEntity.tableName}<c:if
											test="${!empty tableEntity.comment}">【${tableEntity.comment}】</c:if>&nbsp;
									</td>
								</tr>
								<tr>
									<th width="10%">是否分页:</th>
									<td><input type="radio" name="needPage" value="0"
										onclick="__BusQueryRule__.switchNeedPage(this);"
										<c:if test="${busQueryRule.needPage==0}">checked="checked"</c:if>>不分页
										<input type="radio" name="needPage" value="1"
										onclick="__BusQueryRule__.switchNeedPage(this);"
										<c:if test="${busQueryRule.needPage==1}">checked="checked"</c:if>>分页
										<span
										style="color:red;<c:if test="${busQueryRule.needPage==0}">display:none;</c:if>"
										id="spanPageSize" name="spanPageSize"> 分页大小： <select
											id="pageSize" name="pageSize">
												<option value="5"
													<c:if test="${busQueryRule.pageSize==5}">selected="selected"</c:if>>5</option>
												<option value="10"
													<c:if test="${busQueryRule.pageSize==10}">selected="selected"</c:if>>10</option>
												<option value="15"
													<c:if test="${busQueryRule.pageSize==15}">selected="selected"</c:if>>15</option>
												<option value="20"
													<c:if test="${busQueryRule.pageSize==20}">selected="selected"</c:if>>20</option>
												<option value="50"
													<c:if test="${busQueryRule.pageSize==50}">selected="selected"</c:if>>50</option>
												<option value="100"
													<c:if test="${busQueryRule.pageSize==100}">selected="selected"</c:if>>100</option>
										</select>
									</span></td>
								</tr>
								<tr>
									<th>是否初始查询:</th>
									<td><select name="isQuery" id="isQuery"
										validate="{required:true}">
											<option value="0"
												<c:if test="${busQueryRule.isQuery==0}">selected="selected"</c:if>>是</option>
											<option value="1"
												<c:if test="${busQueryRule.isQuery==1}">selected="selected"</c:if>>否</option>
									</select></td>
								</tr>
								<tr>
									<th>没有过滤条件是否默认过滤:</th>
									<td><select name="isFilter" id="isFilter"
										validate="{required:true}">
											<option value="0"
												<c:if test="${busQueryRule.isFilter==0}">selected="selected"</c:if>>是</option>
											<option value="1"
												<c:if test="${busQueryRule.isFilter==1}">selected="selected"</c:if>>否</option>
									</select></td>
								</tr>
								<input type="hidden" id="id" name="id"
									value="${busQueryRule.id}">
								<input type="hidden" id="tableName" name="tableName"
									value="${tableEntity.tableName}">
								<textarea style="display: none;" id="displayField"
									name="displayField">${fn:escapeXml(busQueryRule.displayField)}</textarea>
								<textarea style="display: none;" id="sortField" name="sortField">${fn:escapeXml(busQueryRule.sortField)}</textarea>
								<textarea style="display: none;" id="filterField"
									name="filterField">${fn:escapeXml(busQueryRule.filterField)}</textarea>
								<textarea style="display: none;" id="exportField"
									name="exportField">${fn:escapeXml(busQueryRule.exportField)}</textarea>
							</table>
						</div>
					</div>
			</form>
			<!-- 基本信息  end-->
			<!-- 显示字段  start-->
			<div tabid="displaySetting" id="table" title="显示列字段">
				<div>
					<table id="displayFieldTbl" class="table-grid">
						<thead>
							<tr>
								<th width="5%">序号</th>
								<th width="15%">列名</th>
								<th width="20%">注释</th>
								<th width="20%">权限<select class="rightselect"
									right="fieldRight"><option selected="selected"
											value=""></option>
										<option value="0">无</option>
										<option value="1">所有人</option></select></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 显示字段  end-->
			<!-- 排序字段 start-->
			<div tabid="sortSetting" id="table" title="排序字段">
				<div class="sort-cols">
					<div class="sort-cols-div">
						<table id="sort-columnsTbl" cellpadding="0" cellspacing="0"
							border="0" class="table-detail">
							<thead>
								<tr class="leftHeader">
									<th>选择</th>
									<th>列名</th>
									<th>注释</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty tableEntity}">
									<c:forEach items="${tableEntity.tableFieldList}" var="field"
										varStatus="status">
										<tr <c:if test="${status.index%2==0}">class="odd"</c:if>
											<c:if test="${status.index%2!=0}">class="odd"</c:if>>
											<td><input class="pk" type="checkbox" name="select"
												fieldname="${field.name}" fieldtype="${field.fieldType}"
												fielddesc="${field.desc}"></td>
											<td>${field.name}</td>
											<td>${field.desc}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="sort-conds">
					<div class="sort-conds-div sort-conds-build" id="sort-build-div">
						<div class="sort-conds-div-left">
							<div class="sort-conds-div-left-div">
								<a style="margin-top: 180px;" id="selectSort" href="javascript:;"
									class="button"> <span></span>==>
								</a>
							</div>
						</div>
						<div class="sort-conds-div-right">
							<div class="sort-conds-div-right-div">
								<table id="sortTbl" cellpadding="0" cellspacing="0" border="0"
									class="table-detail">
									<thead>
										<tr class="leftHeader">
											<th nowrap="nowrap">列名</th>
											<th nowrap="nowrap">注释</th>
											<th nowrap="nowrap">排序</th>
											<th nowrap="nowrap">管理</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 排序字段 end-->
			<!-- 过滤条件 start	-->
			<div tabid="filterSetting" id="table" title="过滤条件">
				<div class="table-top-left">
					<div class="toolBar" style="margin: 0;">
						<div class="group">
							<a class="link add" id="btnSearch"
								onclick="__BusQueryRule__.addFilter()"><span></span>添加</a>
						</div>
						<!--	<div class="l-bar-separator"></div>
								<div class="group"><a class="link edit  " onclick="filterDialog()"><span></span>修改</a></div>
							-->
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del " id="btnSearch"
								onclick="__BusQueryRule__.delFilter();"><span></span>删除</a>
						</div>
					</div>
				</div>
				<table id="filterTbl" class="table-grid">
					<thead>
						<tr>
							<th width="5%">选择</th>
							<th width="10%">名称</th>
							<th width="10%">Key</th>
							<th width="10%">类型</th>
							<th>权限<select class="rightselect" right="filterRight"><option
										selected="selected" value=""></option>
									<option value="0">无</option>
									<option value="1">所有人</option></select></th>
							<th width="10%">管理</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<!-- 过滤条件 end-->

		</div>
	</div>
	<!-- end of panel-body -->
	</div>
	<div class="hidden">
		<!-- 显示字段模板 -->
		<div id="displayFieldTemplate">
			<table cellpadding="1" cellspacing="1" class="table-detail">
				<tr var="displayFieldTr">
					<input type="hidden" var="type" value="" />
					<input type="hidden" var="style" value="" />
					<input type="hidden" var="variable" value="" />
					<td var="index">&nbsp;</td>
					<td var="name">&nbsp;</td>
					<td><input type="text" var="desc" value="" /></td>
					<td var="fieldRight"></td>
				</tr>
			</table>
		</div>
		<!-- 排序模板 -->
		<div id="sortTemplate" style="display: none;">
			<table cellpadding="1" cellspacing="1" class="table-detail">
				<tr var="sortTr">
					<input type="hidden" var="source" value="1" />
					<td var="name">&nbsp;</td>
					<td var="desc">&nbsp;</td>
					<td><select var="sort">
							<option value="ASC">升序</option>
							<option value="DESC">降序</option>
					</select></td>
					<td><a class="link moveup" href="javascript:;" title="上移"
						onclick="__BusQueryRule__.moveTr(this,true)"></a> <a
						class="link movedown" href="javascript:;" title="下移"
						onclick="__BusQueryRule__.moveTr(this,false)"></a> <a
						class="link del" href="javascript:;" title="删除"
						onclick="__BusQueryRule__.delTr(this)"></a></td>
				</tr>
			</table>
		</div>
		<!-- 过滤条件模板 -->
		<div id="filterTemplate" style="display: none;">
			<table cellpadding="1" cellspacing="1" class="table-detail">
				<tr var="filterTr">
					<td var="index"><input class="pk" type="checkbox"
						name="select" /> <input type="hidden" var="type"> <textarea
							style="display: none;" var="condition"></textarea></td>
					<td var="name"></td>
					<td var="key"></td>
					<td var="typeshow"></td>
					<td var="filterRight"></td>
					<td><a class="link moveup" href="javascript:;" title="上移"
						onclick="__BusQueryRule__.moveTr(this,true)"></a> <a
						class="link movedown" href="javascript:;" title="下移"
						onclick="__BusQueryRule__.moveTr(this,false)"></a> <a
						class="link edit" href="javascript:;" title="编辑"
						onclick="__BusQueryRule__.editFilter(this)"></a> <a
						class="link del" href="javascript:;" title="删除"
						onclick="__BusQueryRule__.delTr(this)"></a></td>
				</tr>
			</table>
		</div>

	</div>

</body>
<style type="text/css">
.tab-top {
	margin: 0 0 0 0;
}

#busQueryRule td {
	padding: 2px 3px;
}

#busQueryRule th {
	padding: 2px 6px;
}

.even {
	height: 28px;
}

.odd {
	height: 28px;
}

.over {
	background: #FCF1CA;
}

.hide {
	display: none;
}

.moveSelect {
	margin: 4px auto;
}

.leftHeader th {
	font-weight: bold;
	text-align: center;
}

/**排序*/
.sort-cols {
	border: 1px solid #A8CFEB;
	overflow: auto;
	height: 400px;
	float: left;
	width: 29%;
}

.sort-conds {
	border-top: 1px solid #A8CFEB;
	border-bottom: 1px solid #A8CFEB;
	border-right: 1px solid #A8CFEB;
	height: 400px;
	float: left;
	width: 70%;
}

.sort-cols-div {
	padding: 2px;
	height: 394px;
}

.sort-conds-div {
	padding: 2px;
	height: 400px;
}

.sort-conds-div-left {
	float: left;
	width: 15%;
}

.sort-conds-div-left-div {
	border-right: 1px solid #A8CFEB;
	text-align: center;
	vertical-align: middle;
	height: 394px;
	padding-top: 180px;
}

.sort-conds-div-right {
	height: 400px;
	overflow: auto;
	float: left;
	width: 85%;
}

.sort-conds-div-right-div {
	height: 390px;
	padding: 2px;
	margin: 1px;
}

.sort-script-div {
	border: 1px solid #A8CFEB;
	padding: 2px;
	height: 394px;
}

.sort-script-div-script-editor {
	width: 100%;
	height: 348px;
}

.sort-script-div-parameters {
	height: 40px;
}

.sort-script-div-parameters-list {
	height: 36px;
	overflow: auto;
}

.sort-script-div-script {
	height: 346px;
}

.sort-script-div-script-operate { /* 			position: absolute; */
	margin-top: 5px;
}

.info {
	padding-right: 5px;
}

.info ul li {
	list-style: disc;
	margin-left: 30px;
}

.info font {
	color: red;
}
</style>
</html>