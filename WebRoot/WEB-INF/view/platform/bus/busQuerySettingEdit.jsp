<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>字段设置</title>
<%@include file="/commons/include/form.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript">
	/*KILLDIALOG*/
	var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)

	$(function() {
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
		var frm=$('#busQuerySettingForm');
		$("a.save").click(function() {
			setDisplayField();
			frm.setData();
			frm.ajaxForm(options);
			frm.submit();
		});
	});
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.success(obj.getMessage(),'提示',function(){
				//window.returnValue = "close";
				dialog.get('sucCall')("close");
				dialog.close();		
			});	
		} else {
			$.ligerDialog.error(obj.getMessage(),'提示');
		}
	}
	//显示字段
	function setDisplayField() {
		var fields = new Array();
		$("#displayTbl tr").each(function() {
			var me = $(this), obj = {};
			var name =$("[var='name']", me).val();
			if($.isEmpty(name))
				return true;
			obj.name =name;
			obj.desc = $("[var='desc']", me).val();
			obj.show = $("[var='show']", me).val();
			fields.push(obj);
		});
		$("#displayField").val(JSON2.stringify(fields));
	}
	
	function closeWin() {
		dialog.close();
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">字段设置</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link close" href="javascript:;" onclick="closeWin();"><span></span>关闭</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-grid table-list" cellpadding="0" cellspacing="0" border="0" id="displayTbl">
			<tr>
				<th width="70%">字段:</th>
				<th width="30%">是否显示</th>
			</tr>
			<c:forEach items="${busQuerySetting.fieldShowList}" var="fieldShow">
				<tr >
					<td>${fieldShow.name}(${fieldShow.desc})<input type="hidden" var="name" value="${fieldShow.name}"><input type="hidden" var="desc" value="${fieldShow.desc}"></td>
					<td><select var="show">
						<option value="0" <c:if test="${fieldShow.show==0}"> selected="selected"</c:if>>是</option>
						<option value="1"  <c:if test="${fieldShow.show==1}"> selected="selected"</c:if>>否</option>
					</select>
					</td>
				</tr>
			
			</c:forEach>
		</table>
		<form id="busQuerySettingForm" method="post" action="save.ht">
			<input type="hidden" id="id" name="id" value="${busQuerySetting.id}">
			<input type="hidden" id="tableName" name="tableName" value="${busQuerySetting.tableName}">
			<input type="hidden" id="userId" name="userId" value="${busQuerySetting.userId}">
			<textarea style="display: none;" id="displayField"
										name="displayField">${fn:escapeXml(busQuerySetting.displayField)}</textarea>
		</form>
		</div>
	</div>
</body>
</html>

