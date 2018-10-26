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
   <td style="word-break:break-all;" rowspan="1" colspan="2" class="formHead">登陆<br /></td>
  </tr>
  <tr>
   <td class="formTitle" align="right" nowrap="nowarp" width="359">用户E-mail地址:</td>
   <td class="formInput" style="word-break:break-all;" width="737"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="EMAIL" lablename="用户E-mail地址" class="inputText" validate="{maxlength:30,required:true,email:true}" isflag="tableflag" type="text" value="${userInfo.EMAIL}" /></span></td>
  </tr>
  <tr>
   <td colspan="1" rowspan="3" class="formTitle" align="right" nowrap="nowarp" width="359">用户密码:</td>
   <td colspan="1" rowspan="3" class="formInput" style="word-break:break-all;" width="737"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="PASSWORD" lablename="用户密码" class="inputText" validate="{maxlength:32,required:true,英数字:true}" isflag="tableflag" type="text" value="${userInfo.PASSWORD}" /></span></td>
  </tr>
  <tr></tr>
  <tr></tr>
  <tr>
   <td style="text-align:center;word-break:break-all;" colspan="2" rowspan="1" class="formTitle" align="right" nowrap="nowarp"><button onclick="match()">登陆</button></td>
  </tr>
 </tbody>
</table>
