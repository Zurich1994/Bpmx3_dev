<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><c:if test="${ not empty nodeName}">[${nodeName}]</c:if>节点人员设置</title>
<f:link href="form.css" ></f:link>
<link href="${ctx}/js/jquery/plugins/link-div-default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeRule"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/nodeUserSelectorJS.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorInit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefinitionConditionEdit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeRule.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ConditionScriptEditDialog.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/PersonScriptAddDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.linkdiv.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog=null;
try{
	dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
}catch(e){
	dialog=window;
}


var defId = ${defId};
function addPersonScript(obj){
	var _this = $(obj);
	PersonScriptAddDialog({
		data:{
			defId:defId
		},
		callback:addScriptCallBack
	});
};
function addScriptCallBack(data){
	var script = data.script;
	var inst = script.classInsName;
	var method = script.methodName;
	var str = "return "+inst +"."+method+ "( ";
	var paramStr="";
	for(var i=0 ; i<script.argument.length; i++){
		var p=script.argument[i];
		switch(p.paraValType){
		case '1':
			paramStr += p.paraVal+" , " ;
			break;
		case '2':
			if(p.paraType.indexOf("String")>0){
				paramStr += "\"" + p.paraVal+ "\" , " ;
			}else{
				paramStr +=  p.paraVal+ " , " ;
			}
			break;
		}
	}
	if(paramStr){
		paramStr = paramStr.substring(0,paramStr.length-2);
	}
	str += paramStr+");" ;
	var target = $('#txtScriptData')[0];
	jQuery.insertText(target,str);
};

</script>
<style type="text/css">
a.ruledetail,a.delrule {
	cursor: pointer;
}
html{ 
	overflow-x: hidden;
}
#condition-table td{
	text-align:left;
}
.table-grid textarea {
	float: left;
	height: 30px;
}
</style>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">节点人员设置</span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" onclick="save()"><span></span>保存</a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link del" onclick="dialog.close();"><span></span>关闭</a></div>
						<div class="l-bar-separator"></div>
					</div>	
				</div>
			</div>
		</div>
		<div class="panel-body">
			<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
					<legend><span>规则设置</span></legend>					
					<div class="table-top">
						<div class="table-top-right">	
							<div class="toolBar" style="margin:0;">
								<div class="group"><a class="link add" onclick="addDiv(1)">添加规则</a></div>
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
			<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
					<legend><span>人员设置</span></legend>
					<div style="margin-top:5px;">
						<input type="hidden" name="defId" value="${bpmDefinition.defId}" />											
						<input type="hidden" id="conditionId" name="conditionId" value="${bpmUserCondition.id}" />
						
						<%@include file="/commons/include/nodeUserSelector.jsp" %>
					</div>
			</fieldset>
		<!-- 规则模板 -->
		<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
		<!-- 人员设置模板 -->
		<%@include file="/commons/include/nodeUserTemplate.jsp" %>
		
	
		<div class="hidden">
				<div id="userSetTypesSelectOpt">
					<c:forEach items="${userSetTypes}" var="item">
						<option value="${item.key}" <c:if test="${item.key eq 'users'}">selected="selected"</c:if> >${item.value["title"]}</option>
					</c:forEach>
				</div>
				<textarea id="conditionEntity">
						{
						setId:"${setId}",
						defId:${defId},
						actDefId:"${bpmDefinition.actDefId}",
						nodeId:"${nodeId}",
						conditionId:"${bpmUserCondition.id}",
						conditionType:"${bpmUserCondition.conditionType}",
						sn:"${bpmUserCondition.sn}",
						groupNo:"${bpmUserCondition.groupNo}",
						formIdentity:"${bpmUserCondition.formIdentity}"
						}
				</textarea>
				<textarea id="conditionTxt">
					${bpmUserCondition.condition}
				</textarea>
		</div>	
		<div id="divScriptData" style="display: none;">
			<a href="javascript:;" id="btnScript" class="link var" title="常用脚本" onclick="addPersonScript()">常用脚本</a>
			<ul>
				<li>表达式必须返回Set&lt;String>集合类型的数据，数据项为用户ID。</li>
			</ul>
			<textarea id="txtScriptData" rows="10" cols="80" style="height: 200px;width:480px"></textarea>
		</div>
		<div style="display: none">
			<input type="hidden" id="defId" value="${bpmDefinition.defId}" />
			<input type="hidden" id="actDefId" value="${bpmDefinition.actDefId}" />
			<input type="hidden" id="nodeId" value="${nodeId}" />
			<input type="hidden" id="parentActDefId" value="${parentActDefId}" />
		</div>
</body>
</html>


