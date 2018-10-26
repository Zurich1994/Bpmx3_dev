<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx}/js/hotent/scriptMgr.js"></script>
<script type="text/javascript">
	function afterOnload(){
		var afterLoadJs=[
		     			'${ctx}/js/hotent/formdata.js',
		     			'${ctx}/js/hotent/subform.js'
		 ];
		ScriptMgr.load({
			scripts : afterLoadJs
		});
	}
</script>
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
