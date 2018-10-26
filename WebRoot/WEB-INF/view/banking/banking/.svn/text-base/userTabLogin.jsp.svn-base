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
			var frm=$('#userTabForm').form();
			$("a.save").click(
			    function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
		 			if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#userTabForm').submit();
				}
			}
			);
		});

	function check(){
		alert("aaa");

	//	var name = document.getElementById("name").value;
	
	//	var password = document.getElementById("password").value;
	//	var url = __ctx + "/UserTab/UserTab/userTab/check.ht?name="+name+"&password="+password;
          var sfForm = document.createElement("userTabForm"); 
		  document.body.appendChild(sfForm);  
          sfForm.method = "post"; 
          sfForm.action = "yanzheng.ht"; 
          sfForm.submit(); 
          
          

	}

		function showResponse(responseText) {
	//		var obj = new com.hotent.form.ResultMessage(responseText);
	//		if (obj.isSuccess()) {
	//		$.ligerDialog.confirm(obj.getMessage()+",是否登陆","提示信息", function(rtn) {
	//				if(rtn){
	//					window.location.reload();
	//		window.location.href = "${ctx}/UserTab/UserTab/userTab/save.ht";
	 
	       
	    	var name=document.getElementById("name").value;
			var password = document.getElementById("password").value;
	    	var url = __ctx + "/banking/banking/userTab/check.ht?name="+name+"&password="+password;
			
			location.href=url;	
			
			
	//			}else{
					
		
	//					window.location.reload();
	//				}
	//			});
	//		} else {
	//			$.ligerDialog.error(obj.getMessage(),"提示信息");
	//		}
		}
	</script>                
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty userTabItem.id}">
			        <span class="tbar-label"><span></span>编辑用户信息表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加用户信息表</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		
		
		
		
	</div>
	<form id="userTabForm" method="post" href="javascript:;" action="yanzheng.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">登陆界面</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" id="name" lablename="用户名,由英文及数字组合而成" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" type="text" value="${userTab.USERID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PASSWORD" id="password" lablename="密码" class="inputText" validate="{maxlength:32,required:true}" isflag="tableflag" type="password" value="${userTab.PASSWORD}" /></span></td>
  </tr>

 </tbody>
</table>
<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" onclick="check()" ><span></span>登录</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
			</div>
		</div>
		<input type="hidden" name="id" value="${userTab.id}"/>
	</form>
</body>
</html>
