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
<table class="formTable" border="1" cellspacing="0" cellpadding="2">
 <tbody>
  <tr>
   <td class="formHead" colspan="2">flowWebService</td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务名称:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceName}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务地址:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceUrl}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务方法名:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag">${flowwebservice.serviceFuncName}</span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${flowwebservice.serviceType==A}'>服务类</c:if><c:if test='${flowwebservice.serviceType==B}'>电信类</c:if><c:if test='${flowwebservice.serviceType==C}'>医疗类</c:if><c:if test='${flowwebservice.serviceType==D}'>校园类</c:if></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务状态:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><c:if test='${flowwebservice.serviceState==A}'>启用</c:if><c:if test='${flowwebservice.serviceState==B}'>未启用</c:if><c:if test='${flowwebservice.serviceState==C}'>禁止</c:if><c:if test='${flowwebservice.serviceState==D}'>暂停</c:if></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">流程ID:</td>
   <td style="width:80%" class="formInput">${flowwebservice.defid}</td>
  </tr>
 </tbody>
</table>
