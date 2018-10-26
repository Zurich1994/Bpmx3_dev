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
   <td colspan="2" class="formHead">业务曲线信息表</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线数据编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveDataId" lablename="曲线数据编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${businessCurve.curveDataId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveId" lablename="曲线编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${businessCurve.curveId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线类别:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveType" lablename="曲线类别" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${businessCurve.curveType}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">基本公式:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="basicExpression" lablename="基本公式" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${businessCurve.basicExpression}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">历史数据编号:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="historyDataId" lablename="历史数据编号" class="inputText" validate="{maxlength:128}" isflag="tableflag" type="text" value="${businessCurve.historyDataId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线开始时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveStartTime" lablename="曲线开始时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${businessCurve.curveStartTime}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">曲线结束时间:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="curveEndTime" lablename="曲线结束时间" class="inputText" validate="{maxlength:200}" isflag="tableflag" type="text" value="${businessCurve.curveEndTime}" /></span></td>
  </tr>
 </tbody>
</table>
