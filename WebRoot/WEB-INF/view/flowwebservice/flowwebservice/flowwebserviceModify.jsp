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
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceName" isflag="tableflag" validate="{maxlength:50}" lablename="serviceName" value="${flowwebservice.serviceName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务地址:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceUrl" isflag="tableflag" validate="{maxlength:100}" lablename="serviceUrl" value="${flowwebservice.serviceUrl}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务方法名:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block" name="editable-input" isflag="tableflag"><input class="inputText" name="serviceFuncName" isflag="tableflag" validate="{maxlength:100}" lablename="serviceFuncName" value="${flowwebservice.serviceFuncName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务类型:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="serviceType" validate="{empty:false}" lablename="serviceType" controltype="select"><option selected="selected" value="A" <c:if test='${flowwebservice.serviceType=="A"}'>selected='selected'</c:if>>服务类</option><option value="B" <c:if test='${flowwebservice.serviceType=="B"}'>selected='selected'</c:if>>电信类</option><option value="C" <c:if test='${flowwebservice.serviceType=="C"}'>selected='selected'</c:if>>医疗类</option><option value="D" <c:if test='${flowwebservice.serviceType=="D"}'>selected='selected'</c:if>>校园类</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">服务状态:</td>
   <td style="width:80%" class="formInput"><span style="display:inline-block;padding:2px;" class="selectinput" name="editable-input"><select name="serviceState" validate="{empty:false}" lablename="serviceState" controltype="select"><option selected="selected" value="A" <c:if test='${flowwebservice.serviceState=="A"}'>selected='selected'</c:if>>启用</option><option value="B" <c:if test='${flowwebservice.serviceState=="B"}'>selected='selected'</c:if>>未启用</option><option value="C" <c:if test='${flowwebservice.serviceState=="C"}'>selected='selected'</c:if>>禁止</option><option value="D" <c:if test='${flowwebservice.serviceState=="D"}'>selected='selected'</c:if>>暂停</option></select></span></td>
  </tr>
  <tr>
   <td style="width:20%;word-break:break-all" class="formTitle" nowrap="nowrap" align="right">流程ID:</td>
   <td style="width:80%" class="formInput"><input name="defid" validate="{number:true,maxIntLen:18,maxDecimalLen:0}" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':0}" value="${flowwebservice.defid}" /></td>
  </tr>
 </tbody>
</table>
