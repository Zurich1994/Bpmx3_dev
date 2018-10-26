<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>平台信息</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		$(function() {dialog.close();});
		
		function save() {		
			var url = __ctx + "/platform/system/subSystem/save1.ht";
			var para = $('#subSystemForm').serialize();
			$.post(url, para, showResult);
			
		}
		
		function showResult(responseText) {
		//	var obj = new com.hotent.form.ResultMessage(responseText);
		//	if (!obj.isSuccess()) {
		//		$.ligerDialog.err('出错信息',"未保存",obj.getMessage());
		//		return;
		//	} else {
				$.ligerDialog.success('保存成功!','提示信息',function() {
					dialog.close();
				});
		//	}
			
		}
	 
	</script>
	
	<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=subSystem"></script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		
		<div class="panel-toolbar">
			<div class="toolBar">
					
				    <div class="group"><a class="link save" href="javascript:save();"><span></span>保存</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  onclick="dialog.close()"><span></span>关闭</a></div>
					
			</div>
		</div>
	</div> 
	<form id="subSystemForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="4">平台信息</td>
  </tr>
    <tr style="height:23px">
   	  <td style="width:13%" class="formTitle" nowrap="nowrap">平台名称:</td>
  	  <td class="formInput"><input type="text" id="sysName" name="sysName"
		value="${subSystem.sysName}" class="inputText" /></td>
  	 </tr>
  	 <tr>
  	 <tr style="height:23px">
   	  <td style="width:13%" class="formTitle" nowrap="nowrap">联系人:</td>
  	  <td class="formInput"><input type="text" id="contacts" name="contacts"
		value="${subSystem.contacts}" class="inputText" /></td>
  	 </tr>
  	 <tr>
  	<td style="width:13%" class="formTitle" nowrap="nowrap">联系方式:</td>
     <td class="formInput"><input type="text" id="phoneNumber" name="phoneNumber"
		value="${subSystem.phoneNumber}" class="inputText" /></td>
   </tr>
   <tr style="height:23px"> 
    <td style="width:13%" class="formTitle" nowrap="nowrap">关于平台:</td>
   	 <td class="formInput"><input type="text" id="platIntroduce" name="platIntroduce"
		value="${subSystem.platIntroduce}" class="inputText" /></td>
   	 </tr>
   	 <tr>
    <td style="width:13%" class="formTitle" nowrap="nowrap">意见反馈:</td>
   	 <td style="width:20%" class="formInput"><input type="text"/></td>
    </tr>

 </tbody>
</table>
			</div>
		</div>
		
	</form>
	</div>
</body>
</html>
