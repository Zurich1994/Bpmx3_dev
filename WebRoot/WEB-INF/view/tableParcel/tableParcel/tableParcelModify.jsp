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
   <td colspan="2" class="formHead">table_parcel</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">表名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="table_name" lablename="表名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${tableParcel.table_name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数据包名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="parcel_name" lablename="数据包名" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${tableParcel.parcel_name}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数据包注释:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="parcel_alias" lablename="数据包注释" class="inputText" validate="{maxlength:500}" isflag="tableflag" type="text" value="${tableParcel.parcel_alias}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">数据包值:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="parcel_value" lablename="数据包值" class="inputText" validate="{maxlength:1000}" isflag="tableflag" type="text" value="${tableParcel.parcel_value}" /></span></td>
  </tr>
 </tbody>
</table>
