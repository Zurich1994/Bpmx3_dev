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
   <td style="word-break:break-all;" colspan="8" class="formHead">购物车</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">itemname:</td>
   <td style="word-break:break-all;" class="formInput" width="177"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.itemname}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="139">定制:</td>
   <td style="word-break:break-all;" class="formInput" width="212"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.Customizations}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="104">数量:</td>
   <td style="word-break:break-all;" class="formInput" width="208">${confirmation.Quantity}</td>
   <td class="formTitle" align="right" nowrap="nowarp" width="120">价格:</td>
   <td style="width:15%;" class="formInput">${confirmation.price}</td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">总价:</td>
   <td style="word-break:break-all;" class="formInput" width="177">${confirmation.total_price}</td>
   <td class="formTitle" align="right" nowrap="nowarp" width="139">运输姓名:</td>
   <td style="word-break:break-all;" class="formInput" width="212"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingname}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="104">运输地址:</td>
   <td style="word-break:break-all;" class="formInput" width="208"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingaddress}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="120">运输城市:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingcity}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">运输州代码:</td>
   <td style="word-break:break-all;" class="formInput" width="177"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingstate}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="139">运输邮编:</td>
   <td style="word-break:break-all;" class="formInput" width="212"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingzip}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="104">运输电话:</td>
   <td style="word-break:break-all;" class="formInput" width="208"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shippingphone}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="120">运输类型:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.shipping}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp">账单姓名:</td>
   <td style="word-break:break-all;" class="formInput" width="177"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingname}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="139">账单地址:</td>
   <td style="word-break:break-all;" class="formInput" width="212"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingaddress}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="104">账单城市:</td>
   <td style="word-break:break-all;" class="formInput" width="208"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingcity}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" width="120">账单州代码:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingstate}</span></td>
  </tr>
  <tr>
   <td style="width:10%;" class="formTitle" align="right" nowrap="nowarp" height="272">账单邮编:</td>
   <td style="word-break:break-all;" class="formInput" height="272" width="177"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingzip}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" height="272" width="139">账单电话:</td>
   <td style="word-break:break-all;" class="formInput" height="272" width="212"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.billingphone}</span></td>
   <td class="formTitle" align="right" nowrap="nowarp" height="272" width="104">信用卡:</td>
   <td style="word-break:break-all;" class="formInput" height="272" width="208"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${confirmation.creditcard}</span></td>
   <td class="formTitle" height="272" width="120"><br /></td>
   <td style="width:15%;" class="formInput" height="272"><br /></td>
  </tr>
 </tbody>
</table>
