<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 projectInfo</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#projectinfoForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#projectinfoForm').submit();
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
						window.location.href = "${ctx}/projectinfo/projectinfo/projectinfo/list.ht";
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
			    <c:when test="${not empty projectinfoItem.id}">
			        <span class="tbar-label"><span></span>编辑projectInfo</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加projectInfo</span>
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
	<form id="projectinfoForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">projectInfo</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目名称:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="projectName" lablename="项目名称" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${projectinfo.projectName}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">description:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="description" lablename="description" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${projectinfo.description}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">开始时间:</td>
   <td class="formInput" style="width:80%;"><input name="stime" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${projectinfo.stime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">结束时间:</td>
   <td class="formInput" style="width:80%;"><input name="etime" type="text" class="Wdate" displaydate="0" validate="{empty:false,required:true}" value="<fmt:formatDate value='${projectinfo.etime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际开始时间:</td>
   <td class="formInput" style="width:80%;"><input name="astime" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${projectinfo.astime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">实际结束时间:</td>
   <td class="formInput" style="width:80%;"><input name="aetime" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${projectinfo.aetime}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">删除开关:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="deleteFlag" lablename="删除开关" class="inputText" validate="{maxlength:1}" isflag="tableflag" value="${projectinfo.deleteFlag}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">项目报文:</td>
   <td class="formInput" style="width:80%;"><textarea name="projectXml" validate="{empty:false}">${projectinfo.projectXml}</textarea></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${projectinfo.id}"/>
	</form>
</body>
</html>
