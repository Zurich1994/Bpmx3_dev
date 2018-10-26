<%--
	time:2012-01-14 15:10:58
	desc:edit the 发送消息
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>编辑 公告栏目</title>
<%@include file="/commons/include/form.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript">
	$(function() {
		if(${sysBulletinColumn.id==null}){
			$("#name").blur(function(){
				var obj=$(this);
				autoPingin(obj);
			});
		}
		$("a.save").click(function() {
			submitForm();
		});
	});
	
	function autoPingin(obj){
		var value=obj.val();
		Share.getPingyin({
			input:value,
			postCallback:function(data){
				$("#alias").val(data.output);
			}
		});
	}
	
	function submitForm() {
		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}
		var frm = $('#sysBulletinColumnForm').form();
		frm.ajaxForm(options);
		if (frm.valid()) {
			$('#sysBulletinColumnForm').submit();
		}
	}

	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(obj.getMessage() + ",是否继续操作","提示信息",
							function(rtn) {
								if (rtn) {
									window.location.href = window.location.href;
								} else {
									window.location.href = "${returnUrl}";
								}
							});
		} else {
			$.ligerDialog.error(obj.getMessage(), "提示信息");
		}
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">编辑公告栏目</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysBulletinColumnForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%">栏目名字:<span class="required red">*</span></th>
						<td><input type="text" id="name" name="name"
							value="${sysBulletinColumn.name}" lablename="栏目名字"
							validate="{required:true,maxlength:128}" isflag="tableflag" class="inputText" style="width: 250px;"/></td>
					</tr>
					<tr>
						<th width="20%">栏目别名:<span class="required red">*</span></th>
						<td><input type="text" id="alias" name="alias"
							value="${sysBulletinColumn.alias}"
							validate="{required:true,maxlength:128}" class="inputText" style="width: 250px;"/></td>
					</tr>
					<tr>
						<th width="20%">所属分公司:<span class="required red">*</span></th>
						<td>
							<select name="tenantid" class="inputText" style="width: 250px;">
								<option value="0" <c:if test="${sysBulletinColumn.tenantid==0}">selected="selected"</c:if>>所有公司</option>								
								<c:forEach items="${companyList }" var="company">
									<option value="${company.COMPANYID }" 
										<c:if test="${sysBulletinColumn.tenantid==company.COMPANYID}">selected="selected"</c:if>>${company.COMPANY }</option>										
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th width="20%">是否可用:</th>
						<td><select id="status" name="status" class="inputText" >
								<option value="1"
									<c:if test="${sysBulletinColumn.status==1}">selected="selected"</c:if>>启用</option>
								<option value="0"
									<c:if test="${sysBulletinColumn.status==0}">selected="selected"</c:if>>禁用</option>
						</select></td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${sysBulletinColumn.id}" /> 
				<input type="hidden" name="createtime" value="<fmt:formatDate value='${sysBulletinColumn.createtime}' type='date'/>" />
				<input type="hidden" name="creatorid" value="${sysBulletinColumn.creatorid}" /> 
				<input type="hidden" name="creator" value="${sysBulletinColumn.creator}" />
			</form>
		</div>
	</div>
</body>
</html>
