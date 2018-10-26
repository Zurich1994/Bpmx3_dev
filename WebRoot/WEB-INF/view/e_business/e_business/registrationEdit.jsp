<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 用户信息表</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#registrationForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#registrationForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						
//var email = document.getElementById("EMAIL").value;
//var email1 = document.getElementById("EMAIL1").value;

	    //var password = document.getElementById("PASSWORD").value;
	    //var password1 = document.getElementById("PASSWORD1").value;
	    //if(email.equals(email1)&&password.equals(password1){
	window.location.href = "${ctx}/e_business/e_business/registrationSuccess.ht";}
	//else
	//{window.location.href = "${ctx}/registration/registration/registration/error.ht"
					//}
					else{
						window.location.href = "${ctx}/e_business/e_business/registration/list.ht";
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
			    <c:when test="${not empty registrationItem.id}">
			        <span class="tbar-label"><span></span>编辑用户信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>注册</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="registrationForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td style="word-break:break-all;" rowspan="1" colspan="5" class="formHead">注册<br /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户E-mail地址:</td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="EMAIL" id="EMAIL" lablename="用户E-mail地址" class="inputText" validate="{maxlength:30,required:true,email:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td rowspan="1" colspan="1" style="word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">确认E-mail地址:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="EMAIL1" id="EMAIL1" lablename="用户E-mail地址确认" class="inputText" validate="{maxlength:30,required:true,email:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">用户密码:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="PASSWORD" id="PASSWORD" lablename="用户密码" class="inputText" validate="{maxlength:32,required:true,英数字:true}" isflag="tableflag" type="password" /></span></td>
  </tr>
  <tr>
   <td style="word-break:break-all;" rowspan="1" colspan="1" class="formTitle" align="right" nowrap="nowarp">确认密码:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="PASSWORD1" id="PASSWORD1" lablename="用户密码确认" class="inputText" validate="{maxlength:32,required:true,英数字:true}" isflag="tableflag" type="password" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名字:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:100%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="FIRSTNAME" lablename="用户名字" class="inputText" validate="{maxlength:14,required:true,汉字:true}" isflag="tableflag" type="text" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户姓氏:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;">
   <span name="editable-input" style="display:inline-block;" isflag="tableflag">
   <input name="LASTNAME" lablename="用户姓氏" class="inputText" validate="{maxlength:15,required:true,汉字:true}" isflag="tableflag" type="text"  /></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${registration.id}"/>
	</form>
</body>
</html>
