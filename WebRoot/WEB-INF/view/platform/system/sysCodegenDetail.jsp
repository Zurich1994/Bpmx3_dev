<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代码生成</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/foldBox.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/absoulteInTop.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
<script type="text/javascript">
	var form;
	$(function() {
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		form=$("#codeForm").form();
		form.ajaxForm(options);
		$("#codeDetail").ligerLayout({ 
			topHeight:40,
			leftWidth: '25%',
			height: '95%',
			InWindow: false,
			allowLeftCollapse:true,
			rightWidth:'73%',
			centerWidth:'2%',
			allowRightResize:false,
		 	allowRightCollapse:false
		});
		initTable();
		$("#dataFormSave").click(codegen);
	});
	
	
	function codegen(){
		var formDefIds=$("#formDefIds").val();
		var hasTemp=$('[name="templateId"]:checked').length;
		var hasTable=$("#codeForm").find('[name="tableId"]').length;
		var isZip=$("#isZip").attr("checked");
		
		alert("formDefIds="+formDefIds+"hasTemp="+hasTemp+"hasTable="+hasTable+"isZip="+isZip);
		
		if(formDefIds==''){
			$.ligerDialog.error('还未选择任何自定义表单','提示信息');
			return ;
		}
		if(!hasTable){
			$.ligerDialog.error('还未选择任何自定义表','提示信息');
			return;
		}
		if(hasTemp==0){
			$.ligerDialog.error('还未选择任何模版','提示信息');
			return;
		}
		if(isZip){
			var path=$("#folderPath").val();
			if(path==''){
				$.ligerDialog.error('请填写打包文件存放的路径！','提示信息');
				return;
			}
		}
		if(form.valid()){
			$("tr[type='append']").remove();
			$("#codeForm").submit();
		}
		
	}
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.success(obj.getMessage(),"成功", function() {
				window.close();
				$("#codeForm").resetForm();
			});
		} else {
			$.ligerDialog.error(obj.getMessage(),"提示信息");
		}
	}
	
	function initTable(){
		var tree=window.parent.tableTree;
		var formDefIds=[];
		try{
			var nodes=tree.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				var node=nodes[i];
				var formDefId=node.formDefId;
				formDefIds[i]=formDefId;
				$.post("${ctx}/platform/form/bpmFormDef/getTableInfo.ht?formDefId="+formDefId,function(data){
					for(var i= 0,c;c=data[i++];){
						var className=$.getFirstUpper(c.tableName);
						var classVar=$.getFirstLower(c.tableName);
						var row=$("tr[type='append']").clone();
						row.removeAttr("type").removeAttr("style");
						row.find("td[name='tableDesc']").text(c.tableDesc);
						row.find("input[name='tableId']").val(c.tableId);
						row.find('input[name="className"]').val(className);
						row.find('input[name="classVar"]').val(classVar);
						$("#tableVarSet").append(row);
					}
				})
			}
			$("#formDefIds").val(formDefIds.toString());
		}catch(e){}
		
	}
	
	function selectFlow(){
		BpmDefinitionDialog({isSingle:true,showAll:1,returnDefKey:true,callback:function(defIds,subjects,defKeys){
			$("#flowName").val(subjects);
			$("#defKey").val(defKeys);
		}});
	}
</script>
<style type="text/css">
	html {height: 100%}
	body {padding: 0px;margin: 0;overflow-y: hidden;overflow-x: hidden;}
	#codeDetail {width: 100%;margin: 0;padding: 0;}
	.l-layout-top {
	border-style: none none solid none;
	}
	.shortLi { min-width:150px!important;}
	.row li { min-width:350px;}
	.inputText { width:180px;}
	.label { width:93px;}
	.h100{height: 100%;}
	.l-layout-left{
		overflow-y:scroll;
	}
</style>
</head>
<body>
	<div class="panel h100">
		<div class="panel-top ">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link run" id="dataFormSave" href="javascript:;"><span></span>生成</a></div>
					<div class="group"><a class="link download" href="downLoadZip.ht" target="_blank"><span></span>下载</a></div>
				</div>
			</div>
		</div>
		<div class="panel-body h100">
		<form method="post" id="codeForm"  action="codegenZip.ht" class="h100" >
		<div id="codeDetail" style="bottom: 1px; top: 1px;display:inline-block;overflow-y:scroll;" class="h100">
			<div position="top">
				<ul class="row">
					<li><!-- <span class="label">是否覆盖原有文件:</span><input type="checkbox" name="override" value="1" /> -->
						<span class="label">流程定义:</span>
							<input type="text" id="flowName" name="flowName" readonly="readonly"  class="inputText" value="${flowName }" />
							<input type="button" value="..." onclick="selectFlow()"/>
							<input type="hidden" name="defKey" id="defKey" value="${defKey }"/>
						<%-- <span class="label">项目路径:</span><input type="text" class="inputText" id="baseDir" name="baseDir" value="${baseDir }" validate="{required:true}"/> --%>
						<span class="label">模块名称:</span><input type="text" class="inputText" id="system" name="system" value="${system }" validate="{required:true}" />
					</li>
					<!--<li class="shortLi"> <span class="label">是否打包生成文件:</span><input type="checkbox" id="isZip" name="isZip" value="1" />
					<span class="label">打包文件存放路径:</span><input type="text" class="inputText"  id="folderPath" name="folderPath" value="" /> 
					</li>-->
				</ul>
			</div>
			<div position="left" title="代码生成器模版" id="tempalteManage" style="top: 45 ;overflow-y:auto;">
				<table cellpadding="1" class="table-grid table-list" cellspacing="1" id="templates">
						<tr >
							<th><input type="checkbox" id="chkall"/></th>
							<th>模版名称</th>
							<th>别名</th>
						</tr>
						<c:forEach var="template" items="${templateList}" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" name="templateId" class="pk" value="${template.id}" id="templateId" >
							</td>
							<td>
								${template.templateName}
							</td>
							<td>
								${template.templateAlias}
							</td>
							<input type="hidden" name="templateName" value="${template.templateName}"/>
						</tr>
						</c:forEach>
					</table>
			</div>
			<div position="center"></div>
			<div position="right" title="代码信息" id="tableManage" style="top: 45">
				<table id="tableVarSet" cellpadding="1" class="table-grid table-list" cellspacing="1">
      				<tr>
      					<th>
      						自定义表
      					</th>
      					<th>
      						包名(package)
      					</th>
      					<th>
      						类名(class)
      					</th>
      					<th>
      						变量名(classVar)
      					</th>
      				</tr>
      				<tr type="append" style="display:none">
      					<td name="tableDesc">
      					</td>
      					
      					<td>
      						<input type="text" class="inputText" name="packageName" value="" validate="{required:true}"/>
      					</td>
      					<td>
      						<input type="text" class="inputText" name="className" value="" validate="{required:true}"/>
      					</td>
      					<td>
      						<input type="text" class="inputText" name="classVar" value="" validate="{required:true}"/>
      					</td>
      					<input type="hidden" name="tableId" value=""/>
      				</tr>
      			</table>
			</div>
		</div>
		<input type="hidden" id="formDefIds" name="formDefIds" value=""/>
		</form>
		</div>
	</div>
</body>
</html>