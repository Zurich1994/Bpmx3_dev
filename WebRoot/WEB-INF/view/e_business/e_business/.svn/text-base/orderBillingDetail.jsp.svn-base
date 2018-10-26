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
<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="2" class="formHead" style="word-break:break-all;"><br /></td>
  </tr>
  <tr>
   <td colspan="2" class="teamHead">Billing information</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">First name:</td>
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.FIRSTNAME}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Last name:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.LASTNAME}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Address:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.ADDRESS}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">City:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.CITY}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">State:</td>
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.STATE}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Zip:</td>
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.ZIP}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Phone:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.PHONE}</span></td>
  </tr>
  <tr>
   <td colspan="2" class="teamHead">Credit Card information</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Card type:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.CC_TYPE}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Card number:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${orderBilling.CC_NUM}</span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle" rowspan="2"><span style="font-family:'';font-size:medium;">Expiration date</span>:</td>
   <td class="formInput" style="width:80%;word-break:break-all;" rowspan="2"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><c:if test='${orderBilling.CC_EXPYEAR==2013}'>2013</c:if><c:if test='${orderBilling.CC_EXPYEAR==2014}'>2014</c:if><c:if test='${orderBilling.CC_EXPYEAR==2015}'>2015</c:if><c:if test='${orderBilling.CC_EXPYEAR==2016}'>2016</c:if><c:if test='${orderBilling.CC_EXPYEAR==2017}'>2017</c:if></span><c:if test='${orderBilling.CC_EXPMONTH==1}'>1</c:if><c:if test='${orderBilling.CC_EXPMONTH==2}'>2</c:if><c:if test='${orderBilling.CC_EXPMONTH==3}'>3</c:if><c:if test='${orderBilling.CC_EXPMONTH==4}'>4</c:if><c:if test='${orderBilling.CC_EXPMONTH==5}'>5</c:if><c:if test='${orderBilling.CC_EXPMONTH==6}'>6</c:if><c:if test='${orderBilling.CC_EXPMONTH==7}'>7</c:if><c:if test='${orderBilling.CC_EXPMONTH==8}'>8</c:if><c:if test='${orderBilling.CC_EXPMONTH==9}'>9</c:if><c:if test='${orderBilling.CC_EXPMONTH==10}'>10</c:if><c:if test='${orderBilling.CC_EXPMONTH==11}'>11</c:if><c:if test='${orderBilling.CC_EXPMONTH==12}'>12</c:if></span></td>
  </tr>
  <tr></tr>
 </tbody>
</table>
