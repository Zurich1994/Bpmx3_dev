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
   <td style="word-break:break-all;" class="formInput" width="421"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${checkaccount.USERID}</span></td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">账号:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${checkaccount.ACCOUNTNO}</span></td>
  </tr>
  <tr>
   <td align="right" style="word-break:break-all;" class="formTitle" nowrap="nowarp" width="263">类型:</td>
   <td style="word-break:break-all;" class="formInput" width="421">${checkaccount.TYPE}</td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">金额:</td>
   <td style="width:35%;" class="formInput">${checkaccount.BALANCE}</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总存款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421">${checkaccount.TOTALDEPOSIT}</td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均存款数:</td>
   <td style="width:35%;" class="formInput">${checkaccount.AVGDEPOSIT}</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" width="263">总取款数:</td>
   <td style="word-break:break-all;" class="formInput" width="421">${checkaccount.TOTALWITHDRAWL}</td>
   <td align="right" style="width:15%;" class="formTitle" nowrap="nowarp">平均取款数:</td>
   <td style="width:35%;" class="formInput">${checkaccount.AVGWITHDRAWAL}</td>
  </tr>
 </tbody>
</table>
