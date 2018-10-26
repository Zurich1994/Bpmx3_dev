<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>条件过滤设置</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="form.css" ></f:link>
<link href="${ctx}/js/jquery/plugins/link-div-default.css" rel="stylesheet" type="text/css" />
<link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" >
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/mode/sql/sql.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorInit.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.linkdiv.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/bus/BusQueryRuleFilter.js"></script>
  <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
  <script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
  <script type="text/javascript">
	  /*KILLDIALOG*/
	  var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
  </script>
</head>
<body>
<div class="panel">

	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">条件过滤设置</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" onclick="save()"><span></span>确定</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link del" onclick="javasrcipt:dialog.close()"><span></span>关闭</a></div>
				<div class="l-bar-separator"></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table style="margin: auto;width:100%;margin-top: 1px;" class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td  width="10%" nowrap="nowrap">过滤名称：</td>
				<td><input type="text" class="inputText" name="name" id="name" size="50" value="${name}"  onblur="getPingyin(this)"></td>
				<td  width="10%" nowrap="nowrap">过滤key：</td>
				<td><input type="text" class="inputText" name="key" id="key" size="50" value="${key}"></td>
			</tr>
			<tr>
				<td>
					脚本类型：
				</td>
				<td colspan="3">
					<select id="type" name="type" >
						<option value="1" selected="selected">条件脚本</option>
						<option value="2" >SQL</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<fieldset style="margin: 5px 0px 5px 0px;" id="filterSetting" >
					<legend><span>条件设置</span></legend>		
					<div class="table-top">
						<div class="table-top-right">	
							<div class="toolBar" style="margin:0;">
								<div class="group"><a class="link add" onclick="addDiv(1)">添加条件</a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link add" onclick="addDiv(2)">添加脚本</a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="assembleDiv()">组合规则</a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="splitDiv()">拆分规则</a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link del" onclick="removeDiv()">删除</a></div>
							</div>
					    </div>
					</div>
					<div id="ruleDiv" style="border:2px solid #ccc;margin:5px 0 0 0;"></div>
				</fieldset>
				<fieldset style="margin: 5px 0px 5px 0px;display: none;" id="sqlSetting" >
					<legend><span>SQL设置</span></legend>	
					<table  cellpadding="0" cellspacing="0" border="0" style="width: 100%;"  class="table-detail" >
						<tr>
							<td width="5%"><div id="sqlTip"><a href="javascript:;" class="tipinfo"></a></div>
						<td width="10%">常用变量：</td>
							<td>
								<select id="varFieldSelect" class="left margin-set" name="varFields" onchange="varsChange.apply(this)">
									<option value="">--请选择--</option>
									<optgroup class="main-table-item" label="表字段" ></optgroup>			
									<c:forEach items="${tableEntity.tableFieldList}" var="tableField">
										<option class="field-item"  table="${tableEntity.tableName}"   maintable="${tableEntity.tableName}" relation="${tableEntity.relation}"  
												source="2"  value="${tableField.var}" chosenopt="" ctltype="${tableField.controlType}" ftype="${tableField.dataType}" datefmt='${tableField.style}'>
												${tableField.desc}
										</option>
									</c:forEach>
									<optgroup class="main-table-item" label="常用变量" ></optgroup>
									<c:forEach items="${commonVars}" var="commonVar">					
										<option value="${commonVar.value}">${commonVar.name}</option>
									</c:forEach>	
								</select>
							</td>
						
							<td width="10%">表字段：</td>
							<td>
								<select id="fieldSelect" class="left margin-set" name=fields" onchange="fieldChange.apply(this)">
									<option value="">--请选择--</option>
									<optgroup class="main-table-item" label="${tableEntity.comment}" ></optgroup>
									<c:forEach items="${tableEntity.tableFieldList}" var="tableField">
										<option class="field-item"  table="${tableEntity.tableName}"   maintable="${tableEntity.tableName}" relation="${tableEntity.relation}"  
												source="2"  value="${tableField.name}" chosenopt="" ctltype="${tableField.controlType}" ftype="${tableField.dataType}" datefmt='${tableField.style}'>
												${tableField.desc}
										</option>
									</c:forEach>
									<c:forEach items="${tableEntity.subTableList}" var="subTable">				
										<optgroup class="sub-table-item"   label="${subTable.comment}" ></optgroup>
										<c:forEach items="${subTable.tableFieldList}" var="subField">
											<option class="sub-field-item" table="${subTable.tableName}" maintable="${tableEntity.tableName}"  relation="${subTable.relation}"  
													source="2" value="${subField.name}" chosenopt="" ctltype="${tableField.controlType}" ftype="${subField.dataType}" datefmt='${tableField.style}'>${subField.desc}</option>					
										</c:forEach>
									</c:forEach>
								</select>
							</td>
							<td>
								表：
							</td>
							<td>
								<select id="tableSelect" class="left margin-set" name="tableSelect" onchange="tableChange.apply(this)">
									<option value="">--请选择--</option>
									<option value="${tableEntity.tableName}" source="2" >${tableEntity.comment}</option>
									<c:forEach items="${tableEntity.subTableList}" var="subTable">	
										<option value="${subTable.tableName}" source="2" >${subTable.comment}</option>
									</c:forEach>
								</select>
											
							</td>
						</tr>
						<tr>
							<td colspan="7">
							<textarea  id="sql" style="width: 500px;height: 300px" ></textarea>
							</td>
						</tr>
					</table>
				</fieldset>
				</td>
			</tr>
		
		</table>
	</div>
