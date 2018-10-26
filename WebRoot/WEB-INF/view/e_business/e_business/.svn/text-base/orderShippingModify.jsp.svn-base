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
   <td colspan="2" class="formHead">运货表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">EMAIL地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" lablename="EMAIL地址" class="inputText" validate="{maxlength:30,required:true,email:true}" isflag="tableflag" type="text" value="${orderShipping.EMAIL}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="FIRSTNAME" lablename="名字" class="inputText" validate="{maxlength:14,required:true}" isflag="tableflag" type="text" value="${orderShipping.FIRSTNAME}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">姓氏:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="LASTNAME" lablename="姓氏" class="inputText" validate="{maxlength:15,required:true}" isflag="tableflag" type="text" value="${orderShipping.LASTNAME}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">地址:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ADDRESS" lablename="地址" class="inputText" validate="{maxlength:50,required:true}" isflag="tableflag" type="text" value="${orderShipping.ADDRESS}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">城市:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="CITY" lablename="城市" class="inputText" validate="{maxlength:30,required:true,汉字:true}" isflag="tableflag" type="text" value="${orderShipping.CITY}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">州代码:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="STATE" lablename="州代码" class="inputText" validate="{maxlength:2,required:true,英文字母:true}" isflag="tableflag" type="text" value="${orderShipping.STATE}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">邮编:</td>
   <td class="formInput" style="width:80%;"><input name="ZIP" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:6,maxDecimalLen:0,required:true}" type="text" value="${orderShipping.ZIP}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">电话:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PHONE" lablename="电话" class="inputText" validate="{maxlength:15,required:true,手机号码:true}" isflag="tableflag" type="text" value="${orderShipping.PHONE}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">运货类型:</td>
   <td class="formInput" style="width:80%;"><label><input name="SHIPPING" value="NEXT DAY" lablename="运货类型" validate="{required:true}" type="radio" <c:if test='${orderShipping.SHIPPING=="NEXT DAY"}'>checked='checked'</c:if> />NEXT DAY</label><label><input name="SHIPPING" value="TWO DAY" lablename="运货类型" validate="{required:true}" type="radio" <c:if test='${orderShipping.SHIPPING=="TWO DAY"}'>checked='checked'</c:if> />TWO DAY</label><label><input name="SHIPPING" value="FIVE DAY" lablename="运货类型" validate="{required:true}" type="radio" <c:if test='${orderShipping.SHIPPING=="FIVE DAY"}'>checked='checked'</c:if> />FIVE DAY</label></td>
  </tr>
 </tbody>
</table>
