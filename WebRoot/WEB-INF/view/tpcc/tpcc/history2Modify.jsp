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
   <td colspan="8" class="formHead">history2</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:11,maxDecimalLen:0}" type="text" value="${history2.h_c_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_d_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_d_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:4,maxDecimalLen:0}" type="text" value="${history2.h_c_d_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_d_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_d_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_d_id}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_w_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_w_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_w_id}" /></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_date:</td>
   <td style="width:15%;" class="formInput"><input name="h_date" class="Wdate" displaydate="0" validate="{empty:false}" type="text" value="<fmt:formatDate value='${history2.h_date}' pattern='yyyy-MM-dd HH:mm:ss'/>" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_amount:</td>
   <td style="width:15%;" class="formInput"><input name="h_amount" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_amount}" /></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_data:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="h_data" lablename="h_data" class="inputText" validate="{maxlength:24}" isflag="tableflag" type="text" value="${history2.h_data}" /></span></td>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">h_c_w_id:</td>
   <td style="width:15%;" class="formInput"><input name="h_c_w_id" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0}" type="text" value="${history2.h_c_w_id}" /></td>
  </tr>
 </tbody>
</table>
