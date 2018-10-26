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
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="FIRSTNAME" lablename="FIRSTNAME" class="inputText" validate="{maxlength:14,required:true}" isflag="tableflag" value="${orderBilling.FIRSTNAME}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Last name:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="LASTNAME" lablename="LASTNAME" class="inputText" validate="{maxlength:15,required:true}" isflag="tableflag" value="${orderBilling.LASTNAME}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Address:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="ADDRESS" lablename="ADDRESS" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" value="${orderBilling.ADDRESS}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">City:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="CITY" lablename="CITY" class="inputText" validate="{maxlength:30,required:true}" isflag="tableflag" value="${orderBilling.CITY}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">State:</td>
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="STATE" lablename="STATE" class="inputText" validate="{maxlength:2,required:true,英文字母:true}" isflag="tableflag" value="${orderBilling.STATE}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Zip:</td>
   <td class="formInput" style="width:80%;word-break:break-all;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="ZIP" lablename="ZIP" class="inputText" validate="{maxlength:5,required:true,非负整数:true}" isflag="tableflag" value="${orderBilling.ZIP}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Phone:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="PHONE" lablename="PHONE" class="inputText" validate="{maxlength:15,required:true,手机号码:true}" isflag="tableflag" value="${orderBilling.PHONE}" /></span></td>
  </tr>
  <tr>
   <td colspan="2" class="teamHead">Credit Card information</td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Card type:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="CC_TYPE" lablename="CC_TYPE" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" value="${orderBilling.CC_TYPE}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle">Card number:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="CC_NUM" lablename="CC_NUM" class="inputText" validate="{maxlength:16,required:true}" isflag="tableflag" value="${orderBilling.CC_NUM}" /></span></td>
  </tr>
  <tr>
   <td align="right" nowrap="nowarp" style="width:20%;word-break:break-all;" class="formTitle" rowspan="2"><span style="font-family:'';font-size:medium;">Expiration date</span>:</td>
   <td class="formInput" style="width:80%;word-break:break-all;" rowspan="2"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="CC_EXPYEAR" lablename="CC_EXPYEAR" controltype="select" validate="{empty:false,required:true}"><option value="2013" <c:if test='${orderBilling.CC_EXPYEAR=="2013"}'>selected='selected'</c:if>>2013</option><option value="2014" <c:if test='${orderBilling.CC_EXPYEAR=="2014"}'>selected='selected'</c:if>>2014</option><option value="2015" <c:if test='${orderBilling.CC_EXPYEAR=="2015"}'>selected='selected'</c:if>>2015</option><option value="2016" <c:if test='${orderBilling.CC_EXPYEAR=="2016"}'>selected='selected'</c:if>>2016</option><option value="2017" <c:if test='${orderBilling.CC_EXPYEAR=="2017"}'>selected='selected'</c:if>>2017</option></select></span><select name="CC_EXPMONTH" lablename="CC_EXPMONTH" controltype="select" validate="{empty:false,required:true}"><option value="1" <c:if test='${orderBilling.CC_EXPMONTH=="1"}'>selected='selected'</c:if>>1</option><option value="2" <c:if test='${orderBilling.CC_EXPMONTH=="2"}'>selected='selected'</c:if>>2</option><option value="3" <c:if test='${orderBilling.CC_EXPMONTH=="3"}'>selected='selected'</c:if>>3</option><option value="4" <c:if test='${orderBilling.CC_EXPMONTH=="4"}'>selected='selected'</c:if>>4</option><option value="5" <c:if test='${orderBilling.CC_EXPMONTH=="5"}'>selected='selected'</c:if>>5</option><option value="6" <c:if test='${orderBilling.CC_EXPMONTH=="6"}'>selected='selected'</c:if>>6</option><option value="7" <c:if test='${orderBilling.CC_EXPMONTH=="7"}'>selected='selected'</c:if>>7</option><option value="8" <c:if test='${orderBilling.CC_EXPMONTH=="8"}'>selected='selected'</c:if>>8</option><option value="9" <c:if test='${orderBilling.CC_EXPMONTH=="9"}'>selected='selected'</c:if>>9</option><option value="10" <c:if test='${orderBilling.CC_EXPMONTH=="10"}'>selected='selected'</c:if>>10</option><option value="11" <c:if test='${orderBilling.CC_EXPMONTH=="11"}'>selected='selected'</c:if>>11</option><option value="12" <c:if test='${orderBilling.CC_EXPMONTH=="12"}'>selected='selected'</c:if>>12</option></select></span></td>
  </tr>
  <tr></tr>
 </tbody>
</table>
