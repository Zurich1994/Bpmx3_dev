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
   <td colspan="4" class="formHead">excel 数据临时表</td>
  </tr>
  <tr>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">时间:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="sj" lablename="时间" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${excelsjlsbLc.sj}" /></span></td>
   <td style="width:15%;" class="formTitle" align="right" nowrap="nowarp">发生量:</td>
   <td style="width:35%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="fsl" lablename="发生量" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${excelsjlsbLc.fsl}" /></span></td>
  </tr>
 </tbody>
</table>
