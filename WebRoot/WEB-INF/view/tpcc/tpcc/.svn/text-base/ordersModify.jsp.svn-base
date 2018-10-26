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
   <td colspan="2" class="formHead">orders</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="o_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orders.o_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="o_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${orders.o_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_c_id:</td>
   <td class="formInput" style="width:80%;"><input name="o_c_id" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:0}'" type="text" value="${orders.o_c_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_entry_d:</td>
   <td class="formInput" style="width:80%;"><input name="o_entry_d" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${orders.o_entry_d}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_carrier_id:</td>
   <td class="formInput" style="width:80%;"><input name="o_carrier_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_carrier_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_ol_cnt:</td>
   <td class="formInput" style="width:80%;"><input name="o_ol_cnt" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_ol_cnt}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">o_all_local:</td>
   <td class="formInput" style="width:80%;"><input name="o_all_local" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orders.o_all_local}" /></td>
  </tr>
 </tbody>
</table>
