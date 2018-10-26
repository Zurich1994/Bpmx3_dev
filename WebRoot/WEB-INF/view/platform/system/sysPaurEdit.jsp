<%--
	time:2012-12-24 14:42:00
	desc:edit the SYS_PAUR
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 SYS_PAUR</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysPaurForm').form();
			$("a.save").click(function() {
				frm.setData();
				frm.ajaxForm(options);
				if(frm.valid()){
					form.submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/platform/system/sysPaur/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${sysPaur.paurid !=null}">
			        <span class="tbar-label">编辑SYS_PAUR</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加SYS_PAUR</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysPaurForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%">名称: </th>
					<td><input type="text" id="paurname" name="paurname" value="${sysPaur.paurname}"  class="inputText" validate="{required:false,maxlength:30}"  /></td>
				</tr>
				<tr>
					<th width="20%">别名:  <span class="required">*</span></th>
					<td><input type="text" id="aliasname" name="aliasname" value="${sysPaur.aliasname}"  readonly="readonly"  class="inputText" validate="{required:true,maxlength:30}"  /></td>
				</tr>
				<tr>
					<th width="20%">值:  <span class="required">*</span></th>
					<td><input type="text" id="paurvalue" name="paurvalue" value="${sysPaur.paurvalue}"  class="inputText" validate="{required:true,maxlength:50}"  />
						<input type="hidden" id="userid" name="userid" value="${sysPaur.userid}"  class="inputText" validate="{required:true,maxlength:22,number:true }"  />
					</td>
				</tr>				
			</table>
			<input type="hidden" name="paurid" value="${sysPaur.paurid}" />					
		</form>
		
	</div>
</div>
</body>
</html>
