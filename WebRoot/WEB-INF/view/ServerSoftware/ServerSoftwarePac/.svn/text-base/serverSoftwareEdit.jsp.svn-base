<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 服务器-软件表</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#serverSoftwareForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#serverSoftwareForm').submit();
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
						window.location.href = "${ctx}/ServerSoftware/ServerSoftwarePac/serverSoftware/list.ht";
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
			    <c:when test="${not empty serverSoftwareItem.id}">
			        <span class="tbar-label"><span></span>编辑服务器-软件表</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加服务器-软件表</span>
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
	<form id="serverSoftwareForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">服务器-软件表</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">序号:</td>
   <td class="formInput" style="width:80%;"><input name="id" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" value="${serverSoftware.id}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">服务器编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="server_ID" lablename="服务器编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${serverSoftware.server_ID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="softWare_ID" lablename="软件编号" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${serverSoftware.softWare_ID}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件部署路径:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="software_Path" lablename="软件部署路径" class="inputText" validate="{maxlength:200}" isflag="tableflag" value="${serverSoftware.software_Path}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件监听端口:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="software_Port" lablename="软件监听端口" class="inputText" validate="{maxlength:20}" isflag="tableflag" value="${serverSoftware.software_Port}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件登陆方式:</td>
   <td class="formInput" style="width:80%;"><input lablename="软件登陆方式" class="dicComboTree" nodekey="rjdlfs" validate="{empty:false}" name="login_Type" height="200" width="125" value="${serverSoftware.login_Type}" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">数据库名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="dataBase" lablename="数据库名" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${serverSoftware.dataBase}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件登陆用户名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="login_UserName" lablename="软件登陆用户名" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${serverSoftware.login_UserName}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">软件登陆密码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="login_Password" lablename="软件登陆密码" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${serverSoftware.login_Password}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">备注:</td>
   <td class="formInput" style="width:80%;"><textarea name="remark" validate="{empty:false}">${serverSoftware.remark}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${serverSoftware.id}"/>
	</form>
</body>
</html>
