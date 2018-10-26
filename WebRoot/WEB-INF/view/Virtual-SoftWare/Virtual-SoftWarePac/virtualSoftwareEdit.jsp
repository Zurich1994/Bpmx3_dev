<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 虚拟机-软件表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#virtualSoftwareForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#virtualSoftwareForm').submit();
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
						window.location.href = "${ctx}/Virtual-SoftWare/Virtual-SoftWarePac/virtualSoftware/list.ht";
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
			    <c:when test="${not empty virtualSoftwareItem.id}">
			        <span class="tbar-label"><span></span>编辑虚拟机-软件表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加虚拟机-软件表</span>
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
	<form id="virtualSoftwareForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">虚拟机-软件表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">序号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ID" lablename="序号" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${virtualSoftware.ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">虚拟机编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Virtual_ID" lablename="虚拟机编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${virtualSoftware.Virtual_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="SoftWare_ID" lablename="软件编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${virtualSoftware.SoftWare_ID}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件部署路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Software_Path" lablename="软件部署路径" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${virtualSoftware.Software_Path}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件监听端口:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Software_Port" lablename="软件监听端口" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${virtualSoftware.Software_Port}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆方式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_Type" lablename="软件登陆方式" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${virtualSoftware.Login_Type}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">数据库名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="DataBase" lablename="数据库名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${virtualSoftware.DataBase}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_UserName" lablename="软件登陆用户名" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${virtualSoftware.Login_UserName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">软件登陆密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="Login_Password" lablename="软件登陆密码" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${virtualSoftware.Login_Password}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" nowrap="nowarp" align="right">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="Remark" validate="{empty:false}">${virtualSoftware.Remark}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${virtualSoftware.id}"/>
	</form>
</body>
</html>
