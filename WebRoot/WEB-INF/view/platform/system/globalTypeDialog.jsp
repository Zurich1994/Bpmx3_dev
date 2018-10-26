<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类管理</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=globalType"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

var isAdd=${isAdd};

function showRequest(formData, jqForm, options) { 
	return true;
} 
  
$(function() {
	valid(showRequest,showResult);
	$("a.save").click(function(){
		$("#globalTypeForm").submit();
	});
	
	$("#typeName").change(function(){   
		 getFullSpell($(this).val());  				   
	  }); 
	$("input[name=nodeCodeType]").click(function(){   
		showOrHidNodeCode($(this).val());  				   
	  }); 
	showOrHidNodeCode($("input[name=nodeCodeType][checked=checked]").val()); 
});

function showOrHidNodeCode(nodeCodeType){
	if(nodeCodeType == "0"){
		$("#nodeCodeTh").html("<input type='text' id='nodeCode' name='nodeCode' value='${globalType.nodeCode}'  class='inputText'/>");
	}else if(nodeCodeType == "1"){
		if(!isAdd){//修改分类，不能选择流水号，只显示值
			$("#nodeCodeTh").html("<input type='text' id='nodeCode' name='nodeCode' value='${globalType.nodeCode}' disabled='disabled' class='inputText'/>");
		}else{
			getAllSerialnum();
		}
		
	}
}
//获取所有流水号
function getAllSerialnum(){
	var url=__ctx+"/platform/system/identity/getAllIdentity.ht";
	$.get(url,function(data){
		if(!data)return;
		var html=['<select id="nodeCode" name="nodeCode">'];
		for(var i=0,c;c=data[i++];){
			html.push('<option value="'+c.alias+'">'+c.name+'</option>');
		}
		html.push('</select>');
		$("#nodeCodeTh").html(html.join(''));
	});
};	
function getFullSpell(typeName){
	if($("#nodeKey").val()!="") return;
// 	var url="${ctx}/platform/system/globalType/getPingyin.ht";
// 	var params={typeName:typeName};
// 	$.post(url,params,function(result){
// 		$("#nodeKey").val(result); 
// 	});
	
	Share.getPingyin({
		input:typeName,
		postCallback:function(data){
			$("#nodeKey").val(data.output);
		}
	});
}

function showResult(responseText){
	var obj=new com.hotent.form.ResultMessage(responseText);
	if(!obj.isSuccess()) {
		$.ligerDialog.warn(obj.getMessage(),'提示信息');
		return;
	};
	if(isAdd){
		$.ligerDialog.confirm("添加成功，继续添加吗?",'提示信息',function(rtn){
			if(rtn){
				$("#typeName").val('');
				$("#nodeKey").val('');
			}
			else{
				$.ligerDialog.success("添加分类成功!",'提示信息',function(){
					dialog.get("sucCall")();
					dialog.close();
				});
			}
		});
	}
	else{
		$.ligerDialog.success("修改分类成功!",'提示信息',function(){
			dialog.close();
		});
	}
}
</script>
</head>
<body>
	<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><c:choose><c:when test="${isAdd}">添加分类</c:when><c:otherwise>编辑分类</c:otherwise></c:choose></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
					</div>
				</div>
		</div>
		<form id="globalTypeForm" method="post" action="save.ht">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<c:if test="${isAdd}">
								<tr>
									<th width="20%">父节点: </th>
									<td >${parentName}</td>
								</tr>
							</c:if>
							<tr>
								<th width="20%">名称:  <span class="required">*</span></th>
								<td ><input type="text" id="typeName" name="typeName" value="${globalType.typeName}"  class="inputText"/></td>
							</tr>
							<c:if test="${isDict}">
							<tr>
								<th width="20%">自编码:</th>
								<td >
									<div id="nodeCodeTh"></div>
									<input type="radio" name="nodeCodeType" value="0"  <c:if test="${globalType.nodeCodeType==0}">checked="checked"</c:if> <c:if test="${!isAdd}">disabled="disabled"</c:if> />手工录入
									<input type="radio" name="nodeCodeType" value="1" <c:if test="${globalType.nodeCodeType==1}">checked="checked"</c:if> <c:if test="${!isAdd}">disabled="disabled"</c:if>/>自动生成
								</td>
							</tr>
							</c:if>
							<tr>
								<th width="20%">节点Key:  <span class="required">*</span></th>
								<td ><input type="text" id="nodeKey" name="nodeKey" value="${globalType.nodeKey}"  class="inputText"/></td>
							</tr>

							<c:if test="${isDict}">
							<tr>
								<th width="20%">字典类型:</th>
								<td>
									<input type="radio" name="type" value="0"  <c:if test="${globalType.type==0}">checked="checked"</c:if>  />平铺
									<input type="radio" name="type" value="1" <c:if test="${globalType.type==1}">checked="checked"</c:if> />树形
								</td>
							</tr>
							</c:if>
					</table>
				</div>
				<input type="hidden" id="isPrivate" name="isPrivate" value="${isPrivate}"/>
				<input type="hidden" id="isRoot" name="isRoot" value="${isRoot}"/>
				<input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
				<input type="hidden" id="typeId" name="typeId" value="${globalType.typeId}" />
		</form>
</body>
</html>