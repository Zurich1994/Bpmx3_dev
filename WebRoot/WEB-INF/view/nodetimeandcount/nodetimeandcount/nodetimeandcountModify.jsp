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
   <td class="formHead" colspan="2">节点发生时间与发生量表</td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">项目id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="projectId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:50}" lablename="项目id" value="${nodetimeandcount.projectId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">流程定义id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="defId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:50}" lablename="流程定义id" value="${nodetimeandcount.defId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">节点Id:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="nodeId" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="节点Id" value="${nodetimeandcount.nodeId}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">发生时间与发生量:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="timeAndCount" class="inputText" type="text" isflag="tableflag" validate="{maxlength:200}" lablename="发生时间与发生量" value="${nodetimeandcount.timeAndCount}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段1:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain1" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段1" value="${nodetimeandcount.remain1}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段2:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain2" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段2" value="${nodetimeandcount.remain2}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段3:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain3" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段3" value="${nodetimeandcount.remain3}" /></span></td>
  </tr>
  <tr>
   <td align="right" class="formTitle" nowrap="nowarp" style="width:20%;">预留字段4:</td>
   <td class="formInput" style="width:80%;"><span style="display:inline-block;" name="editable-input" isflag="tableflag"><input name="remain4" class="inputText" type="text" isflag="tableflag" validate="{maxlength:100}" lablename="预留字段4" value="${nodetimeandcount.remain4}" /></span></td>
  </tr>
 </tbody>
</table>
