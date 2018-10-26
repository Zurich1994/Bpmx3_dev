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
   <td style="word-break:break-all;" rowspan="1" colspan="5" class="formHead">注册<br /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户E-mail地址:</td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.EMAIL}</span></td>
  </tr>
  <tr>
   <td rowspan="1" colspan="1" style="word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">确认E-mail地址:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.EMAIL}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all;" class="formTitle" align="right" nowrap="nowarp">用户密码:</td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.PASSWORD}</span></td>
  </tr>
  <tr>
   <td style="word-break:break-all;" rowspan="1" colspan="1" class="formTitle" align="right" nowrap="nowarp">确认密码:<br /></td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.PASSWORD}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户名字:</td>
   <td colspan="4" rowspan="1" class="formInput" style="width:100%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.FIRSTNAME}</span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">用户姓氏:</td>
   <td colspan="4" rowspan="1" class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${registration.LASTNAME}</span></td>
  </tr>
 </tbody>
</table>
