<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>WEB服务数据模板编辑</title>
<style type="text/css">
	html,body{
		overflow:auto;
	}
	.para-info-table th, .para-info-table td{
		text-align: center;
		height: 32px;
	}
	.para-info-table td{
		padding:5px;
	}
	input{
		width:180px;
	}
	select{
		min-width:185px;
		height:24px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
	var editors = InitMirror.options.editors ;
	$(function(){
		$("select[name='setId']").change(aliasChange).trigger("change");
		$('#wsTemplateDataEdit').ajaxForm({success:showResponse });
		$("a.done").click(doExecute);
		$('a.detail').click(doPrase);
		$('a.save').click(function(){
			var template = editors[2].editor.getCode();
			var script = editors[1].editor.getCode();
			$('[name="template"]').val(template);
			$('[name="script"]').val(script);
			$('#wsTemplateDataEdit').submit();
		})
	});
	function showResponse(r){
		var data = eval("("+r+")");
		if(data.result){
			$.ligerDialog.success(data.message,'成功',function(){
				window.location.href = 'list.ht';
			});
		}
		else{
			$.ligerDialog.warn(data.message,'出错了');
		}
	}
	//service别名选择事件
	function aliasChange(){
		var setId = $(this).val();
		if(!setId)return;
		$("#paraInfo").html("");
		if(editors[0]){
			editors[0].editor.setCode("");
		}
		$.post('${ctx}/platform/system/wsDataTemplate/getWsDocumentById.ht',{'setId':setId},function(doc){
			if(doc){
				var docObj = $.parseJSON(doc);
				initInputs(docObj.inputs);
			}
			else{
				$.ligerDialog.error('出错了','出错了');
			}
		});
	};
	function initInputs(list){
		if(!list||list.length==0)return;
		var ary = [];
		for(var i=0,c;c=list[i++];){
			if(c.bindingType=="2"){
				ary.push(c);
			}
		}
		
		var template = $("#param_tr").val();
			
		var html = easyTemplate(template,ary).toString();
		$("#paraInfo").html(html);
	};
	function doExecute(){
		//InitMirror.editor.getCode();
		var rtn=$("#wsTemplateDataEdit").form().valid();
		if(!rtn) {
			$.ligerDialog.warn("请填写参数信息！",'提示');
			return false ;
		}
		var XMLEditor = editors[0].editor;
		XMLEditor.setCode('');
		$.ligerDialog.waitting('请稍候...');
		var url = __ctx+'/platform/bpmCommonWsSet/bpmCommonWsSet/doExecute.ht',
			parent = $(".para-info-table"),
			setId = $("[name='setId']").val(),
			json = [];
		$("tbody > tr",parent).each(function(){
			var me = $(this),
				bindingVal = $("span[name='bindingVal']",me).text(),
				javaType = $("input[name='javaType']",me).val(),
				testValObj = $("[name='testVal']",me);
			
			var	testVal = testValObj.val();
			if(testValObj.length > 1){
				testVal = [];
				testValObj.each(function(){
					testVal.push($(this).val());
				});
			}
			
			json.push({bindingVal:bindingVal,javaType:javaType,testVal:testVal});
		});
		json = JSON2.stringify(json);
		
		$.post(url,{setId:setId,json:json},function(d){
			$.ligerDialog.closeWaitting();
			var data = $.parseJSON(d);
			if(data.result){
				XMLEditor.setCode(data.message);
			}
			else{
				$.ligerDialog.error('出错了' + data.message);
			}
		});
	};
	function doPrase(){
		var rtn=$("#wsTemplateDataEdit").form().valid();
		if(!rtn){
			$.ligerDialog.warn("请填写参数信息！",'提示');
			return false ;
		}
		var templateEditor = editors[2].editor;
		var returnXML = editors[0].editor.getCode();
		var template = templateEditor.getCode();
		var parseScript = editors[1].editor.getCode();
		if(returnXML.trim().length<=2){
			$.ligerDialog.warn('返回的XML不能为空！','提示');
			return false ;
		}
		if(parseScript.trim().length<=2){
			$.ligerDialog.warn('解析脚本不能为空！','提示');
			return false ;
		}
		var requestObj = {};
		requestObj.returnXML = returnXML;
		requestObj.template = template;
		requestObj.parseScript = parseScript;
		//templateEditor.setCode(editors[1].editor.getCode());
		$.ligerDialog.waitting('请稍候...');
		$.post(__ctx+'/platform/system/wsDataTemplate/parse.ht' ,requestObj ,function(d){
				$.ligerDialog.closeWaitting();
				var data = $.parseJSON(d);
				if(data.result){
					templateEditor.setCode(data.template);
					$('#messageDiv').html(data.message);
					var options = {};
					options.target = $('#messageDiv');
					options.width = '500';
					options.height = '400';
					
					$.ligerDialog.open(options);
				}
				else{
					$.ligerDialog.error('出错了' + data.message);
				}
		});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">WEB服务数据模板编辑</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<form id="wsTemplateDataEdit" method="post" action="save.ht">
			<div class="panel-detail">
			<input type="hidden" name="id" value="${wsDataTemplate.id}"/>
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
				<tr>
					<th width="20%">名称:</th>
					<td><input type="text" name="name" value="${wsDataTemplate.name}"/></td>
				</tr>
				<tr>
					<th width="20%">别名:</th>
					<td>
						<select name="setId">
							<c:forEach items="${bpmCommonWsSetList}" var="bpmCommonWsSet">
								<option value="${bpmCommonWsSet.id}" <c:if test='${bpmCommonWsSet.id == wsDataTemplate.serviceId}'>selected="selected"</c:if>>${bpmCommonWsSet.alias}</option>
							</c:forEach>
						</select>
						<a class="link done" href="javascript:;"><span></span>调用</a>
					</td>
				</tr>
				<tr>
					<th width="20%">参数信息:</th>
					<td>
						<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
						<thead>
							<tr>
								<th width="25%">参数名</th>
								<th width="20%">参数类型</th>
								<th width="30%">参数值</th>
							</tr>
						</thead>
						<tbody id="paraInfo">
						</tbody>
						</table>
					</td>
				</tr> 
				<tr>
					<th width="20%">返回的XML:</th>
					<td>
						<textarea id="xmlScript" name="returnXML" id="returnXML" rows="26" codemirror="true" mirrorheight="200px"></textarea>
					</td>
				</tr> 
				<tr>
					<th width="20%" rowspan="2">解析脚本:</th>
					<td>
						<input type="hidden" name="script" value='${wsDataTemplate.script}' />
						<textarea id="parseScript" codemirror="true" mirrorheight="200px" rows="26" cols="70">${wsDataTemplate.script}</textarea>
					</td>
				</tr>
				<tr>
					<td style="line-height: 30px;"><a class="link detail" href="javascript:;"><span></span>解析</a></td>
				</tr>
				<tr>
					<th width="20%">模板:</th>
					<td>
						<input type="hidden" name="template" value='${wsDataTemplate.template}' />
						<textarea id="templateScript" codemirror="true" mirrorheight="200px" rows="26" cols="70">${wsDataTemplate.template}</textarea>
					</td>
				</tr> 
			</table>
			</div>
		</form>
		</div>
	</div>
	
	<div class="hidden">
		<textarea id="param_tr">
			<#list data as obj>
			<tr>
				<td>
				<input type="hidden" name="javaType" value="\${obj.javaType}"/>
				<span name="bindingVal">\${obj.bindingVal}</span>
				</td>
				<#if (obj.javaType == 1)>
				<td>字符串</td>
				<td>
					<input type="text" name="testVal" validate="{required:true}"/>
				</td>
				<#elseif (obj.javaType == 2)>
				<td>数字</td>
				<td>
					<input type="text" name="testVal" validate="{required:true}"/>
				</td>
				<#else>
				<td>日期</td>
				<td>
					<input type="text" name="testVal" class="inputText date" validate="{required:true}"/>
				</td>
				</#if>
			</tr>
			</#list>
		</textarea>
	</div>
	<div id="messageDiv"></div>
</body>
</html>