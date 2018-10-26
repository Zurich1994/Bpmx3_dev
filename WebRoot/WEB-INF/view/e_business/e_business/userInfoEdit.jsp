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
			var frm=$('#userInfoForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#userInfoForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			var EMAIL=document.getElementById("EMAIL").value;
			
			if (obj.isSuccess()) {
		
				var url = __ctx + "/e_business/e_business/userInfo/Match.ht?EMAIL="+EMAIL;
				window.location.href=url;
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
			    <c:when test="${not empty userInfoItem.id}">
			        <span class="tbar-label"><span></span>编辑用户信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:;"><span></span>登陆</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="userInfoForm" method="post" action="match.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td style="word-break:break-all;" rowspan="1" colspan="2" class="formHead">登陆<br /></td>
  </tr>
  <tr>
   <td class="formTitle" align="right" nowrap="nowarp" width="359">用户E-mail地址:</td>
   <td class="formInput" style="word-break:break-all;" width="737"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" id="EMAIL" lablename="用户E-mail地址" class="inputText" validate="{maxlength:30,required:true,email:true}" isflag="tableflag" type="text" value="${userInfo.EMAIL}" /></span></td>
  </tr>
  <tr>
   <td colspan="1" rowspan="3" class="formTitle" align="right" nowrap="nowarp" width="359">用户密码:</td>
   <td colspan="1" rowspan="3" class="formInput" style="word-break:break-all;" width="737"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PASSWORD" id="PASSWORD" lablename="用户密码" class="inputText" validate="{maxlength:32,required:true,英数字:true}" isflag="tableflag" type="password" value="${userInfo.PASSWORD}" /></span></td>
  </tr>
  <tr></tr>
  <tr></tr>
  <tr>
 
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${userInfo.EMAIL}"/>
	</form>
</body>
</html>
