<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 节点发生时间与发生量表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#nodetimeandcountForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#nodetimeandcountForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/nodetimeandcount/nodetimeandcount/nodetimeandcount/list.ht";
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
			    <c:when test="${not empty nodetimeandcountItem.id}">
			        <span class="tbar-label"><span></span>编辑节点发生时间与发生量表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加节点发生时间与发生量表</span>
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
	<form id="nodetimeandcountForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">节点发生时间与发生量表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">项目id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="projectId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:50}" lablename="项目id" value="${nodetimeandcount.projectId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">流程定义id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="defId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:50}" lablename="流程定义id" value="${nodetimeandcount.defId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">节点Id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="nodeId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="节点Id" value="${nodetimeandcount.nodeId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">发生时间与发生量:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="timeAndCount" class="inputText" type="text" isflag="tableflag" validate="{maxlength:200}" lablename="发生时间与发生量" value="${nodetimeandcount.timeAndCount}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段1:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain1" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段1" value="${nodetimeandcount.remain1}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段2:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain2" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段2" value="${nodetimeandcount.remain2}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段3:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain3" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段3" value="${nodetimeandcount.remain3}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段4:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain4" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段4" value="${nodetimeandcount.remain4}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${nodetimeandcount.id}"/>
	</form>
</body>
</html>
