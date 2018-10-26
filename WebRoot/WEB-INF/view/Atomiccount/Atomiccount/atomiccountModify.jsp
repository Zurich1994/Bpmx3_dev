<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/codegen/include/customForm.jsp" %>
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
   <td colspan="2" class="formHead">原子运行次数</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:bpmid" lablename="流程id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.bpmid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">操作图id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:operaterid" lablename="操作图id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.operaterid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">事务图id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:transactionid" lablename="事务图id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.transactionid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">原子操作id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:atomiccount:atomicid" lablename="原子操作id" class="inputText" validate="{maxlength:50}" isflag="tableflag" type="text" value="${atomiccount.atomicid}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">运行次数:</td>
   <td class="formInput" style="width:80%;"><input name="m:atomiccount:runs" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0}" type="text" value="${atomiccount.runs}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">时间:</td>
   <td class="formInput" style="width:80%;"><input name="m:atomiccount:time" class="Wdate" displaydate="0" datefmt="yyyy-MM-dd HH:mm:ss" validate="{empty:false}" type="text" value="${atomiccount.time}" /></td>
  </tr>
 </tbody>
</table>
