<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title>别名脚本管理列表</title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptEdit.js"></script>
<script type="text/javascript">

	//自定义类型脚本预览事件
	function runCustomScript(id){
		var scriptComten = $("#"+id).val();
		exeCustomScript(scriptComten,"list");
	}

    //预览事件
	function runScript(classInsName,methodName,jsonId){
		var jsonStr = $("#"+jsonId).val();
		var json; 
		if(typeof(jsonStr)==undefined||jsonStr==null||jsonStr==''){
			json==[];
		}else{
			json = eval('(' + jsonStr + ')');
		}
    	exeScript(classInsName,methodName,json);
	}

	 //帮助事件
	function goToHelp(){
		var url = __ctx+'/platform/system/aliasScript/goToHelp.ht'
		var winArgs='height=550,width=700,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
		url=url.getNewUrl();
		window.open (url,"开发帮助说明",winArgs);
	}

    
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">别名脚本管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>编辑</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link reset" onclick="$.clearQueryForm()"><span></span>重置</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label">脚本别名:</span><input type="text" name="Q_aliasName_SL"  class="inputText" value="${param['Q_aliasName_SL']}"/></<li>
						<li><span class="label">脚本描述:</span><input type="text" name="Q_aliasDesc_SL"  class="inputText" value="${param['Q_aliasDesc_SL']}"/></<li>
						<li><span class="label">脚本所在类的类名:</span><input type="text" name="Q_className_SL"  class="inputText" value="${param['Q_className_SL']}"/></<li>
						<li><span class="label">调用方法:</span><input type="text" name="Q_methodName_SL"  class="inputText" value="${param['Q_methodName_SL']}"/></<li>
						<li><span class="label">脚本内容:</span><input type="text" name="Q_scriptComten_SL"  class="inputText" value="${param['Q_scriptComten_SL']}"/></<li>
						<li><span class="label">返回类型:</span>
							<select name="Q_scriptType_S" class="select" value="${param['Q_scriptType_S']}">
								<option value="">全部</option>
								<option value="default" <c:if test="${param['Q_scriptType_S'] eq 'default'}">selected</c:if>>系统默认</option>
								<option value="custom" <c:if test="${param['Q_scriptType_S'] eq 'custom'}">selected</c:if>>自定义</option>
							</select>
						</<li>
						<li><span class="label">是否启用:</span>
							<select name="Q_enable_L" class="select" value="${param['Q_enable_L']}">
								<option value="">全部</option>
								<option value="0" <c:if test="${param['Q_enable_L'] == '0'}">selected</c:if>>是</option>
								<option value="1" <c:if test="${param['Q_enable_L'] == '1'}">selected</c:if>>否</option>
							</select>
						</<li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="aliasScriptList" id="aliasScriptItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${aliasScriptItem.id}">
				</display:column>
				<display:column  title="脚本别名" sortable="true" sortName="ALIAS_NAME">
						<a class="link" href="get.ht?id=${aliasScriptItem.id}" style="color:blue;">${aliasScriptItem.aliasName}</a> 
				</display:column>
				<display:column property="aliasDesc" title="脚本描述" sortable="true" sortName="ALIAS_DESC"></display:column>
				<display:column title="脚本所在类的类名" sortable="true" sortName="CLASS_NAME" >
					<c:choose>
						<c:when test="${aliasScriptItem.className == null || aliasScriptItem.className == ''}">自定义</c:when>
						<c:otherwise>${aliasScriptItem.className}</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="调用方法" sortable="true" sortName="METHOD_NAME">
					<c:choose>
						<c:when test="${aliasScriptItem.methodName == null ||aliasScriptItem.methodName == ''}">自定义</c:when>
						<c:otherwise>${aliasScriptItem.methodName}</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="脚本类型"   sortable="true" sortName="SCRIPT_TYPE">
					<c:choose>
						<c:when test="${aliasScriptItem.scriptType == 'default' }">系统默认</c:when>
						<c:when test="${aliasScriptItem.scriptType == 'custom' }">自定义</c:when>
					</c:choose>
				</display:column>
				<display:column title="是否启动"   sortable="true" sortName="ENABLE">
					<c:choose>
						<c:when test="${aliasScriptItem.enable == 0}"><span class="green">是</span></c:when>
						<c:when test="${aliasScriptItem.enable == 1}"><span class="red">否</span></c:when>
					</c:choose>
				</display:column>
				
				<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<a href="del.ht?id=${aliasScriptItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${aliasScriptItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${aliasScriptItem.id}" class="link detail">明细</a>
					
					<c:choose>
						<c:when test="${aliasScriptItem.scriptType == 'default' }">
							<a href="javascript:;" onclick="javascript:runScript('${aliasScriptItem.classInsName}','${aliasScriptItem.methodName}','${aliasScriptItem.id}_Argument')" class="link run">预览</a>
						</c:when>
						<c:when test="${aliasScriptItem.scriptType == 'custom' }">
							<a href="javascript:;" onclick="javascript:runCustomScript('${aliasScriptItem.id}_scriptComten')" class="link run">预览</a>
						</c:when>
					</c:choose> 
				    <textarea class="hidden" id="${aliasScriptItem.id}_Argument">${aliasScriptItem.argument}</textarea>
				    <textarea class="hidden" id="${aliasScriptItem.id}_scriptComten">${aliasScriptItem.scriptComten}</textarea>
				    <a href="javascript:;" onclick="javascript:goToHelp()" class="link help">帮助</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="aliasScriptItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


