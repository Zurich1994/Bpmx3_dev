
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>新建表单</title>
<%@include file="/commons/include/form.jsp" %>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDef"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormTableDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>


<style type="text/css">
	html,body{height:100%;width:100%; overflow: hidden;}
</style>


<script type="text/javascript">

	window.name="frmEdit";
	var designType="table_create",dType=${designType};
	
	$(function(){
		function showRequest(formData, jqForm, options) {
			return true;
		}
		valid(showRequest,showResponse);		
		$("#dataFormSave").click(function(){
			var rtn=$('#bpmFormDefForm').valid();
			if(!rtn) return;
			if(designType=='table_create'||designType=='table_parcel'){
				var tableId=$("#tableId").val();
				if(!tableId){
					$.ligerDialog.error("请您选择要生成的表或数据包","提示信息");
					return;
				}
				 else if(designType=='table_create')
				{$("#bpmFormDefForm").attr("action","selectTemplateczt.ht");}
				else
				{
				$("#bpmFormDefForm").attr("action","selectParcelTemplate.ht");
				}
			}
			else{
				$("#bpmFormDefForm").attr("action","chooseDesignTemplate.ht");
			}
			var formKey=$("#formKey").val();
			
			validExist(formKey,function(rtn){
				if(rtn>0){
					$.ligerDialog.error("您填写的别名系统中已存在!","提示信息");
				}
				else{
					$('#bpmFormDefForm')[0].submit();
				}
			})
		});
	
		if(dType==1){
			$("#table_tr").hide();
			designType='editor_design';
		}
		
		$("#subject").blur( function () {
			Share.setPingyin($(this),$("#formKey"));
		});

	});
	
	function validExist(formKey,callBack){
		var url= __ctx + "/platform/form/bpmFormDef/getCountByFormKey.ht";
		var obj={};
		obj.formKey=formKey;
		$.post(url, obj,function(data){
			callBack(data) ;
		});
	}
	
	function selectTable(){
	
		var callBack=function(tableId,tableName){		
			$("#tableId").val(tableId);
			$("#tableName").val(tableName);
		}
		var val = $("input:radio[name='designType']:checked").attr("id");
		if(!val)return;
		designType = val;
		if(designType=="table_parcel"){
			FormTableDialogA({callBack:callBack});
		}
		else{
			FormTableDialog({callBack:callBack});
		}
	    
		
	
	}
	function resetTable(){
		$("#tableId").val('');
		$("#tableName").val('');
	};
	function designTypeChange(){
		var val = $("input:radio[name='designType']:checked").attr("id");
		if(!val)return;
		designType = val;
		if(designType=="editor_design"){
			$("#table_tr").hide();
		}
		else{
			$("#table_tr").show();
		}
	};
	
	function getTableName(obj){
	    var value=$(obj).val()
		
	}


</script>

</head>
<body >
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="javascript:;"><span></span>下一步</a>
						
					</div>
				</div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-detail">
			<form  id="bpmFormDefForm" method="post" action="selectTemplateczt.ht" >
				

				<table cellpadding="1" cellspacing="1" class="table-detail">
				
					<tr>
						<th width="150">表单标题:</th> 
						<td><input id="subject" type="text" name="subject" class="inputText" size="30" value="${subject}"  /></td>
					</tr>
					<tr>
						<th width="150">表单别名:</th> 
						<td><input id="formKey" type="text" name="formKey" class="inputText" size="30" value="${formKey}" /></td>
					</tr>
					<tr>
						<th width="150">表单ID:</th> 
						<td><input id="formDefId" type="text" name="formDefId" class="inputText" size="30" value="${formDefId}" /></td>
					</tr>
					<tr>
						<th width="150">表单类型:</th>
						<td>
							<input class="catComBo" catKey="FORM_TYPE" valueField="categoryId" catValue="${categoryId}" name="typeName" height="150" width="200"/>
						</td>
					</tr>
					<tr>
						<th width="150">表单描述:</th>
						<td>
							<textarea rows="3" cols="35" id="formDesc" name="formDesc" class="textarea">${formDesc}</textarea>
						</td>
					</tr>
					<tr>
						<th width="150">设计类型:</th>
						<td>
							<input id="table_create"  onclick="designTypeChange()" name="designType" type="radio" <c:if test="${designType==0}"> checked="checked"</c:if> /><label for="table_create">通过表生成</label>
							<input id="editor_design" onclick="designTypeChange()" name="designType" type="radio" <c:if test="${designType==1}"> checked="checked"</c:if>  /><label for="editor_design">编辑器设计</label>
							<input id="table_parcel"  onclick="designTypeChange()" name="designType" type="radio" <c:if test="${designType==2}"> checked="checked"</c:if> /><label for="table_parcel">通过数据包生成</label>
						</td>
					</tr>
					<tr id="table_tr">
						<th width="150">表/数据包:</th>
						<td style="padding-top: 5px;">
							
							<input type="text" id="tableName" class="inputText" name="tableName" value="" readonly="readonly">
							<input type="hidden" id="tableId" name="tableId" value="">
							<a href='#' class='link search'  onclick="selectTable()" ></a>
							<a href='#' class='link redo' style='margin-left:10px;' onclick="resetTable()"><span>重选</span></a>
						</td>
					</tr>
					
				</table>
				
			</form>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>



					