</div>
<div class="hidden">
		<textarea id="filterTxt" style="display: none;">${fn:escapeXml(condition)}</textarea>
		<input type="hidden" name="tableName" id="tableName" value="${tableName}">
		<!-- 数字的判断 -->
		<span  id="judgeCon-1" class="judge-condition" >
			<select  name="judgeCondition">
				<option value="1">等于</option>
				<option value="2">不等于</option>
				<option value="3">大于</option>
				<option value="4">大于等于</option>
				<option value="5">小于</option>
				<option value="6">小于等于</option>
			</select>
		</span>
		<!-- 字符串的判断 -->
		<span  id="judgeCon-2"  class="judge-condition">
			<select name="judgeCondition">
				<option value="1">等于</option>
				<option value="3">等于(忽略大小写)</option>
				<option value="2">不等于</option>
				<option value="4">like</option>
				<option value="5">like左</option>
				<option value="6">like右</option>
			</select>
		</span>
		<!-- 字典的判断 -->
		<span  id="judgeCon-4"  class="judge-condition">
			<select name="judgeCondition">
				<option value="1">等于</option>
				<option value="2">不等于</option>
			</select>
		</span>
		<!-- 选择器的判断 -->
		<span  id="judgeCon-5"   class="judge-condition">
			<select  name="judgeCondition" onchange="judgeConditionChange.apply(this)">
				<option value="1">包含</option>
				<option value="2">不包含</option>
				<option value="3">等于变量</option>
				<option value="4">不等于变量</option>
			</select>
		</span>
		
		
		<!-- 默认类型-->
		<span id="normal-input" class="judge-value"  type="1">
			<input class="short-input" name="judgeValue" type="text" />
		</span>
		<!-- 日期类型 -->
		<span id="date-input" class="judge-value"  type="1">
			<input id="date-input" type="text" class="Wdate" />
		</span>
				
		<!-- 用户选择器 -->
		<div id="user-div">
			<span  class="judge-value" type="1">
				<input type="hidden" value="">
				<input type="text" readonly="readonly" />
				<a href="javascript:;" class="link users">选择</a>
			</span>
		</div>
		
		<!-- 角色选择器 -->
		<div id="role-div">
			<span  class="judge-value"  type="1" >
				<input type="hidden" value="">
				<input type="text" readonly="readonly" />
				<a href="javascript:;" class="link roles">选择</a>
			</span>
		</div>
		<!-- 组织选择器 -->
		<div id="org-div">
			<span  class="judge-value"  type="1">
				<input type="hidden" value="">
				<input type="text" readonly="readonly" />
				<a href="javascript:;" class="link orgs">选择</a>
			</span>
		</div>
		
		<!-- 岗位选择器 -->
		<div id="position-div">
			<span  class="judge-value"  type="1">
				<input type="hidden" value="">
				<input type="text" readonly="readonly" />
				<a href="javascript:;" class="link positions">选择</a>
			</span>
		</div>
		
		<!-- 下拉框 -->
		<textarea id="dic-select">
			<span  class="judge-value"  type="1">
				<select>					
					<#list data as obj>
						<option value="\${obj.option}">\${obj.memo}</option>
					</#list>	
				</select>
			</span>
		</textarea>
		<!-- 单选、多选 -->
		<textarea id="dic-radio-checkbox">
			<span  class="judge-value"  type="1">
				<#list data as obj>
					<label><input type="\${obj.type}" name="\${obj.name}" value="\${obj.option}"/>\${obj.memo}</label>
				</#list>
			</span>
		</textarea>
		<!--常用变量-->
		<span id="commonVar" class="judge-value"  type="2">
				<select >	
					<c:forEach items="${commonVars}" var="commonVar">					
						<option value="${commonVar.value}">${commonVar.name}</option>
					</c:forEach>	
				</select>
		</span>
		
		
		<select id="flowVarsSelect" class="left margin-set" name="flowVars" onchange="flowVarChange.apply(this)">
			<option value="">--请选择--</option>
			<optgroup class="main-table-item" label="${tableEntity.comment}" ></optgroup>	
			<c:forEach items="${tableEntity.tableFieldList}" var="tableField">
				<option class="field-item"  table="${tableEntity.tableName}"   maintable="${tableEntity.tableName}" relation="${tableEntity.relation}"  
						source="2"  value="${tableField.name}" chosenopt="${tableField.options}" ctltype="${tableField.controlType}" ftype="${tableField.dataType}" datefmt='${tableField.style}'>
						${tableField.desc}
				</option>
			</c:forEach>
			<c:forEach items="${tableEntity.subTableList}" var="subTable">				
				<optgroup class="sub-table-item"   label="${subTable.comment}" ></optgroup>
				<c:forEach items="${subTable.tableFieldList}" var="subField">
					<option class="sub-field-item" table="${subTable.tableName}" maintable="${tableEntity.tableName}"  relation="${subTable.relation}"  
							source="2" value="${subField.name}"  chosenopt="${tableField.options}" ctltype="${tableField.controlType}" ftype="${subField.dataType}" datefmt='${tableField.style}'>${subField.desc}</option>					
				</c:forEach>
			</c:forEach>
		</select>
</div>
</body>
</html>