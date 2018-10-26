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
<table class="formTable" border="1" cellpadding="2" cellspacing="0">
 <tbody>
  <tr>
   <td colspan="2" class="formHead">warehouse</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_name:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_name" lablename="w_name" class="inputText" validate="{maxlength:10}" isflag="tableflag" type="text" value="${warehouse.w_name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_street_1:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_street_1" lablename="w_street_1" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_street_1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_street_2:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_street_2" lablename="w_street_2" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_street_2}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_city:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_city" lablename="w_city" class="inputText" validate="{maxlength:20}" isflag="tableflag" type="text" value="${warehouse.w_city}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_state:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_state" lablename="w_state" class="inputText" validate="{maxlength:2}" isflag="tableflag" type="text" value="${warehouse.w_state}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_zip:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="w_zip" lablename="w_zip" class="inputText" validate="{maxlength:9}" isflag="tableflag" type="text" value="${warehouse.w_zip}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_tax:</td>
   <td class="formInput" style="width:80%;"><input name="w_tax" showtype="validate='{number:true,maxIntLen:2,maxDecimalLen:2}'" type="text" value="${warehouse.w_tax}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">w_ytd:</td>
   <td class="formInput" style="width:80%;"><input name="w_ytd" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:2}'" type="text" value="${warehouse.w_ytd}" /></td>
  </tr>
 </tbody>
</table>
