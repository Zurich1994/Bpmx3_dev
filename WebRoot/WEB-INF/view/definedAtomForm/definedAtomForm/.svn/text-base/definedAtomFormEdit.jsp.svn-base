<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 defined_atom_form</title>
	<%@include file="/codegen/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#definedAtomFormForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#definedAtomFormForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						window.location.reload();
					}else{
						window.location.href = "${ctx}/definedAtomForm/definedAtomForm/definedAtomForm/list.ht";
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
			    <c:when test="${not empty definedAtomFormItem.id}">
			        <span class="tbar-label"><span></span>编辑defined_atom_form</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加defined_atom_form</span>
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
	<form id="definedAtomFormForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="8">defined_atom_form</td>
  </tr>
  <tr>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">setId:</td>
   <td style="width:15%" class="formInput"><input name="setId" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${definedAtomForm.setId}" /></td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">defId:</td>
   <td style="width:15%" class="formInput"><input name="defId" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${definedAtomForm.defId}" /></td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">nodeId:</td>
   <td style="width:15%" class="formInput"><input name="nodeId" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${definedAtomForm.nodeId}" /></td>
   <td style="width:10%" class="formTitle" nowrap="nowrap" align="right">formKey:</td>
   <td style="width:15%" class="formInput"><input name="formKey" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${definedAtomForm.formKey}" /></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${definedAtomForm.id}"/>
	</form>
</body>
</html>
