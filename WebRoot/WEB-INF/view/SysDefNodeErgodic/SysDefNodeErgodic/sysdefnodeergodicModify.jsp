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
   <td colspan="2" class="formHead">子系统流程节点遍历</td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">主键:</td>
   <td class="formInput" style="width:80%;"><input name="m:SysDefNodeErgodic:zj" showtype="{"coinValue":"","isShowComdify":0,"decimalValue":0}" validate="{number:true,maxIntLen:13,maxDecimalLen:0,required:true}" type="text" value="${SysDefNodeErgodic.zj}" /></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">子系统名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:sysName" lablename="子系统名字" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.sysName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">子系统id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:sysId" lablename="子系统id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.sysId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:defName" lablename="流程名字" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.defName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:defId" lablename="流程id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.defId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">流程版本id:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:actDefId" lablename="流程版本id" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.actDefId}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">节点名字:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:nodeName" lablename="节点名字" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.nodeName}" /></span></td>
  </tr>
  <tr>
   <td style="width:20%;" class="formTitle" align="right" nowrap="nowarp">节点作业名:</td>
   <td class="formInput" style="width:80%;"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input name="m:SysDefNodeErgodic:nodeWorkName" lablename="节点作业名" class="inputText" validate="{maxlength:100}" isflag="tableflag" type="text" value="${SysDefNodeErgodic.nodeWorkName}" /></span></td>
  </tr>
 </tbody>
</table>
