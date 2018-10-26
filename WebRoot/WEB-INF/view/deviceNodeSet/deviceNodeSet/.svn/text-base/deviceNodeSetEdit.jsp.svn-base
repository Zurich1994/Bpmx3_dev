<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 设备节点部署表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#deviceNodeSetForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#deviceNodeSetForm').submit();
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
						window.location.href = "${ctx}/deviceNodeSet/deviceNodeSet/deviceNodeSet/list.ht";
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
			    <c:when test="${not empty deviceNodeSetItem.id}">
			        <span class="tbar-label"><span></span>编辑设备节点部署表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加设备节点部署表</span>
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
	<form id="deviceNodeSetForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" cellspacing="0" cellpadding="2" border="1" uetable="null">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">设备节点部署表</td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">业务IP:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="businessIP" isflag="tableflag" validate="{maxlength:50}" lablename="业务IP" value="${deviceNodeSet.businessIP}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程定义ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="actdefid" isflag="tableflag" validate="{maxlength:64}" lablename="流程定义ID" value="${deviceNodeSet.actdefid}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">流程节点ID:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="nodeid" isflag="tableflag" validate="{maxlength:20}" lablename="流程节点ID" value="${deviceNodeSet.nodeid}" /></span></td>
  </tr>
  <tr>
   <td class="formTitle" style="width:20%" nowrap="nowrap" align="right">设备表:</td>
   <td class="formInput" style="width:80%"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="deviceTable" isflag="tableflag" validate="{maxlength:50}" lablename="设备表" value="${deviceNodeSet.deviceTable}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${deviceNodeSet.id}"/>
	</form>
</body>
</html>
