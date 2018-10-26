
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/form.jsp"%>
<title>条件脚本添加对话框</title>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<!--<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ConditionScriptAddDialog.js"></script>-->
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ConditionScriptDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/SelectorInit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)


	//var winArgs = window.dialogArguments;
	var winArgs =dialog.get('data');
	var defId = winArgs.defId;

	$(function(){
		
		
		$(':input[id="dialog"]').live('click',function(){
			var target = $(this).attr('param');
			var dialog = $(this).attr('dialog');
			var me = $(this);
			CommonDialog(dialog,function(data){
				if(Object.prototype.toString.call((data)) == '[object Array]'){
					for(var i=0,d;d=data[i++];){
						me.closest('div').find('[name="paraValID"]').val(d[target]);
						me.closest('div').find('[name="paraVal"]').val(d[target]);
					}
				}else{
					me.closest('div').find('[name="paraValID"]').val(data[target]);
					me.closest('div').find('[name="paraVal"]').val(data[target]);
				}
			});
		});
	});
	
	function formSave(obj){
		var _this=$(obj);
		var form=$('#ruleConditionScriptForm').form();
		form.setData();
		var json =$('textarea[name="json"]',form).val();
		var _this = $(obj);

		var script = getScriptJson();

		var status = script?1:0;
		var winRtn = {
			status:status,
			data:{
				script:script
			}
		};
		//window.returnValue = winRtn;
		dialog.get('sucCall')(winRtn);
		dialog.close();
	};

	function getScriptJson(){
		var conditionScriptObj = $("[name='condScriptId']").data("conditionScriptObj");
		if(!conditionScriptObj){
			return null;
		}
		if(!$.isPlainObject(conditionScriptObj.argument)){
			if($.type(conditionScriptObj.argument)=='string'){
				conditionScriptObj.argument=$.parseJSON(conditionScriptObj.argument);
			}
		}

		var json={};
		$(".para-info-table .param-tr").each(function(i,e){
			var _this = $(this);
			var paraValType = $("[name='paraValType']",_this).val();
			
			if(!conditionScriptObj.argument[i]){
				conditionScriptObj.argument[i]={};
			}
			conditionScriptObj.argument[i].paraValType = paraValType;
			
			switch(paraValType){
			case '1':
				conditionScriptObj.argument[i].paraVal = $("[name='flowVar']",_this).val();
				conditionScriptObj.argument[i].paraValName = $("[name='flowVar'] option:selected",_this).text();
				break;
			case '2':
				conditionScriptObj.argument[i].paraVal = $("[name='paraValID']",_this).val();
				conditionScriptObj.argument[i].paraValName = $("[name='paraVal']",_this).val();				
				break;
			}
		});
		json = $.extend(true,json,conditionScriptObj);
		return json;
	};

	var conditionScripts={};

	/**
	 * 条件脚本变更处理
	 * @param obj
	 * @returns
	 */
	function conditionScriptChange(obj){
		var me = $(obj);
		var json = me.data("conditionScriptObj");
		if(json){
			var params = json.argument;
			if($.type(params)=='string'){
				params = $.parseJSON(json.argument);
			}
			constructParamTable(params);
		}
	};

	/**
	 * 选择条件脚本
	 * @param obj
	 */
	function selectConditionScript(obj){
		var me = $(obj);
		ConditionScriptDialog({
			callback:function(data){
				var id = data.id;
				var json = getConditionScript(id);
				var idObj = me.parent().find("input[type='hidden']");
				var nameObj = me.parent().find("input[type='text']");
				if(json){
					idObj.val(json.id);
					nameObj.val(json.methodDesc);
					idObj.removeData("conditionScriptObj");
					idObj.data("conditionScriptObj",json);
					idObj.trigger("change");
				}
			}
		});
	};



	/**
	 * 根据条件脚本ID，取得脚本脚本
	 * @param id
	 * @returns
	 */
	function getConditionScript(id){
		if(conditionScripts[id]){
			json = conditionScripts[id];
			return json;
		}
		var url =__ctx+ "/platform/system/conditionScript/getJson.ht";
		var params={
				id:id
		};
		
		var json;
		$.ajax({
			url:url,
			async:false,
			data:params
		}).done(function(data){
			if(!data.status){
				json = data.conditionScript;
			}else{
				//TODO error handle
			}
		});
		conditionScripts[id]=json;
		return json;
	}

	/**
	 * 构造参数信息表
	 * @param params
	 * @returns
	 */
	function constructParamTable(params){
		if(!params){
			params=[];
		}
		var paramTableBody = $(".para-info-table tbody").empty();
		for(var i=0;i<params.length;i++){
			var p = params[i];
			var tr = constructParamTr(p);
			paramTableBody.append(tr);
			tr.data("param",p);
			$('[name="paraValType"]',tr).trigger("change");
		}
	};

	/**
	* 构造 参数 行
	*/
	function constructParamTr(p){
		var tr = $(".param-tr-template").clone().removeClass("param-tr-template");
		$("[name='paraName']",tr).text(p.paraName);
		$("[name='paraType']",tr).text(p.paraType);
		$("[name='paraDesc']",tr).text(p.paraDesc);
		return tr;
	};


	/**
	* 参数值来源 变更
	*/
	function paraValTypeChange(obj){
		var _this=$(obj);
		var paraValType = _this.val();
		var tr = _this.closest("tr");
		var p = tr.data("param");
		var paraValTd = _this.closest("td").next("td");
		var div=null;
		switch(paraValType){
		case '1':
			div = $("#template-container [name='flowVars-div']").clone();
			break;
		case '2':
			var input = getInput(p);
			div = $("#template-container [name='custom-div']").clone();
			div.append(input);
			if(p.dialog){
				var dialog = '<input type="button" value="…" id="dialog" dialog="'+p.dialog+'" param="'+p.target+'"/>';
				div.append(dialog);
			}
			break;
		}
		paraValTd.empty().append(div);
	};


	/**
	 * 操作类型变更处理
	 * @param obj 事件源对象
	 */
	function getInput(p){
		var ct = p.paraCt;
		ct = ""+ct;
		
		var valueName = "paraValID";
	//	var datefmt = "YYYY-MM-DD"
		
		var getNormalInput = function(){
			input = $("#normal-input").clone(true,true).removeAttr("id").attr("name",valueName);
			return input;
		};
		
		var getSelector = function(str){
			var value="paraVal";
			var control = $("#"+str).clone(true,true).removeAttr("id");
		 	$("input[type='text']",control).attr("name","paraVal");
		 	$("input[type='hidden']",control).attr("name",valueName);
		 	$("a.link",control).attr("name",value);
			return control;
		};
		
	//	var dateInput = function(){
	//		dateInput = $("#date-input").clone(true,true).removeAttr("id").attr("datefmt",datefmt).attr("name",valueName);
	//	};
		
		var dicInput = function(){
			var control = getDicControl(flowVarOptioin,container).attr("name",valueName);
			return control;
		};


		var input;
		switch(ct){
			case "4"://用户单选
			case "8"://用户多选
				str = "user-div";
				input = getSelector(str);
				break;
			case "5"://角色
			case "17"://角色
				str = "role-div";
				input = getSelector(str);
				break;
			case "6"://组织
			case "18"://组织
				str = "org-div";
				input = getSelector(str);
				break;
			case "7"://岗位
			case "19"://岗位
				str = "position-div";
				input = getSelector(str);
				break;
			default:
				input = getNormalInput();
		}
		return input;
	};

