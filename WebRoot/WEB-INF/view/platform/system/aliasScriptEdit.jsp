<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>编辑别名脚本</title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htDicCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=conditionScript"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptEdit.js"></script>
<script type="text/javascript">

	$(function(){
		changeScriptType($('#scriptType'));
		$('#isReturnValue').trigger('change');
		//方法转换
		$("select[name='className']").change(classNameChange).trigger("change");
		//别名检测
		checkAliasName();
		//返回提示
		$('#aliasScriptEdit').ajaxForm({success:showResponse });
		
		//初始化脚本编辑器
		$("#script_editor").click(function(){
			ScriptDialog({
				callback:function(obj){
					InitMirror.editor.setCode(obj);
				}
			});
		});
		
		//初始化保存按键
		$("a.save").click(function() {
			InitMirror.save();
			var json = [];
			$('.params').each(function(){
				var obj = {};
				obj.fieldDesc = $(this).find('#fieldDesc').val();
				obj.fieldName = $(this).find('#fieldName').val();
				json.push(obj);
			});
			var result = JSON2.stringify(json).replaceAll('\"', "'");
			$('#returnParamJson').val(result);
			save('aliasScriptEdit');
		});
		
		$('td').delegate('a.link.add', 'click', function(){
			var data = [{fieldName:'', fieldDesc:''}];
			appendTemplate(data);
		});
		$('td').delegate('a.link.del', 'click', function(){
			$(this).closest('tr').remove();
		});
		var returnParamJson = "${aliasScript.returnParamJson}";
		if(returnParamJson){
			returnParamJson = eval("("+returnParamJson+")");
			appendTemplate(returnParamJson);
		}
		$('[name="returnType"]').change(function(){
			var type = $(this).val();
			if(type == 'java.util.List' || type == 'java.util.Set'){
				$('#returnValueTbody').show();
				$('#isReturnValue').trigger('change');
			}else {
				$('#returnValueTbody').hide();
				$('#isReturnValue').trigger('change');
			}
		});
		$('#isReturnValue').change(function(){
			var value = $(this).val();
			if(value == 1) {
				$('#returnParamTbody').show();
			}else {
				$('#returnParamTbody').hide();
			}
		});
	});
	
	function appendTemplate(data){
		var template = $('#template').text();
		var templateHtml = easyTemplate(template, data).toString();
		$('#returnParam').append(templateHtml);
	}
	
	//类名选择事件
	function classNameChange(){
		var className = $(this).val();
		if(!className)return;
		
		var match = /^.*\.(\w*)$/.exec(className),
			name = '';
		if(match!=null){
			name = match[1];
		}
		if(!name)return;
		name = name.toLowerCase().split("",1) + name.slice(1);
		$("input[name='classInsName']").val(name);
		var url = __ctx + '/platform/system/aliasScript/getMethodsByName.ht?name='+className;
		getMethods(url);
	};
	
	
	//类名选择事件
	function changeScriptType(obj){
		var scriptType = $(obj).val();
		//脚本类型（自定义：custom）
		if(scriptType=='custom'){
			$('#script_editor').css("display","block");
			$("tr[name='defaultTbody']").css("display","none");
			$("tr[name='customTbody']").removeAttr("style");
			$('.returnTbody').hide();
		}else{
			$('#script_editor').css("display","none");
			$("tr[name='defaultTbody']").removeAttr("style");
			$("tr[name='customTbody']").css("display","none");
			$('[name="returnType"]').trigger('change');
		}
		
	};
	
	
	
	//类名选择事件
	function checkAliasName(){
		
		$("input[name='aliasName']").blur(function(){
			var me = $(this);
			var aliasName = me.val();
			var valueStr = $("input[name='id']").val();
			if(typeof(valueStr)!=undefined && valueStr!=null && valueStr!=''){  //修改时如果
				//$("input[name='aliasName']").attr("readonly","true");
				var oldName = $("#aliasNameOld").val();
				if(oldName==aliasName){
					return;                      //如果是修改情况，别名和以前的相同的就不用检测了
				}
			}
			var path =  __ctx + '/platform/system/aliasScript/checkAliasName.ht';
			if(typeof(aliasName)!=undefined&&aliasName!=null&&aliasName!=''){
				$.post(path,{aliasName:aliasName},function(data){			
					var json = eval("("+data+")");
					if(json.isSuccess==1){
						me.val("");
						$.ligerDialog.warn(json.msg);
					}
			    });
			}
			
		});
	};
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑别名脚本</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" ><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link preview" onclick="previewScript()" ><span></span>预览</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
					<div class="group">
						<a class="link update" id="script_editor" href="javascript:;"><span></span>脚本编译器</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="aliasScriptEdit" method="post" action="save.ht">
				<input id="typeName" type="hidden" value="text" />
				<div class="panel-detail">
				<input type="hidden" name="id" value="${aliasScript.id}"/>
				<input type="hidden" id="aliasNameOld" value="${aliasScript.aliasName}"/>
				<input type="hidden" id="methodName" value="${aliasScript.methodName}"/>
				<textarea class="hidden" name="argument">${aliasScript.argument}</textarea>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
					
					<tr>
						<th width="20%">脚本别名:</th>
						<td>
							<input type="text" name="aliasName" size="100" value="${aliasScript.aliasName}" validate="{'required':true}" />
						</td>
					</tr>
					<tr>
						<th width="20%">脚本描述:</th>
						<td>
							<input type="text" name="aliasDesc" size="100" value="${aliasScript.aliasDesc}"/>
						</td>
					</tr>
					<tr>
						<th width="20%">脚本类型:</th>
						<td>
							<select id="scriptType" name="scriptType" onchange="changeScriptType(this);">
								<option value="default" <c:if test="${aliasScript.scriptType eq 'default'}">selected</c:if>>系统默认</option>
								<option value="custom" <c:if test="${aliasScript.scriptType eq 'custom'}">selected</c:if>>自定义</option>
							</select>
						</td>
					</tr>
					
					
					<tr name="defaultTbody" >
						<th width="20%">脚本所在类的类名:</th>
						<td>
							<select name="className">
								<c:forEach items="${implClasses}" var="implClass">
									<option value="${implClass.name}"<c:if test="${aliasScript.className eq implClass.name}"> selected="selected"</c:if>>${implClass.name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr name="defaultTbody" >
						<th width="20%">调用类的对象名:</th>
						<td>
							<input type="text" size="100" readonly="readonly" value="${aliasScript.classInsName}" name="classInsName" />
						</td>
					</tr>
					<tr name="defaultTbody" >
						<th width="20%">调用方法:</th>
						<td>
							<select name="methodName">
							</select>
						</td>
					</tr>
					<tr name="defaultTbody" >
						<th width="20%">调用的方法的描叙:</th>
						<td>
							<input type="text" size="100" name="methodDesc" value="${aliasScript.methodDesc}"/>
						</td>
					</tr>
					<tr name="defaultTbody" >
						<th width="20%">方法返回类型:</th>
						<td>
							<input type="text" size="100" readonly="readonly" name="returnType" value="${aliasScript.returnType}"/>
						</td>
					</tr>
					<tr name="defaultTbody" >
						<th width="20%">参数信息:</th>
						<td id="paraInfo">
						
						</td>
					</tr> 
							
					<tr name="customTbody" >
						<th width="20%">脚本内容:</th>
						<td>
						   <%-- <textarea type="text" id="scriptComten" name="scriptComten" rows="25" cols="200">${aliasScript.scriptComten}</textarea> --%>
						   <textarea type="text" id="scriptComten" codemirror="true"
								mirrorheight="400px" name="scriptComten" rows="30" cols="200">${aliasScript.scriptComten}</textarea>
						</td>
					</tr>

					<tr>
						<th width="20%">是否启用:</th>
						<td>
							<select name="enable">
								<option value="0"<c:if test="${aliasScript.enable eq 0}"> selected="selected"</c:if>>是</option>
								<option value="1"<c:if test="${aliasScript.enable eq 1}"> selected="selected"</c:if>>否</option>
							</select>
						</td>
					</tr>
					<tr id="returnValueTbody" class="returnTbody">
						<th width="20%">是否配置返回值:</th>
						<td>
							<select name="isReturnValue" id="isReturnValue">
								<option value="0"<c:if test="${aliasScript.isReturnValue eq 0}"> selected="selected"</c:if>>否</option>
								<option value="1"<c:if test="${aliasScript.isReturnValue eq 1}"> selected="selected"</c:if>>是</option>
							</select>
						</td>
					</tr>
					<tr id="returnParamTbody" class="returnTbody hidden">
						<th width="20%">返回值信息:</th>
						<td>
						<a class="link add" href="javascript:;"><span></span>添加</a>
						<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0" id="returnParam">
						<thead>
						<tr>
							<th style="text-align:left;padding-left:10px;">参数描述</th>
							<th style="text-align:left;padding-left:10px;">参数名</th>
							<th style="text-align:left;padding-left:10px;">管理</th>
						</tr>
						</thead>
						</table>
						</td>
					</tr>
					<tr><th>&nbsp;</th><td>&nbsp;</td></tr>
				</table>
				</div>
				<input type="hidden" name="returnParamJson" id="returnParamJson" value="${aliasScript.returnParamJson}">
			</form>
		</div>
	</div>
	<div class="hidden">
		<div id="para-txt">
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="25%">参数信息</th>
						<th width="25%">参数类型</th>
						<th width="25%">是否必填</th>
						<th width="25%">参数说明</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" name="paraName" value="${aliasScript.aliasName}"/></td>
						<td><span name="paraType"></span></td>
						<td><input type="checkbox" name="isRequired" value="1"></td>
						<td><input type="text" name="paraDesc"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<textarea style="display: none;" id="template">
		<#list data as obj>
		<tr class="params">
			<td><input type="text" id="fieldDesc" value="\${obj.fieldDesc}"/></td>
			<td><input type="text" id="fieldName" value="\${obj.fieldName}"/></span></td>
			<td><a href="javascript:;" class="link del">删除</a></td>
		</tr>
		</#list>
	</textarea>
</body>
</html>

