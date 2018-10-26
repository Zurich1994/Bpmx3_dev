<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>分配主表</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript">
/*KILLDIALOG*/
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

$(function(){
	$("#dataFormSave").click(assignMainTable);
});

function assignMainTable(){
	var subTableId=${subTableId};
	var checkIndex=$("#mainTableId ").get(0).selectedIndex; 
	if(checkIndex==-1){
		$.ligerDialog.error("没有选择主表!",'出错了');
		return;
	}
	
	var mainTableId=$("#mainTableId").val();
	var fkField=$("#fkField").val();
	var params={subTableId:subTableId,mainTableId:mainTableId,fkField:fkField};
	$.post('linkSubtable.ht' ,params, function(data) {
		var obj=new com.hotent.form.ResultMessage(data);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success("关联主表成功!",'提示信息',function(){
				dialog.close();
			});
	    }else{//失败
	    	$.ligerDialog.err('出错信息',"关联主表失败",obj.getMessage());
	    }
	});
}

</script>
</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				分配主表
		    </span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="javascript:;" onclick="dialog.close()"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form action="linkSubtable.ht" method="post">
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%">所属主表: </th>
					<td>
						<select id="mainTableId" name="mainTableId">
							<c:forEach items="${mainTableList}" var="mainTable">
								<option value="${mainTable.tableId}">${mainTable.tableDesc}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						关联字段
					</th>
					<td>
						<select name="fkField" id="fkField">
							<c:forEach items="${fieldList}" var="field">
								<option value="${field.fieldName}">${field.fieldDesc}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