</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			 <span class="tbar-label">条件脚本添加对话框</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" onclick="formSave(this)" id="dataFormSave" href="javascript:;"><span></span>确定</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" onclick="dialog.close()"><span></span>关闭</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="ruleConditionScriptForm">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th>条件脚本:</th>
					<td>
						<input type="hidden" name="condScriptId" value="" onchange="conditionScriptChange(this)">
						<input type="text" name="condScriptName" readonly="readonly" />
						<a class="link detail" onclick="selectConditionScript(this)">选择</a>
					</td>
				</tr>
			</table>
			
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="10%">参数名称</th>
						<th width="25%">参数类型</th>
						<th width="25%">参数说明</th>
						<th  colspan="3">参数值</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
	
	<div id="template-container" style="display: none">
		<table>
			<tr class="param-tr-template param-tr">
				<td><span name="paraName"></span></td>
				<td><span name="paraType"></span></td>
				<td><span name="paraDesc"></span></td>
				<td>
					<select name="paraValType" onchange="paraValTypeChange(this)">
						<option value="1">流程变量</option>
						<option value="2">固定值</option>
					</select>
				</td>
				<td>
				</td>
			</tr>
		</table>
		
		<div  name="flowVars-div">
						<f:flowVar defId="${defId}" controlName="flowVar"></f:flowVar>
					</div>
		<div name="custom-div">
			<div name="paraValueDiv"></div>
		</div>
		
		<!-- 规则模板 -->
		<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
	</div>
</body>
</html>

