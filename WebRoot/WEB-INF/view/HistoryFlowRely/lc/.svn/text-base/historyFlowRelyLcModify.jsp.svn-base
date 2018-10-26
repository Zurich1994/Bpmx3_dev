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
   <td colspan="2" class="formHead">历史数据流程依赖表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">依赖编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="relyId" lablename="依赖编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyFlowRelyLc.relyId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号1:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="processId1" lablename="流程编号1" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyFlowRelyLc.processId1}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程编号2:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="processId2" lablename="流程编号2" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyFlowRelyLc.processId2}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">依赖时间类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="relyTimeType" lablename="依赖时间类别" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${historyFlowRelyLc.relyTimeType}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">依赖参数:</td>
   <td class="formInput" style="width:80%;"><input name="relyNumber" showtype="{'coinValue':'','isShowComdify':0,'decimalValue':2}" validate="{number:true,maxIntLen:13,maxDecimalLen:2}" type="text" value="${historyFlowRelyLc.relyNumber}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">依赖发生时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="relyOccurTime" lablename="依赖发生时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${historyFlowRelyLc.relyOccurTime}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">依赖结束时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="relyEndTime" lablename="依赖结束时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${historyFlowRelyLc.relyEndTime}" /></span></td>
  </tr>
 </tbody>
</table>
