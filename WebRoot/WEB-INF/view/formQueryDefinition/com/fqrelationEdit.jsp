<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 节点查询关联表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#fqrelationForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#fqrelationForm').submit();
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
						window.location.href = "${ctx}/formQueryDefinition/com/fqrelation/list.ht";
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
			    <c:when test="${not empty fqrelationItem.id}">
			        <span class="tbar-label"><span></span>编辑节点查询关联表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加节点查询关联表</span>
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
	<form id="fqrelationForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">节点查询关联表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">自定义查询ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="fqID" lablename="自定义查询ID" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${fqrelation.fqID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">流程ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="defID" lablename="流程ID" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${fqrelation.defID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">节点ID:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="nodeID" lablename="节点ID" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${fqrelation.nodeID}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${fqrelation.id}"/>
	</form>
</body>
</html>
