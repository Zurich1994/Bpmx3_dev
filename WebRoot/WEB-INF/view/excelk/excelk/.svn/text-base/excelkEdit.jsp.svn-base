<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 excelk</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/subform.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#excelkForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				$("#saveData").val(1);
				if(frm.valid()){
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					if(OfficePlugin.officeObjs.length>0){
						OfficePlugin.submit(function(){
							frm.handleFieldName();
							$('#excelkForm').submit();
						});
					}else{
						frm.handleFieldName();
						$('#excelkForm').submit();
					}
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.href = window.location.href;
					}else{
					    window.location.href = window.location.href;
						//window.location.href = "${ctx}/excelk/excelk/excelk/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel" style="height:100%;overflow:auto;">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty excelkItem.id}">
			        <span class="tbar-label"><span></span>编辑excelk</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加excelk</span>
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
	<form id="excelkForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">excelk</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">导入excel数据:</td>
   <td class="formInput" style="width:80%;" right="w">
    <div name="div_attachment_container" right="w">
     <div class="attachement"></div>
     <textarea name="drsj" style="display:none;" lablename="导入excel数据" controltype="attachment">${excel.drsj}</textarea>
     <a class="link selectFile" onclick="{AttachMent.directUpLoadFile(this);}"  validate="{required:true}" atype="select" field="drsj" name="drsj">选择</a>
    </div></td>
  </tr>
  <%-- 
 
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">time:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:excelk:time" lablename="time" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${excelk.time}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">reguval:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:excelk:reguval" lablename="reguval" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${excelk.reguval}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">processId:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:excelk:processId" lablename="processId" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${excelk.processId}" /></span></td>
  </tr>
   <tr>
   <td align="right" nowrap="nowarp" style="width:20%;" class="formTitle">count:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="m:excelk:count" lablename="count" class="inputText" validate="{maxlength:100}" isflag="tableflag" value="${excelk.count}" /></span></td>
  </tr>
   --%> 
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${excelk.id}"/>
		<input type="hidden" id="saveData" name="saveData"/>
		<input type="hidden" name="executeType"  value="start" />
	</form>
</body>
</html>
