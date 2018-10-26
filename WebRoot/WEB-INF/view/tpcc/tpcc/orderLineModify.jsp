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
   <td colspan="2" class="formHead">order_line</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_d_id:</td>
   <td class="formInput" style="width:80%;"><input name="ol_d_id" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_d_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="ol_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_number:</td>
   <td class="formInput" style="width:80%;"><input name="ol_number" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0,required:true}'" type="text" value="${orderLine.ol_number}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_i_id:</td>
   <td class="formInput" style="width:80%;"><input name="ol_i_id" showtype="validate='{number:true,maxIntLen:10,maxDecimalLen:0}'" type="text" value="${orderLine.ol_i_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_supply_w_id:</td>
   <td class="formInput" style="width:80%;"><input name="ol_supply_w_id" showtype="validate='{number:true,maxIntLen:5,maxDecimalLen:0}'" type="text" value="${orderLine.ol_supply_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_delivery_d:</td>
   <td class="formInput" style="width:80%;"><input name="ol_delivery_d" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${orderLine.ol_delivery_d}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_quantity:</td>
   <td class="formInput" style="width:80%;"><input name="ol_quantity" showtype="validate='{number:true,maxIntLen:3,maxDecimalLen:0}'" type="text" value="${orderLine.ol_quantity}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_amount:</td>
   <td class="formInput" style="width:80%;"><input name="ol_amount" showtype="validate='{number:true,maxIntLen:4,maxDecimalLen:2}'" type="text" value="${orderLine.ol_amount}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">ol_dist_info:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ol_dist_info" lablename="ol_dist_info" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${orderLine.ol_dist_info}" /></span></td>
  </tr>
 </tbody>
</table>
