<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 更新账户信息</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#updateForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#updateForm').submit();
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
					
						window.location.href = "${ctx}/banking/banking/update/list.ht?name=${name}";
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
			    <c:when test="${not empty updateItem.id}">
			        <span class="tbar-label"><span></span>编辑更新账户信息</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加更新账户信息</span>
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
	<form id="updateForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="4" class="formHead">更新账户信息</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">用户名，由英文及数字组合而成:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名，由英文及数字组合而成" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" type="text" value="${update.USERID}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">密码:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PASSWORD" lablename="密码" class="inputText" validate="{maxlength:32,required:true}" isflag="tableflag" type="text" value="${update.PASSWORD}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">地址:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ADDRESS" lablename="地址" class="inputText" validate="{maxlength:100,required:true}" isflag="tableflag" type="text" value="${update.ADDRESS}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CITY" lablename="城市" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${update.CITY}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">州,2位州代码:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STATE" lablename="州,2位州代码" class="inputText" validate="{maxlength:2,required:true}" isflag="tableflag" type="text" value="${update.STATE}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">区号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ZIP" lablename="区号" class="inputText" validate="{maxlength:5,required:true}" isflag="tableflag" type="text" value="${update.ZIP}" /></span></td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PHONE" lablename="电话" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${update.PHONE}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">邮件地址:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" lablename="邮件地址" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" type="text" value="${update.EMAIL}" /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${update.id}"/>
	</form>
</body>
</html>
