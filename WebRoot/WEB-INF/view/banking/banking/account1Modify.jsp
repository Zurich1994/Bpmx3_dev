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
   <td colspan="8" class="formHead">账户概要</td>
   <td colspan="1" class="formHead"><br /></td>
   <td colspan="1" class="formHead"><br /></td>
  </tr>
  <tr>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="197">用户名:</td>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="135">账号:</td>
   <td style="text-align:center;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp" width="180">类型：</td>
   <td style="text-align:center;" class="formTitle" align="right" nowrap="nowarp" width="342">金额:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">总存款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">平均存款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">总取款数:</td>
   <td style="width:10%;text-align:center;" class="formTitle" align="right" nowrap="nowarp">平均取款数:</td>
  
  </tr>
  <tr>
   <td style="word-break:break-all;" class="formInput" width="197"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="USERID" lablename="用户名" class="inputText" validate="{maxlength:20,required:true}" isflag="tableflag" type="text" value="${account1.USERID}" /></span></td>
   <td style="word-break:break-all;" class="formInput" width="135"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="ACCOUNTNO" lablename="账号" class="inputText" validate="{maxlength:10,required:true}" isflag="tableflag" type="text" value="${account1.ACCOUNTNO}" /></span></td>
   <td style="word-break:break-all;" class="formInput" width="180"><input name="TYPE" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" validate="{number:true,maxIntLen:2,maxDecimalLen:0,required:true}" type="text" value="${account1.TYPE}" /></td>
   <td class="formInput" width="342"><input name="BALANCE" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.BALANCE}" /></td>
   <td style="width:15%;" class="formInput"><input name="TOTALDEPOSIT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.TOTALDEPOSIT}" /></td>
   <td style="width:15%;" class="formInput"><input name="AVGDEPOSIT" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.AVGDEPOSIT}" /></td>
   <td style="width:15%;" class="formInput"><input name="TOTALWITHDRAWL" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.TOTALWITHDRAWL}" /></td>
   <td style="width:15%;" class="formInput"><input name="AVGWITHDRAWAL" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:10,maxDecimalLen:2,required:true}" type="text" value="${account1.AVGWITHDRAWAL}" /></td>
  
  </tr>
 </tbody>
</table>
