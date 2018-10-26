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
   <td colspan="4" class="formHead">账户概要</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">用户名:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="USERID" lablename="用户名" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" value="${checkaccount.USERID}" /></span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">账号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="ACCOUNTNO" lablename="账号" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" value="${checkaccount.ACCOUNTNO}" /></span></td>
  </tr>
  <tr>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp" width="263">类型:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TYPE" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:2,maxDecimalLen:0,required:true}" value="${checkaccount.TYPE}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">金额:</td>
   <td style="width:35%;" class="formInput"><input name="BALANCE" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.BALANCE}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总存款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TOTALDEPOSIT" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.TOTALDEPOSIT}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均存款数:</td>
   <td style="width:35%;" class="formInput"><input name="AVGDEPOSIT" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.AVGDEPOSIT}" /></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总取款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421"><input name="TOTALWITHDRAWL" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.TOTALWITHDRAWL}" /></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均取款数:</td>
   <td style="width:35%;" class="formInput"><input name="AVGWITHDRAWAL" type="text" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" value="${checkaccount.AVGWITHDRAWAL}" /></td>
  </tr>
 </tbody>
</table>
