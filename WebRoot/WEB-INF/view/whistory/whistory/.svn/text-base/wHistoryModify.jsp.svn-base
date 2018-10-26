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
   <td colspan="2" class="formHead">w_history</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_c_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_c_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${wHistory.h_c_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_c_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_c_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${wHistory.h_c_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${wHistory.h_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="h_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${wHistory.h_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_date:</td>
   <td class="formInput" style="width:80%;"><input name="h_date" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${wHistory.h_date}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_amount:</td>
   <td class="formInput" style="width:80%;"><input name="h_amount" showtype="validate='{number:true,maxIntLen:4,maxDecimalLen:2}'" type="text" value="${wHistory.h_amount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">h_data:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="h_data" lablename="h_data" class="inputText" validate="{maxlength:48}" isflag="tableflag" type="text" value="${wHistory.h_data}" /></span></td>
  </tr>
 </tbody>
</table>
